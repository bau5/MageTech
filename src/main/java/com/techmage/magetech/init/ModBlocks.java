package com.techmage.magetech.init;

import com.techmage.magetech.block.*;
import com.techmage.magetech.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks
{
    public static final BlockMageTech infuser = new BlockInfuser();
    public static final BlockMageTech stoneHardened = new BlockHardenedStone();
    public static final BlockMageTech mpWire = new BlockMPWire();
    public static final BlockMageTech ore = new BlockOre();
    public static final BlockMageTech oreCrystal = new BlockOreCrystal();
    public static final BlockMageTech crusher = new BlockCrusher();
    public static final BlockMageTech oreSilicon = new BlockOreSilicon();
    public static final BlockMageTech electronicsWorkbench = new BlockElectronicsWorkbench();

    public static void init()
    {
        GameRegistry.registerBlock(infuser, Names.Blocks.INFUSER);
        GameRegistry.registerBlock(stoneHardened, Names.Blocks.STONE_HARDENED);
        GameRegistry.registerBlock(mpWire, Names.Blocks.MPWIRE);
        GameRegistry.registerBlock(ore, BlockItemOre.class, Names.Blocks.ORE);
        GameRegistry.registerBlock(oreCrystal, Names.Blocks.ORECRYSTAL);
        GameRegistry.registerBlock(crusher, Names.Blocks.CRUSHER);
        GameRegistry.registerBlock(oreSilicon, Names.Blocks.ORESILICON);
        GameRegistry.registerBlock(electronicsWorkbench, Names.Blocks.ELECTRONICS_WORKBENCH);

        OreDictionary.registerOre("oreLead", new ItemStack(ore, 1, 0));
        OreDictionary.registerOre("oreSilver", new ItemStack(ore, 1, 1));
        OreDictionary.registerOre("oreCopper", new ItemStack(ore, 1, 2));
    }
}
