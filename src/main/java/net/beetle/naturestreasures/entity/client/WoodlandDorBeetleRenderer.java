package net.beetle.naturestreasures.entity.client;

import net.beetle.naturestreasures.NaturesTreasures;
import net.beetle.naturestreasures.entity.custom.WoodlandDorBeetleEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WoodlandDorBeetleRenderer extends MobEntityRenderer<WoodlandDorBeetleEntity, WoodlandDorBeetleModel<WoodlandDorBeetleEntity>> {
    private static final Identifier TEXTURE = new Identifier(NaturesTreasures.MOD_ID,"textures/entity/woodlanddorbeetle.png");

    public WoodlandDorBeetleRenderer(EntityRendererFactory.Context context) {
        super(context, new WoodlandDorBeetleModel<>(context.getPart(ModModelLayers.WOODLANDDORBEETLE)),0.4f);
        //0.6f sind die Größe des Schattens
    }

    @Override
    public Identifier getTexture(WoodlandDorBeetleEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(WoodlandDorBeetleEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()) {
            matrixStack.scale(0.25f,0.25f,0.25f);
            //Größe Baby im Verhältnis (0,5 = halb so groß)
        } else {
            matrixStack.scale(0.4f,0.4f,0.4f);
            //Größe normal im Verhältnis ig
        }



        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }


}
