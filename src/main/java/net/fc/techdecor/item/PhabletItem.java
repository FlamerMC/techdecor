
package net.fc.techdecor.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class PhabletItem extends Item {
	public PhabletItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
