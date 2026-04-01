package net.beetle.naturestreasures.entity.client;

import net.beetle.naturestreasures.entity.animation.ModAnimations;
import net.beetle.naturestreasures.entity.custom.WoodlandDorBeetleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class WoodlandDorBeetleModel<T extends WoodlandDorBeetleEntity>  extends SinglePartEntityModel<T> {
	private final ModelPart woodlanddorbeetle;
	private final ModelPart head;

	public WoodlandDorBeetleModel(ModelPart root) {
		this.woodlanddorbeetle = root.getChild("woodlanddorbeetle");
		this.head = woodlanddorbeetle.getChild("head");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData woodlanddorbeetle = modelPartData.addChild("woodlanddorbeetle", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 18.5F, -12.0F));

		ModelPartData head = woodlanddorbeetle.addChild("head", ModelPartBuilder.create().uv(20, 23).cuboid(-2.0F, -0.7592F, -4.9389F, 3.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 7.0F, 0.3491F, 0.0F, 0.0F));

		ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(0, 28).cuboid(2.0F, -1.0F, -2.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -0.0092F, -4.9389F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r2 = head.addChild("cube_r2", ModelPartBuilder.create().uv(16, 29).cuboid(-2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -0.7592F, -2.6889F, 0.0F, 0.0F, -0.5236F));

		ModelPartData cube_r3 = head.addChild("cube_r3", ModelPartBuilder.create().uv(8, 28).cuboid(0.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.7592F, -2.6889F, 0.0F, 0.0F, 0.5236F));

		ModelPartData antler = head.addChild("antler", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, -0.0092F, -4.6889F));

		ModelPartData antlerr = antler.addChild("antlerr", ModelPartBuilder.create().uv(32, 9).cuboid(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -0.75F, 3.5F, 0.0F, -0.2182F, -0.2182F));

		ModelPartData cube_r4 = antlerr.addChild("cube_r4", ModelPartBuilder.create().uv(16, 28).cuboid(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, -1.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData antlerl = antler.addChild("antlerl", ModelPartBuilder.create().uv(16, 32).cuboid(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -0.75F, 3.5F, 0.0F, 0.2182F, 0.2182F));

		ModelPartData cube_r5 = antlerl.addChild("cube_r5", ModelPartBuilder.create().uv(18, 28).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, -1.0F, 0.0F, 0.0F, 0.3927F));

		ModelPartData body = woodlanddorbeetle.addChild("body", ModelPartBuilder.create().uv(0, 12).cuboid(-3.0F, -0.5F, 8.0F, 7.0F, 3.0F, 8.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-2.5F, 0.0F, 6.5F, 6.0F, 2.0F, 10.0F, new Dilation(0.0F))
				.uv(0, 23).cuboid(-2.5F, -1.0F, 7.75F, 6.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData legs = woodlanddorbeetle.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, 4.0F, 2.0F));

		ModelPartData legs_left = legs.addChild("legs_left", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 1.5F, 10.0F));

		ModelPartData m = legs_left.addChild("m", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, -4.0F, -0.75F));

		ModelPartData cube_r6 = m.addChild("cube_r6", ModelPartBuilder.create().uv(24, 29).cuboid(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 2.25F, 0.5F, 0.0F, 0.0F, -0.6981F));

		ModelPartData cube_r7 = m.addChild("cube_r7", ModelPartBuilder.create().uv(30, 20).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 4.0F, 0.5F, 0.0F, 0.0F, -0.5236F));

		ModelPartData f = legs_left.addChild("f", ModelPartBuilder.create(), ModelTransform.of(4.5F, -3.75F, -3.5F, 0.0F, 0.2182F, 0.0F));

		ModelPartData cube_r8 = f.addChild("cube_r8", ModelPartBuilder.create().uv(28, 29).cuboid(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.5545F, 2.0F, 0.4369F, 0.0F, 0.0F, -0.6981F));

		ModelPartData cube_r9 = f.addChild("cube_r9", ModelPartBuilder.create().uv(8, 31).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5545F, 3.75F, 0.4369F, 0.0F, 0.0F, -0.5236F));

		ModelPartData b = legs_left.addChild("b", ModelPartBuilder.create(), ModelTransform.of(4.25F, -4.0F, 2.5F, 0.0F, -0.2182F, 0.0F));

		ModelPartData cube_r10 = b.addChild("cube_r10", ModelPartBuilder.create().uv(30, 12).cuboid(0.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.5545F, 2.25F, -0.4369F, 0.0F, 0.0F, -0.6981F));

		ModelPartData cube_r11 = b.addChild("cube_r11", ModelPartBuilder.create().uv(12, 31).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5545F, 4.0F, -0.4369F, 0.0F, 0.0F, -0.5236F));

		ModelPartData legs_right = legs.addChild("legs_right", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 1.5F, 10.0F));

		ModelPartData m2 = legs_right.addChild("m2", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, -4.0F, -0.75F));

		ModelPartData cube_r12 = m2.addChild("cube_r12", ModelPartBuilder.create().uv(30, 16).cuboid(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 2.25F, 0.5F, 0.0F, 0.0F, 0.6981F));

		ModelPartData cube_r13 = m2.addChild("cube_r13", ModelPartBuilder.create().uv(32, 0).cuboid(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 4.0F, 0.5F, 0.0F, 0.0F, 0.5236F));

		ModelPartData f2 = legs_right.addChild("f2", ModelPartBuilder.create(), ModelTransform.of(-4.5F, -4.0F, -3.5F, 0.0F, -0.2182F, 0.0F));

		ModelPartData cube_r14 = f2.addChild("cube_r14", ModelPartBuilder.create().uv(0, 31).cuboid(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.5545F, 2.25F, 0.4369F, 0.0F, 0.0F, 0.6981F));

		ModelPartData cube_r15 = f2.addChild("cube_r15", ModelPartBuilder.create().uv(32, 3).cuboid(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5545F, 4.0F, 0.4369F, 0.0F, 0.0F, 0.5236F));

		ModelPartData b2 = legs_right.addChild("b2", ModelPartBuilder.create(), ModelTransform.of(-5.25F, -4.0F, 2.75F, 0.0F, 0.2182F, 0.0F));

		ModelPartData cube_r16 = b2.addChild("cube_r16", ModelPartBuilder.create().uv(4, 31).cuboid(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5241F, 2.25F, -0.4645F, 0.0F, 0.0F, 0.6981F));

		ModelPartData cube_r17 = b2.addChild("cube_r17", ModelPartBuilder.create().uv(32, 6).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.5241F, 4.0F, -0.4645F, 0.0F, 0.0F, 0.5236F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.WOODLANDDORBEETLE_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.IdleAnimationState, ModAnimations.WOODLANDDORBEETLE_IDLE, ageInTicks, 1f);
	}


		private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
		headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

		this.head.yaw = headYaw * 0.017453282F;
		this.head.pitch = headPitch * 0.017453282F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		woodlanddorbeetle.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return woodlanddorbeetle;
	}


}

