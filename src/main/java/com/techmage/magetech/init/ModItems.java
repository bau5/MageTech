package com.techmage.magetech.init;

import com.techmage.magetech.item.*;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemMageTech crystal = new ItemCrystal();
    public static final ItemMageTech ingot = new ItemIngot();
    public static final ItemMageTech siliconOre = new ItemSiliconOre();
    public static final ItemMageTech silicon = new ItemSilicon();
    public static final ItemMageTech resin = new ItemResin();
    public static final ItemMageTech plastic = new ItemPlastic();
    public static final ItemMageTech glassFibre = new ItemGlassFibre();
    public static final ItemMageTech glassFibreMat = new ItemGlassFibreMat();
    public static final ItemMageTech wireIron = new ItemWireIron();
    public static final ItemMageTech ic = new ItemIC();
    public static final ItemMageTech capacitor = new ItemCapacitor();
    public static final ItemMageTech transistor = new ItemTransistor();

    public static void init()
    {

        OreDictionary.registerOre("ingotLead", new ItemStack(ingot, 1, 0));
        OreDictionary.registerOre("ingotSilver", new ItemStack(ingot, 1, 1));
        OreDictionary.registerOre("ingotCopper", new ItemStack(ingot, 1, 2));

        GameRegistry.registerItem(crystal, Names.Items.CRYSTAL);
        GameRegistry.registerItem(ingot, Names.Items.INGOT);
        GameRegistry.registerItem(siliconOre, Names.Items.SILICONORE);
        GameRegistry.registerItem(silicon, Names.Items.SILICON);
        GameRegistry.registerItem(resin, Names.Items.RESIN);
        GameRegistry.registerItem(plastic, Names.Items.PLASTIC);
        GameRegistry.registerItem(glassFibre, Names.Items.GLASSFIBRE);
        GameRegistry.registerItem(glassFibreMat, Names.Items.GLASSFIBREMAT);
        GameRegistry.registerItem(wireIron, Names.Items.WIREIRON);
        GameRegistry.registerItem(ic, Names.Items.IC);
        GameRegistry.registerItem(capacitor, Names.Items.CAPACITOR);
        GameRegistry.registerItem(transistor, Names.Items.TRANSISTOR);

    }
}

