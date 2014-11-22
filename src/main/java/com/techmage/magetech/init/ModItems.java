package com.techmage.magetech.init;

import com.techmage.magetech.item.ItemCrystal;
import com.techmage.magetech.item.ItemIngot;
import com.techmage.magetech.item.ItemMageTech;
import com.techmage.magetech.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemMageTech crystal = new ItemCrystal();
    public static final ItemMageTech ingot = new ItemIngot();

    public static void init()
    {

        OreDictionary.registerOre("ingotLead", new ItemStack(ingot, 1, 0));
        OreDictionary.registerOre("ingotSilver", new ItemStack(ingot, 1, 1));

        GameRegistry.registerItem(crystal, "crystal");
        GameRegistry.registerItem(ingot, "ingot");
    }
}

