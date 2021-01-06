package io.github.illegalbeagle123.fishen;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Items;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.util.Identifier;

public class Fishen implements ModInitializer {

    private static final Identifier CHICKEN_LOOT = new Identifier("minecraft", "entities/chicken");

    @Override
    public void onInitialize() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (CHICKEN_LOOT.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(UniformLootTableRange.between(0,1))
                        .withEntry(ItemEntry.builder(Items.EGG).build())
                        .withFunction(LootingEnchantLootFunction.builder(UniformLootTableRange.between(0,2)).build());
                supplier.withPool(poolBuilder.build());
            }
        });
    }
}
