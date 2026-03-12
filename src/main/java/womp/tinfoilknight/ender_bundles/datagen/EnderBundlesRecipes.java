package womp.tinfoilknight.ender_bundles.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import womp.tinfoilknight.ender_bundles.EnderBundles;

import java.util.concurrent.CompletableFuture;

public class EnderBundlesRecipes extends RecipeProvider {

    protected EnderBundlesRecipes(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.MISC, EnderBundles.ENDER_BUNDLE.get(), 1).pattern(" E ").pattern("OCO").pattern(" B ").define('E', Items.ENDER_EYE).define('O', Items.OBSIDIAN).define('C', Items.ENDER_CHEST).define('B', ItemTags.BUNDLES).unlockedBy("has_eye_of_ender", has(Items.ENDER_EYE)).unlockedBy("has_obsidian", has(Items.OBSIDIAN)).unlockedBy("has_ender_chest", has(Items.ENDER_CHEST)).unlockedBy("has_bundles", has(ItemTags.BUNDLES)).save(output);
        shapeless(RecipeCategory.MISC, EnderBundles.ENDER_BUNDLE.get(), 1).requires(EnderBundles.ENDER_BUNDLE).requires(Items.ENDER_EYE).unlockedBy("has_ender_bundle", has(EnderBundles.ENDER_BUNDLE)).save(output, EnderBundles.MODID + ":ender_bundle_clear");
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider, @NotNull RecipeOutput output) {
            return new EnderBundlesRecipes(provider, output);
        }

        @Override
        public @NotNull String getName() {
            return "Recipes - " + EnderBundles.MODID;
        }
    }
}