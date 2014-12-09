package com.techmage.magetech.creativetab;

import com.techmage.magetech.init.ModItems;
import com.techmage.magetech.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMageTech
{
    public static final CreativeTabs MAGETECH_TECH_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase() + " - Tech")
    {
        @Override
        public Item getTabIconItem() { return ModItems.ic; }
    };

    public static final CreativeTabs MAGETECH_MAGIC_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase() + " - Magic")
    {
        @Override
        public Item getTabIconItem() { return ModItems.crystal; }
    };
}
