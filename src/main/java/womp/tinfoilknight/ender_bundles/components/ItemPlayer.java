package womp.tinfoilknight.ender_bundles.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.UUIDUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.util.UUID;

public record ItemPlayer(UUID player) {
    public static final Codec<ItemPlayer> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    UUIDUtil.CODEC.fieldOf("player").forGetter(ItemPlayer::player)
            ).apply(instance, ItemPlayer::new)
    );
    public static final StreamCodec<ByteBuf, ItemPlayer> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, ItemPlayer::player,
            ItemPlayer::new
    );
}
