package womp.tinfoilknight.ender_bundles.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import womp.tinfoilknight.ender_bundles.EnderBundles;

public class EnderBundlesItemModels extends ItemModelProvider {
    public EnderBundlesItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EnderBundles.MODID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
        this.basicItem(EnderBundles.ENDER_BUNDLE.get());
    }
}
