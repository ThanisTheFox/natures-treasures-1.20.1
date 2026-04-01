package net.beetle.naturestreasures.entity.client;

import net.beetle.naturestreasures.NaturesTreasures;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer ANT =
            new EntityModelLayer(new Identifier(NaturesTreasures.MOD_ID,"ant"),"main");
    public static final EntityModelLayer WOODLANDDORBEETLE =
            new EntityModelLayer(new Identifier(NaturesTreasures.MOD_ID,"woodlanddoorbeetle"),"main");
}
