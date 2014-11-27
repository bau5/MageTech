package com.techmage.magetech.init;

import com.techmage.magetech.item.*;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Reference;
import com.techmage.magetech.reference.Textures;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemMageTech crystal = new ItemCrystal();
    public static final ItemMageTech ingot = new ItemIngot();
    public static final Item siliconOre = new ItemMageTech().setUnlocalizedName(Names.Items.SILICONORE).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.SILICONORE);
    public static final Item silicon = new ItemMageTech().setUnlocalizedName(Names.Items.SILICON).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.SILICON);
    public static final Item resin = new ItemMageTech().setUnlocalizedName(Names.Items.RESIN).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.RESIN);
    public static final Item plastic = new ItemMageTech().setUnlocalizedName(Names.Items.PLASTIC).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.PLASTIC);
    public static final Item glassFibre = new ItemMageTech().setUnlocalizedName(Names.Items.GLASSFIBRE).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.GLASSFIBRE);
    public static final Item glassFibreMat = new ItemMageTech().setUnlocalizedName(Names.Items.GLASSFIBREMAT).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.GLASSFIBREMAT);
    public static final Item wireIron = new ItemMageTech().setUnlocalizedName(Names.Items.WIREIRON).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.WIREIRON);
    public static final Item ic = new ItemMageTech().setUnlocalizedName(Names.Items.IC).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.IC);
    public static final Item capacitor = new ItemMageTech().setUnlocalizedName(Names.Items.CAPACITOR).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.CAPACITOR);
    public static final Item transistor = new ItemMageTech().setUnlocalizedName(Names.Items.TRANSISTOR).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.TRANSISTOR);
    public static final ItemMageTech circuitBoard = new ItemCircuitBoard();

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
        GameRegistry.registerItem(circuitBoard, Names.Items.CIRCUITBOARD);

    }
}

