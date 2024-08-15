package com.kuba.random.bits.items;

import com.kuba.random.bits.KubaRandomBits;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static ItemGroup KUBA_RANDOM_BITS = FabricItemGroup.builder(
                    new Identifier(KubaRandomBits.MOD_ID, "kuba-random-bits"))
            .displayName(Text.translatable("itemgroup.kuba-random-bits"))
            .icon(() -> new ItemStack(ModItems.DIAMOND_GEAR)).build();

    public static void registerItemGroups() {

    }
}
