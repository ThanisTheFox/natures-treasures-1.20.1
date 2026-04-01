package net.beetle.naturestreasures.entity.client;

import net.beetle.naturestreasures.NaturesTreasures;
import net.beetle.naturestreasures.entity.custom.AntEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class AntRenderer extends MobEntityRenderer<AntEntity, AntModel<AntEntity>> {
    private static final Identifier TEXTURE = new Identifier(NaturesTreasures.MOD_ID,"textures/entity/ant.png");

    public AntRenderer(EntityRendererFactory.Context context) {
        super(context, new AntModel<>(context.getPart(ModModelLayers.ANT)),0.6f);
        //0.6f sind die Größe des Schattens
    }

    @Override
    public Identifier getTexture(AntEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(AntEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()) {
           matrixStack.scale(0.5f,0.5f,0.5f);
           //Größe Baby im Verhältnis (0,5 = halb so groß)
        } else {
            matrixStack.scale(1f,1f,1f);
            //Größe normal im Verhältnis ig
        }



        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
