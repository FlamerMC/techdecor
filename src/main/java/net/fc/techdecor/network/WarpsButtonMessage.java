
package net.fc.techdecor.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.fc.techdecor.world.inventory.WarpsMenu;
import net.fc.techdecor.procedures.HomeButtonProcedure;
import net.fc.techdecor.TechdecorMod;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record WarpsButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<WarpsButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(TechdecorMod.MODID, "warps_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, WarpsButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, WarpsButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new WarpsButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<WarpsButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final WarpsButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				Player entity = context.player();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = WarpsMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			HomeButtonProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		TechdecorMod.addNetworkMessage(WarpsButtonMessage.TYPE, WarpsButtonMessage.STREAM_CODEC, WarpsButtonMessage::handleData);
	}
}
