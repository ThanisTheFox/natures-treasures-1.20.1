package net.beetle.naturestreasures;

import net.beetle.naturestreasures.entity.ModEntities;
import net.beetle.naturestreasures.entity.custom.AntEntity;
import net.beetle.naturestreasures.item.ModItemGroups;
import net.beetle.naturestreasures.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NaturesTreasures implements ModInitializer {
	public static final String MOD_ID = "naturestreasures";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();

		ModEntities.registerModEntities();
		ModEntities.registerAttributes();

	}
}