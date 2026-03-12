package womp.tinfoilknight.ender_bundles.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;
import womp.tinfoilknight.ender_bundles.EnderBundles;

public class EnderBundlesItemBlockModels extends ModelProvider {
    public EnderBundlesItemBlockModels(PackOutput output) {
        super(output, EnderBundles.MODID);
    }
    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(EnderBundles.ENDER_BUNDLE.get(), ModelTemplates.FLAT_ITEM);
    }
}
