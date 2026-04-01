package net.beetle.naturestreasures.entity.client;

import net.beetle.naturestreasures.entity.animation.ModAnimations;
import net.beetle.naturestreasures.entity.custom.AntEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class AntModel<T extends AntEntity>  extends SinglePartEntityModel<T> {
	private final ModelPart ant;
	private final ModelPart head;

	public AntModel(ModelPart root) {
		this.ant = root.getChild("ant");
		this.head = ant.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData ant = modelPartData.addChild("ant", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 23.0F, 0.0F));

		ModelPartData body = ant.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData mid = body.addChild("mid", ModelPartBuilder.create().uv(0, 19).cuboid(-2.6F, -8.9F, 0.0F, 5.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData butt = body.addChild("butt", ModelPartBuilder.create().uv(0, 0).cuboid(-4.5F, -5.6F, -1.0F, 8.0F, 8.0F, 11.0F, new Dilation(0.0F))
				.uv(26, 30).cuboid(-3.5F, -4.6F, 10.0F, 6.0F, 6.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 32).cuboid(-3.5F, -4.6F, -2.0F, 6.0F, 6.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, -6.6F, 9.9F));

		ModelPartData head = ant.addChild("head", ModelPartBuilder.create().uv(26, 19).cuboid(-3.5F, -10.0F, -5.0F, 7.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

		ModelPartData antlerr = head.addChild("antlerr", ModelPartBuilder.create(), ModelTransform.pivot(-1.9F, -9.4F, -5.0F));

		ModelPartData cube_r1 = antlerr.addChild("cube_r1", ModelPartBuilder.create().uv(12, 43).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -1.6F, 1.0F, 0.9163F, 0.7854F, 0.0F));

		ModelPartData cube_r2 = antlerr.addChild("cube_r2", ModelPartBuilder.create().uv(42, 36).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.4F, 1.0F, 0.0F, 0.7854F, 0.0F));

		ModelPartData cube_r3 = antlerr.addChild("cube_r3", ModelPartBuilder.create().uv(16, 43).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.1F, -2.8F, -0.1F, 1.8326F, 0.7854F, 0.0F));

		ModelPartData antlerl = head.addChild("antlerl", ModelPartBuilder.create(), ModelTransform.pivot(1.9F, -9.4F, -5.0F));

		ModelPartData cube_r4 = antlerl.addChild("cube_r4", ModelPartBuilder.create().uv(42, 10).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.4F, 1.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData cube_r5 = antlerl.addChild("cube_r5", ModelPartBuilder.create().uv(22, 42).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -1.6F, 1.0F, 0.9163F, -0.7854F, 0.0F));

		ModelPartData cube_r6 = antlerl.addChild("cube_r6", ModelPartBuilder.create().uv(32, 43).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.1F, -2.8F, -0.1F, 1.8326F, -0.7854F, 0.0F));

		ModelPartData mandibles = head.addChild("mandibles", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, -7.0F, 0.0F));

		ModelPartData right = mandibles.addChild("right", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 1.0F, -5.0F));

		ModelPartData cube_r7 = right.addChild("cube_r7", ModelPartBuilder.create().uv(22, 39).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.3F, 1.5F, -1.0F, 0.0F, -0.6109F, 0.0F));

		ModelPartData left = mandibles.addChild("left", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, 1.0F, -4.0F));

		ModelPartData cube_r8 = left.addChild("cube_r8", ModelPartBuilder.create().uv(38, 15).cuboid(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.2297F, 1.5F, -1.9483F, 0.0F, 0.6109F, 0.0F));

		ModelPartData eyes = head.addChild("eyes", ModelPartBuilder.create().uv(38, 2).cuboid(3.0F, -8.0F, -4.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(38, 6).cuboid(-4.0F, -8.0F, -4.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData legs = ant.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData l = legs.addChild("l", ModelPartBuilder.create().uv(40, 32).cuboid(6.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.9F, 0.0F, 1.2F, 0.0F, 0.6981F, 0.0F));

		ModelPartData cube_r9 = l.addChild("cube_r9", ModelPartBuilder.create().uv(18, 38).cuboid(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(6.1F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		ModelPartData cube_r10 = l.addChild("cube_r10", ModelPartBuilder.create().uv(34, 37).cuboid(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -3.5F, 0.0F, 0.0F, 0.0F, 0.0087F));

		ModelPartData r = legs.addChild("r", ModelPartBuilder.create().uv(40, 34).cuboid(6.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.1F, 0.0F, 1.2F, 0.0F, 2.2689F, 0.0F));

		ModelPartData cube_r11 = r.addChild("cube_r11", ModelPartBuilder.create().uv(8, 39).cuboid(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(6.1F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		ModelPartData cube_r12 = r.addChild("cube_r12", ModelPartBuilder.create().uv(38, 0).cuboid(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -3.5F, 0.0F, 0.0F, 0.0F, 0.0087F));

		ModelPartData l2 = legs.addChild("l2", ModelPartBuilder.create().uv(38, 41).cuboid(7.1F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 0.0F, 3.2F));

		ModelPartData cube_r13 = l2.addChild("cube_r13", ModelPartBuilder.create().uv(0, 39).cuboid(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(7.1F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		ModelPartData cube_r14 = l2.addChild("cube_r14", ModelPartBuilder.create().uv(14, 32).cuboid(-2.0F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -3.5F, 0.0F, 0.0F, 0.0F, 0.0087F));

		ModelPartData r2 = legs.addChild("r2", ModelPartBuilder.create().uv(40, 30).cuboid(7.1F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 0.0F, 2.2F, 0.0F, 3.1416F, 0.0F));

		ModelPartData cube_r15 = r2.addChild("cube_r15", ModelPartBuilder.create().uv(28, 39).cuboid(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(7.1F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		ModelPartData cube_r16 = r2.addChild("cube_r16", ModelPartBuilder.create().uv(14, 36).cuboid(-2.0F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -3.5F, 0.0F, 0.0F, 0.0F, 0.0087F));

		ModelPartData l3 = legs.addChild("l3", ModelPartBuilder.create().uv(32, 41).cuboid(6.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, 0.0F, 6.2F, 0.0F, -0.7418F, 0.0F));

		ModelPartData cube_r17 = l3.addChild("cube_r17", ModelPartBuilder.create().uv(4, 39).cuboid(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(6.1F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		ModelPartData cube_r18 = l3.addChild("cube_r18", ModelPartBuilder.create().uv(14, 34).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -3.5F, 0.0F, 0.0F, 0.0F, 0.0087F));

		ModelPartData r3 = legs.addChild("r3", ModelPartBuilder.create().uv(32, 39).cuboid(6.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, 0.0F, 5.5F, 0.0F, -2.3126F, 0.0F));

		ModelPartData cube_r19 = r3.addChild("cube_r19", ModelPartBuilder.create().uv(38, 10).cuboid(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(6.1F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		ModelPartData cube_r20 = r3.addChild("cube_r20", ModelPartBuilder.create().uv(24, 37).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -3.5F, 0.0F, 0.0F, 0.0F, 0.0087F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(AntEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.ANT_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.IdleAnimationState, ModAnimations.ANT_IDLE, ageInTicks, 1f);

	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw,-30.0f, 30.0f);
		headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

		this.head.yaw = headYaw * 0.017453282F;
		this.head.pitch = headPitch * 0.017453282F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		ant.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return ant;
	}
}