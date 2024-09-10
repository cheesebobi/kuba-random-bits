package com.kuba.random.bits;

import com.kuba.random.bits.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class KubaRandomBitsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ItemTagGenerator::new);
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(ModelGenerator::new);

	}


	private static class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {
		public ItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}


		private void addItemsToTag(TagKey<Item> tag, ItemConvertible... items) {
			FabricTagBuilder builder = getOrCreateTagBuilder(tag);
			for (int i = 0; i < items.length; i++) {
				builder.add(items[i].asItem());
			}
		}

		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {

		}
	}


	private static class ModelGenerator extends FabricModelProvider {
		private ModelGenerator(FabricDataOutput generator) {
			super(generator);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

		}

		public void registerCubeAllBlockModels(BlockStateModelGenerator blockStateModelGenerator, Block... blocks) {
			for (int i = 0; i < blocks.length; i++)
				blockStateModelGenerator.registerSimpleCubeAll(blocks[i]);
		}

		public void registerLeveledHorizontalOrientables(BlockStateModelGenerator blockStateModelGenerator, String path, Block... blocks) {
			Identifier id = new Identifier(KubaRandomBits.MOD_ID, "block/" + path + "/");

			for (int i = 0; i < blocks.length; i++) {
				Block block = blocks[i];
				TextureMap textureMap = new TextureMap()
						.put(TextureKey.TOP, id.withSuffixedPath("top_" + (i + 1)))
						.put(TextureKey.BOTTOM, id.withSuffixedPath("other"))
						.put(TextureKey.SIDE, id.withSuffixedPath("other"))
						.put(TextureKey.FRONT, id.withSuffixedPath("front"));
				Identifier identifier = Models.ORIENTABLE_WITH_BOTTOM.upload(block, textureMap, blockStateModelGenerator.modelCollector);
				blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, identifier)).coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
			}
		}


		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {

registerGeneratedModels( itemModelGenerator, ModItems.DIAMOND_GEAR, ModItems.WOOD_GEAR, ModItems.STONE_GEAR, ModItems.IRON_GEAR, ModItems.GOLD_GEAR, ModItems.URANIUM, ModItems.ATOMIC_DISASSEMBLER, ModItems.MEKANISM_FREERUNNERS, ModItems.ENDERIO_BLOCK_REINFORCED_OBSIDIAN, ModItems.BOTANIA_MANATABLET, ModItems.CHISEL_CONCRETE_ORANGE1, ModItems.CHISEL_FACTORY1, ModItems.CHISEL_REDSTONE, ModItems.THERMALFOUNDATION_MATERIAL, ModItems.THERMALFOUNDATION_TOOLHAMMER_PLATINUM, ModItems.THERMALFOUNDATION_TOOLSWORD_PLATINUM);

		}

		public void registerGeneratedModels(ItemModelGenerator itemModelGenerator, Item... items) {
			for (int i = 0; i < items.length; i++) {
				itemModelGenerator.register(items[i], Models.GENERATED);
			}
		}
		public void registerHandheldModels(ItemModelGenerator itemModelGenerator, Item... items) {
			for (int i = 0; i < items.length; i++) {
				itemModelGenerator.register(items[i], Models.HANDHELD);
			}
		}

	}



	private static class RecipeGenerator extends FabricRecipeProvider {
		private RecipeGenerator(FabricDataOutput generator) {
			super(generator);
		}


		@Override
		public void generate(Consumer<RecipeJsonProvider> exporter) {
			TagKey<Item> diamond = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "diamonds"));

			ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIAMOND_GEAR)
					.pattern(" D ")
					.pattern("DGD")
					.pattern(" D ")
					.input('D', Items.DIAMOND)
					.input('G', Items.STONE)
					.criterion("has_diamond", FabricRecipeProvider.conditionsFromTag(diamond))
					.offerTo(exporter, new Identifier(KubaRandomBits.MOD_ID, "diamond_gear"));

		}
	}
}
