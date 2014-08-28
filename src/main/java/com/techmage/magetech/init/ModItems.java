package com.techmage.magetech.init;

import com.techmage.magetech.item.ItemCrystal;
import com.techmage.magetech.item.ItemIngot;
import com.techmage.magetech.item.ItemMageTech;
import com.techmage.magetech.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemMageTech crystal = new ItemCrystal();
    public static final ItemMageTech ingot = new ItemIngot();

    public static void init()
    {
        GameRegistry.registerItem(crystal, "crystal");
        GameRegistry.registerItem(ingot, "ingot");
    }
}

