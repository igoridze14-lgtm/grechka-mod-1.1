package net.grechka.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GreczkaMod implements ModInitializer {
    
    public static final String MOD_ID = "grechka";
    
    public static final EntityType<GrechkaEntity> GRECHKA = Registry.register(
        Registries.ENTITY_TYPE,
        new Identifier(MOD_ID, "grechka"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GrechkaEntity::new)
            .dimensions(EntityDimensions.fixed(0.6f, 1.9f))
            .build()
    );
    
    public static final SpawnEggItem GRECHKA_SPAWN_EGG = Registry.register(
        Registries.ITEM,
        new Identifier(MOD_ID, "grechka_spawn_egg"),
        new SpawnEggItem(GRECHKA, 0x8B4513, 0xD2B48C, new net.minecraft.item.Item.Settings())
    );

    @Override
    public void onInitialize() {
        // Мод инициализирован!
    }
}
