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
    public static final ItemMageTech_Magic crystal = new ItemCrystal();
    public static final ItemMageTech_Tech ingot = new ItemIngot();
    public static final Item siliconOre = new ItemMageTech_Tech().setUnlocalizedName(Names.Items.SILICONORE).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.SILICONORE);
    public static final Item silicon = new ItemMageTech_Tech().setUnlocalizedName(Names.Items.SILICON).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.SILICON);
    public static final Item resin = new ItemMageTech_Tech().setUnlocalizedName(Names.Items.RESIN).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.RESIN);
    public static final Item plastic = new ItemMageTech_Tech().setUnlocalizedName(Names.Items.PLASTIC).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.PLASTIC);
    public static final Item glassFibre = new ItemMageTech_Tech().setUnlocalizedName(Names.Items.GLASSFIBRE).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.GLASSFIBRE);
    public static final Item glassFibreMat = new ItemMageTech_Tech().setUnlocalizedName(Names.Items.GLASSFIBREMAT).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.GLASSFIBREMAT);
    public static final Item wireIron = new ItemMageTech_Tech().setUnlocalizedName(Names.Items.WIREIRON).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.WIREIRON);
    public static final Item ic = new ItemMageTech_Tech().setUnlocalizedName(Names.Items.IC).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.IC);
    public static final Item capacitor = new ItemMageTech_Tech().setUnlocalizedName(Names.Items.CAPACITOR).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.CAPACITOR);
    public static final Item transistor = new ItemMageTech_Tech().setUnlocalizedName(Names.Items.TRANSISTOR).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.TRANSISTOR);
    public static final ItemMageTech_Tech circuitBoard = new ItemCircuitBoard();
    public static final ItemMageTech_Tech crushedOre = new ItemCrushedOre();
    public static final Item rubber = new ItemMageTech_Tech().setUnlocalizedName(Names.Items.RUBBER).setTextureName(Textures.RESOURCE_PREFIX + Names.Items.RUBBER);
    public static final ItemMageTech_Tech dustOre = new ItemDustOre();
    public static final ItemMageTech_Magic talismanAir = new ItemTalismanAir();
    public static final ItemMageTech_Magic talismanFire = new ItemTalismanFire();

    public static void init()
    {
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
        GameRegistry.registerItem(crushedOre, Names.Items.CRUSHEDORE);
        GameRegistry.registerItem(rubber, Names.Items.RUBBER);
        GameRegistry.registerItem(dustOre, Names.Items.DUSTORE);
        GameRegistry.registerItem(talismanAir, Names.Items.TALISMAN_AIR);
        GameRegistry.registerItem(talismanFire, Names.Items.TALISMAN_FIRE);

        OreDictionary.registerOre("ingotLead", new ItemStack(ingot, 1, 0));
        OreDictionary.registerOre("ingotSilver", new ItemStack(ingot, 1, 1));
        OreDictionary.registerOre("ingotCopper", new ItemStack(ingot, 1, 2));
        OreDictionary.registerOre("dustIron", new ItemStack(dustOre, 1, 0));
        OreDictionary.registerOre("dustGold", new ItemStack(dustOre, 1, 1));
        OreDictionary.registerOre("dustCopper", new ItemStack(dustOre, 1, 2));
        OreDictionary.registerOre("dustLead", new ItemStack(dustOre, 1, 3));
        OreDictionary.registerOre("dustSilver", new ItemStack(dustOre, 1, 4));
    }
}

