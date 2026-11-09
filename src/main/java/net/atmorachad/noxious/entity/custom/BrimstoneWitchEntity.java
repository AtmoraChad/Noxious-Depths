
package net.atmorachad.noxious.entity.custom;

import net.atmorachad.noxious.potion.ModPotions;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableWitchTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestHealableRaiderTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownSplashPotion;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class BrimstoneWitchEntity extends Raider implements RangedAttackMob {

    private static final Identifier SPEED_MODIFIER_DRINKING_ID =
            Identifier.withDefaultNamespace("drinking");

    private static final AttributeModifier SPEED_MODIFIER_DRINKING =
            new AttributeModifier(
                    SPEED_MODIFIER_DRINKING_ID,
                    -0.25,
                    AttributeModifier.Operation.ADD_VALUE
            );

    private static final EntityDataAccessor<Boolean> DATA_USING_ITEM =
            SynchedEntityData.defineId(
                    BrimstoneWitchEntity.class,
                    EntityDataSerializers.BOOLEAN
            );

    private int usingTime;

    private NearestHealableRaiderTargetGoal<Raider> healRaidersGoal;
    private NearestAttackableWitchTargetGoal<Player> attackPlayersGoal;

    // Defensive potion sequence
    private boolean defensivePotionSequenceUsed = false;
    private boolean defensivePotionSequenceActive = false;
    private int defensivePotionsThrown = 0;
    private int defensivePotionThrowCooldown = 0;

    public BrimstoneWitchEntity(
            final EntityType<? extends BrimstoneWitchEntity> type,
            final Level level
    ) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.healRaidersGoal = new NearestHealableRaiderTargetGoal<>(
                this,
                Raider.class,
                true,
                (target, level) ->
                        this.hasActiveRaid() && !target.is(EntityTypes.WITCH)
        );

        this.attackPlayersGoal =
                new NearestAttackableWitchTargetGoal<>(
                        this,
                        Player.class,
                        10,
                        true,
                        false,
                        null
                );

        this.targetSelector.addGoal(
                3,
                new NearestAttackableTargetGoal<>(
                        this,
                        AbstractVillager.class,
                        false
                )
        );

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(
                2,
                new RangedAttackGoal(this, 1.0, 60, 10.0F)
        );
        this.goalSelector.addGoal(
                2,
                new WaterAvoidingRandomStrollGoal(this, 1.0)
        );
        this.goalSelector.addGoal(
                3,
                new LookAtPlayerGoal(this, Player.class, 8.0F)
        );
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(
                1,
                new HurtByTargetGoal(this, Raider.class)
        );
        this.targetSelector.addGoal(2, this.healRaidersGoal);
        this.targetSelector.addGoal(3, this.attackPlayersGoal);
    }

    @Override
    protected void defineSynchedData(
            final SynchedEntityData.Builder entityData
    ) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_USING_ITEM, false);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITCH_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(final DamageSource source) {
        return SoundEvents.WITCH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WITCH_DEATH;
    }

    public void setUsingItem(final boolean using) {
        this.getEntityData().set(DATA_USING_ITEM, using);
    }

    public boolean isDrinkingPotion() {
        return this.getEntityData().get(DATA_USING_ITEM);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    // Starts drinking a potion
    private void startDrinkingPotion(Holder<Potion> potion) {
        this.setItemSlot(
                EquipmentSlot.MAINHAND,
                PotionContents.createItemStack(Items.POTION, potion)
        );

        this.usingTime = this.getMainHandItem().getUseDuration(this);
        this.setUsingItem(true);

        if (!this.isSilent()) {
            this.level().playSound(
                    null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    SoundEvents.WITCH_DRINK,
                    this.getSoundSource(),
                    1.0F,
                    0.8F + this.random.nextFloat() * 0.4F
            );
        }

        AttributeInstance speed =
                this.getAttribute(Attributes.MOVEMENT_SPEED);

        speed.removeModifier(SPEED_MODIFIER_DRINKING_ID);
        speed.addTransientModifier(SPEED_MODIFIER_DRINKING);
    }

    // Throws a splash potion downwards at the witch's position
    private void throwPotionAtFeet(MobEffectInstance effect) {
        if (this.level() instanceof ServerLevel serverLevel) {
            ItemStack itemStack = new ItemStack(Items.SPLASH_POTION);

            itemStack.set(
                    DataComponents.POTION_CONTENTS,
                    PotionContents.EMPTY.withEffectAdded(effect)
            );

            Projectile.spawnProjectileUsingShoot(
                    ThrownSplashPotion::new,
                    serverLevel,
                    itemStack,
                    this,
                    0.0,
                    -1.0,
                    0.0,
                    0.5F,
                    0.0F
            );
        }

        if (!this.isSilent()) {
            this.level().playSound(
                    null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    SoundEvents.WITCH_THROW,
                    this.getSoundSource(),
                    1.0F,
                    0.8F + this.random.nextFloat() * 0.4F
            );
        }
    }

    @Override
    public void aiStep() {
        if (!this.level().isClientSide() && this.isAlive()) {

            // Raid ally healing cooldown
            this.healRaidersGoal.decrementCooldown();

            if (this.healRaidersGoal.getCooldown() <= 0) {
                this.attackPlayersGoal.setCanAttack(true);
            } else {
                this.attackPlayersGoal.setCanAttack(false);
            }

            // Potion drinking
            if (this.isDrinkingPotion()) {

                if (this.usingTime-- <= 0) {
                    this.setUsingItem(false);

                    ItemStack itemStack = this.getMainHandItem();
                    this.setItemSlot(
                            EquipmentSlot.MAINHAND,
                            ItemStack.EMPTY
                    );

                    PotionContents potion =
                            itemStack.get(DataComponents.POTION_CONTENTS);

                    if (itemStack.is(Items.POTION) && potion != null) {
                        potion.forEachEffect(
                                this::addEffect,
                                itemStack.getOrDefault(
                                        DataComponents.POTION_DURATION_SCALE,
                                        1.0F
                                )
                        );
                    }

                    this.gameEvent(GameEvent.DRINK);

                    this.getAttribute(Attributes.MOVEMENT_SPEED)
                            .removeModifier(SPEED_MODIFIER_DRINKING.id());

                    // Brief delay after drinking invisibility
                    if (this.defensivePotionSequenceActive) {
                        this.defensivePotionThrowCooldown = 5;
                    }
                }

            } else if (this.defensivePotionSequenceActive) {

                // Defensive potion sequence
                if (this.defensivePotionThrowCooldown > 0) {
                    this.defensivePotionThrowCooldown--;

                } else {

                    if (this.defensivePotionsThrown == 0) {
                        this.throwPotionAtFeet(
                                new MobEffectInstance(MobEffects.NAUSEA, 100)
                        );
                    } else {
                        this.throwPotionAtFeet(
                                new MobEffectInstance(MobEffects.BLINDNESS, 100)
                        );

                    }

                    this.defensivePotionsThrown++;

                    if (this.defensivePotionsThrown >= 2) {
                        this.defensivePotionSequenceActive = false;
                    } else {
                        this.defensivePotionThrowCooldown = 10;
                    }
                }

            } else if (!this.defensivePotionSequenceUsed
                    && this.getHealth() <= this.getMaxHealth() * 0.5F) {

                // Trigger defensive ability at 50% health
                this.defensivePotionSequenceUsed = true;
                this.defensivePotionSequenceActive = true;
                this.defensivePotionsThrown = 0;

                this.startDrinkingPotion(Potions.INVISIBILITY);

            } else {

                // Normal Witch potion selection
                Holder<Potion> potion = null;

                if (this.random.nextFloat() < 0.15F
                        && this.isEyeInFluid(FluidTags.WATER)
                        && !this.hasEffect(MobEffects.WATER_BREATHING)) {

                    potion = Potions.WATER_BREATHING;

                } else if (this.random.nextFloat() < 0.15F
                        && (this.isOnFire()
                        || this.getLastDamageSource() != null
                        && this.getLastDamageSource().is(DamageTypeTags.IS_FIRE))
                        && !this.hasEffect(MobEffects.FIRE_RESISTANCE)) {

                    potion = Potions.FIRE_RESISTANCE;

                } else if (this.random.nextFloat() < 0.05F
                        && this.getHealth() < this.getMaxHealth()) {

                    potion = Potions.HEALING;

                } else if (this.random.nextFloat() < 0.5F
                        && this.getTarget() != null
                        && !this.hasEffect(MobEffects.SPEED)
                        && this.getTarget().distanceToSqr(this) > 121.0) {

                    potion = Potions.SWIFTNESS;
                }

                if (potion != null) {
                    this.startDrinkingPotion(potion);
                }
            }

            // Witch ambient particles
            if (this.random.nextFloat() < 7.5E-4F) {
                this.level().broadcastEntityEvent(this, (byte) 15);
            }
        }

        super.aiStep();
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.WITCH_CELEBRATE;
    }

    @Override
    public void handleEntityEvent(
            final @EntityEvent.Value byte id
    ) {
        if (id == 15) {
            for (int i = 0; i < this.random.nextInt(35) + 10; i++) {
                this.level().addParticle(
                        ParticleTypes.WITCH,
                        this.getX() + this.random.nextGaussian() * 0.13F,
                        this.getBoundingBox().maxY
                                + 0.5
                                + this.random.nextGaussian() * 0.13F,
                        this.getZ() + this.random.nextGaussian() * 0.13F,
                        0.0,
                        0.0,
                        0.0
                );
            }
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    protected float getDamageAfterMagicAbsorb(
            final DamageSource damageSource,
            float damage
    ) {
        damage = super.getDamageAfterMagicAbsorb(
                damageSource,
                damage
        );

        if (damageSource.getEntity() == this) {
            damage = 0.0F;
        }

        if (damageSource.is(DamageTypeTags.WITCH_RESISTANT_TO)) {
            damage *= 0.15F;
        }

        return damage;
    }

    // Poison immunity
    @Override
    public boolean canBeAffected(MobEffectInstance effect) {
        return !effect.is(MobEffects.POISON)
                && super.canBeAffected(effect);
    }

    @Override
    public void performRangedAttack(
            final LivingEntity target,
            final float power
    ) {

        // Prevent normal attacks during the defensive sequence
        if (!this.isDrinkingPotion()
                && !this.defensivePotionSequenceActive) {

            Vec3 targetMovement = target.getDeltaMovement();

            double xd = target.getX() + targetMovement.x - this.getX();
            double yd = target.getEyeY() - 1.1F - this.getY();
            double zd = target.getZ() + targetMovement.z - this.getZ();

            double dist = Math.sqrt(xd * xd + zd * zd);

            Holder<Potion> potion = Potions.HARMING;
            MobEffectInstance customEffect = null;

            if (target instanceof Raider) {

                // Heal allied raiders
                if (target.getHealth() <= 4.0F) {
                    potion = Potions.HEALING;
                } else {
                    potion = Potions.REGENERATION;
                }

                this.setTarget(null);

            } else if (dist >= 8.0
                    && !target.hasEffect(MobEffects.SLOWNESS)) {

                potion = Potions.SLOWNESS;

            } else if (target.getHealth() >= 8.0F
                    && !target.hasEffect(MobEffects.POISON)) {

                potion = Potions.POISON;

            } else if (dist <= 3.0
                    && !target.hasEffect(MobEffects.WEAKNESS)
                    && this.random.nextFloat() < 0.25F) {

                potion = Potions.WEAKNESS;

            } else if (target instanceof Player && target.getHealth() >= 8.0F && !target.hasEffect(MobEffects.NAUSEA)) {
                potion = ModPotions.NAUSEA_POTION;
                customEffect = new MobEffectInstance(MobEffects.NAUSEA, 100); // 5 seconds
            }
            else if (target instanceof Player && target.getHealth() >= 8.0F && !target.hasEffect(MobEffects.BLINDNESS)) {
                potion = ModPotions.BLINDNESS_POTION;
                customEffect = new MobEffectInstance(MobEffects.BLINDNESS, 60); // 3 seconds
            }

            // Throw selected splash potion
            if (this.level() instanceof ServerLevel serverLevel) {
                ItemStack itemStack;

                if (customEffect != null) {
                    itemStack = new ItemStack(Items.SPLASH_POTION);
                    itemStack.set(
                            DataComponents.POTION_CONTENTS,
                            PotionContents.EMPTY.withEffectAdded(customEffect)
                    );
                } else {
                    itemStack = PotionContents.createItemStack(Items.SPLASH_POTION, potion);
                }

                Projectile.spawnProjectileUsingShoot(
                        ThrownSplashPotion::new,
                        serverLevel,
                        itemStack,
                        this,
                        xd,
                        yd + dist * 0.2,
                        zd,
                        dist <= 2.0 ? 0.45F : 0.75F,
                        this.rangedAttackUncertainty(serverLevel)
                );
            }

            if (!this.isSilent()) {
                this.level().playSound(
                        null,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        SoundEvents.WITCH_THROW,
                        this.getSoundSource(),
                        1.0F,
                        0.8F + this.random.nextFloat() * 0.4F
                );
            }
        }
    }

    @Override
    public float rangedAttackUncertainty(final Level level) {
        return 8.0F;
    }

    @Override
    public void applyRaidBuffs(
            final ServerLevel level,
            final int wave,
            final boolean isCaptain
    ) {
    }

    @Override
    public boolean canBeLeader() {
        return false;
    }
}
