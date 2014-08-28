package com.techmage.magetech.creativetab;

import com.techmage.magetech.init.ModItems;
import com.techmage.magetech.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMageTech
{
    public static final CreativeTabs MAGETECH_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem() { return ModItems.crystal; }
    };
}
