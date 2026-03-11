package womp.tinfoilknight.ender_bundles;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import womp.tinfoilknight.ender_bundles.components.ItemPlayer;
import womp.tinfoilknight.ender_bundles.datagen.EnderBundlesItemModels;
import womp.tinfoilknight.ender_bundles.datagen.EnderBundlesLanguageEN_US;
import womp.tinfoilknight.ender_bundles.datagen.EnderBundlesRecipes;
import womp.tinfoilknight.ender_bundles.item.EnderBundleItem;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Mod(EnderBundles.MODID)
public class EnderBundles
{
    public static final String MODID = "ender_bundles";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);

    public static final DeferredItem<@NotNull Item> ENDER_BUNDLE = ITEMS.registerItem("ender_bundle", EnderBundleItem::new);

    public static final Supplier<DataComponentType<ItemPlayer>> ITEM_PLAYER = DATA_COMPONENTS.registerComponentType(
            "item_player",
            builder -> builder
                    .persistent(ItemPlayer.CODEC)
                    .networkSynchronized(ItemPlayer.STREAM_CODEC)
    );

    public EnderBundles(IEventBus modEventBus, ModContainer modContainer)
    {
        ITEMS.register(modEventBus);
        DATA_COMPONENTS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
            event.insertAfter(Items.PINK_BUNDLE.getDefaultInstance(), ENDER_BUNDLE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }


    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
        @SubscribeEvent
        public static void gatherData(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

            generator.addProvider(
                    event.includeClient(),
                    new EnderBundlesItemModels(output, existingFileHelper)
            );
            generator.addProvider(
                    event.includeClient(),
                    new EnderBundlesLanguageEN_US(output)
            );
            generator.addProvider(
                    event.includeClient(),
                    new EnderBundlesRecipes.Runner(output, lookupProvider)
            );
        }
    }
}
