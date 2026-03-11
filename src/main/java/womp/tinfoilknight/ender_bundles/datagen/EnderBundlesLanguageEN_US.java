package womp.tinfoilknight.ender_bundles.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import womp.tinfoilknight.ender_bundles.EnderBundles;
import womp.tinfoilknight.ender_bundles.item.EnderBundleItem;

public class EnderBundlesLanguageEN_US extends LanguageProvider {
    public EnderBundlesLanguageEN_US(PackOutput output) {
        super(output, EnderBundles.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(womp.tinfoilknight.ender_bundles.EnderBundles.ENDER_BUNDLE.get(), "Ender Bundle");
        add(EnderBundleItem.playerComponent, "Player: %s");
    }
}
