package net.beetle.naturestreasures;

import net.beetle.naturestreasures.entity.ModEntities;
import net.beetle.naturestreasures.entity.custom.AntEntity;
import net.beetle.naturestreasures.entity.custom.WoodlandDorBeetleEntity;
import net.beetle.naturestreasures.gui.ModScreenHandlers;
import net.beetle.naturestreasures.item.ModItemGroups;
import net.beetle.naturestreasures.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.beetle.naturestreasures.entity.ModEntities.ANT;
import static net.beetle.naturestreasures.entity.ModEntities.WOODLANDDORBEETLE;

public class NaturesTreasures implements ModInitializer {
	public static final String MOD_ID = "naturestreasures";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		System.out.println("=== NaturesTreasures: onInitialize called ===");
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();

		ModEntities.registerModEntities();
		FabricDefaultAttributeRegistry.register(ANT, AntEntity.createAntAttributes());
		FabricDefaultAttributeRegistry.register(WOODLANDDORBEETLE, WoodlandDorBeetleEntity.createWoodlandDorBeetleAttributes());

		ModScreenHandlers.registerScreenHandlers(); // Register the handler type
		System.out.println("=== NaturesTreasures: onInitialize finished ===");
	}
}