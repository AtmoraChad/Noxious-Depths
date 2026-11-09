package net.atmorachad.noxious.entity.client.brimstone_witch;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.VillagerLikeModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.WitchRenderState;
import net.minecraft.util.Mth;

public class BrimstoneWitchModel extends EntityModel<WitchRenderState> implements VillagerLikeModel<WitchRenderState>, HeadedModel {
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart headwear;
    private final ModelPart hat2;
    private final ModelPart hat3;
    private final ModelPart hat4;
    private final ModelPart headwear2;
    private final ModelPart mole;
    private final ModelPart body;
    private final ModelPart bodywear;
    private final ModelPart arms;
    private final ModelPart mirrored;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public BrimstoneWitchModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.nose = this.head.getChild("nose");
        this.headwear = this.head.getChild("headwear");
        this.hat2 = this.headwear.getChild("hat2");
        this.hat3 = this.hat2.getChild("hat3");
        this.hat4 = this.hat3.getChild("hat4");
        this.headwear2 = root.getChild("headwear2");
        this.mole = this.nose.getChild("mole");
        this.body = root.getChild("body");
        this.bodywear = root.getChild("bodywear");
        this.arms = root.getChild("arms");
        this.mirrored = this.arms.getChild("mirrored");
        this.left_leg = root.getChild("left_leg");
        this.right_leg = root.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition headwear = head.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(0, 64).addBox(0.0F, 0.0F, 0.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(1.0F, 0.05F, 1.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.51F)), PartPose.offset(-5.0F, -10.05F, -5.0F));

        PartDefinition hat2 = headwear.addOrReplaceChild("hat2", CubeListBuilder.create().texOffs(0, 76).addBox(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.0524F, 0.0F, 0.0262F));

        PartDefinition hat3 = hat2.addOrReplaceChild("hat3", CubeListBuilder.create().texOffs(0, 87).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.1047F, 0.0F, 0.0524F));

        PartDefinition hat4 = hat3.addOrReplaceChild("hat4", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(1.75F, -2.0F, 2.0F, -0.2094F, 0.0F, 0.1047F));

        PartDefinition headwear2 = partdefinition.addOrReplaceChild("headwear2", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition mole = nose.addOrReplaceChild("mole", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 3.0F, -6.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bodywear = partdefinition.addOrReplaceChild("bodywear", CubeListBuilder.create().texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 20.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition arms = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.95F, -1.05F, -0.7505F, 0.0F, 0.0F));

        PartDefinition mirrored = arms.addOrReplaceChild("mirrored", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(4.0F, -23.05F, -3.05F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 21.05F, 1.05F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 128);
    }

    public ModelPart getNose() {
        return this.nose;
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

    @Override
    public void setupAnim(WitchRenderState state) {
        super.setupAnim(state);

        this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
        this.head.xRot = state.xRot * (float) (Math.PI / 180.0);

        this.right_leg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F)
                * 1.4F * state.walkAnimationSpeed * 0.5F;

        this.left_leg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + (float) Math.PI)
                * 1.4F * state.walkAnimationSpeed * 0.5F;

        float speed = 0.01F * (state.entityId % 10);

        this.nose.xRot = Mth.sin(state.ageInTicks * speed)
                * 4.5F * (float) (Math.PI / 180.0);

        this.nose.zRot = Mth.cos(state.ageInTicks * speed)
                * 2.5F * (float) (Math.PI / 180.0);

        if (state.isHoldingItem) {
            this.nose.setPos(0.0F, 1.0F, -1.5F);
            this.nose.xRot = -0.9F;
        }
    }

    @Override
    public void translateToArms(WitchRenderState state, PoseStack outputPoseStack) {
        this.root.translateAndRotate(outputPoseStack);
        this.arms.translateAndRotate(outputPoseStack);
    }
}
