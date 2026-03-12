package womp.tinfoilknight.ender_bundles.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import womp.tinfoilknight.ender_bundles.EnderBundles;
import womp.tinfoilknight.ender_bundles.components.ItemPlayer;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class EnderBundleItem extends Item {
    private final Component containerTitle;
    public static String playerComponent = "item." + EnderBundles.MODID + ".ender_bundle.tooltip";
    public EnderBundleItem(Properties properties) {
        super(properties);
        this.containerTitle = getName();
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.has(EnderBundles.ITEM_PLAYER.get())){
            stack.set(EnderBundles.ITEM_PLAYER.get(), new ItemPlayer(player.getUUID()));
        }
        UUID playerUUID = stack.get(EnderBundles.ITEM_PLAYER.get()).player();
        Player currentPlayer = level.getPlayerByUUID(playerUUID);
        if (currentPlayer != null){
            PlayerEnderChestContainer playerenderchestcontainer = currentPlayer.getEnderChestInventory();
            player.openMenu(
                    new SimpleMenuProvider(
                            (var1, inventory, instPlayer) -> ChestMenu.threeRows(var1, inventory, playerenderchestcontainer), containerTitle
                    )
            );
        }
        player.awardStat(Stats.OPEN_ENDERCHEST);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        String playerText;
        if (stack.has(EnderBundles.ITEM_PLAYER.get())) {
            playerText = context.level().getPlayerByUUID(stack.get(EnderBundles.ITEM_PLAYER.get()).player()).getName().getString();
        } else {
            playerText = "None";
        }
        tooltipComponents.add(Component.translatable(playerComponent, playerText).withStyle(ChatFormatting.DARK_PURPLE));
    }
}
