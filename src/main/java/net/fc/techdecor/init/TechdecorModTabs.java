
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.fc.techdecor.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import net.fc.techdecor.TechdecorMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class TechdecorModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TechdecorMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {

			tabData.accept(TechdecorModItems.LAPTOP_SPAWN_EGG.get());
			tabData.accept(TechdecorModItems.IPC_SPAWN_EGG.get());
			tabData.accept(TechdecorModItems.MONITOR_SPAWN_EGG.get());

		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {

			tabData.accept(TechdecorModItems.PHONE.get());
			tabData.accept(TechdecorModItems.PHABLET.get());

		}
	}
}
