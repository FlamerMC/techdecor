
package net.fc.inetcore.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class PhoneItem extends Item {
	public PhoneItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}