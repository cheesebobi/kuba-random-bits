package com.kuba.random.bits.items;

import com.kuba.random.bits.KubaRandomBits;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item DIAMOND_GEAR = registerItemWithGroup("diamond_gear", new Item(new FabricItemSettings()));

    public static final Item WOOD_GEAR = registerItemWithGroup("wood_gear", new Item(new FabricItemSettings()));

    public static final Item STONE_GEAR = registerItemWithGroup("stone_gear", new Item(new FabricItemSettings()));

    public static final Item IRON_GEAR = registerItemWithGroup("iron_gear", new Item(new FabricItemSettings()));

    public static final Item GOLD_GEAR = registerItemWithGroup("gold_gear", new Item(new FabricItemSettings()));

    public static final Item URANIUM = registerItemWithGroup("uranium", new Item(new FabricItemSettings()));

    public static final Item ATOMIC_DISASSEMBLER = registerItemWithGroup("atomic_disassembler", new Item(new FabricItemSettings()));
    public static final Item MEKANISM_FREERUNNERS = registerItemWithGroup("mekanism_freerunners", new Item(new FabricItemSettings()));
    public static final Item ENDERIO_BLOCK_REINFORCED_OBSIDIAN = registerItemWithGroup("enderio_block_reinforced_obsidian", new Item(new FabricItemSettings()));
    public static final Item BOTANIA_MANATABLET = registerItemWithGroup("botania_manatablet", new Item(new FabricItemSettings()));
    public static final Item CHISEL_CONCRETE_ORANGE1 = registerItemWithGroup("chisel_concrete_orange1", new Item(new FabricItemSettings()));
    public static final Item CHISEL_FACTORY1 = registerItemWithGroup("chisel_factory1", new Item(new FabricItemSettings()));
    public static final Item CHISEL_REDSTONE = registerItemWithGroup("chisel_redstone", new Item(new FabricItemSettings()));
    public static final Item THERMALFOUNDATION_MATERIAL = registerItemWithGroup("thermalfoundation_material", new Item(new FabricItemSettings()));
    public static final Item THERMALFOUNDATION_TOOLHAMMER_PLATINUM = registerItemWithGroup("thermalfoundation_toolhammer_platinum", new Item(new FabricItemSettings()));
    public static final Item THERMALFOUNDATION_TOOLSWORD_PLATINUM = registerItemWithGroup("thermalfoundation_toolsword_platinum", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        Registry.register(Registries.ITEM, new Identifier(KubaRandomBits.MOD_ID, name), item);
        return item;
    }

    private static Item registerGroup(Item item) {
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.KUBA_RANDOM_BITS).register(entries -> entries.add(item));
        return item;
    }

    private static Item registerItemWithGroup(String name, Item item) {
        registerItem(name, item);
        registerGroup(item);
        return item;
    }

    public static void registerModItems() {
        registerGroup(DIAMOND_GEAR);
    }
}
