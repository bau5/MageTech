package com.techmage.magetech.init;

import com.techmage.magetech.block.*;
import com.techmage.magetech.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks
{
    // Tech
    public static final BlockMageTech_Tech ore = new BlockOre();
    public static final BlockMageTech_Tech oreSilicon = new BlockOreSilicon();

    public static final BlockMageTech_Tech crusher = new BlockCrusher();
    public static final BlockMageTech_Tech centrifuge = new BlockCentrifuge();
    public static final BlockMageTech_Tech powerFurnace = new BlockPowerFurnace();
    public static final BlockMageTech_Tech electronicsWorkbench = new BlockElectronicsWorkbench();
    public static final BlockMageTech_Tech solderingStation = new BlockSolderingStation();

    // Magic
    public static final BlockMageTech_Magic oreCrystal = new BlockOreCrystal();
    public static final BlockMageTech_Magic stoneHardened = new BlockHardenedStone();
    public static final BlockMageTech_Magic crystalCluster = new BlockCrystalCluster();

    public static final BlockMageTech_Magic infuser = new BlockInfuser();
    public static final BlockMageTech_Magic elementalExtractor = new BlockElementalExtractor();

    public static void init()
    {
        // Tech
        GameRegistry.registerBlock(ore, BlockItemOre.class, Names.Blocks.ORE);
        GameRegistry.registerBlock(oreSilicon, Names.Blocks.ORESILICON);

        OreDictionary.registerOre("oreLead", new ItemStack(ore, 1, 0));
        OreDictionary.registerOre("oreSilver", new ItemStack(ore, 1, 1));
        OreDictionary.registerOre("oreCopper", new ItemStack(ore, 1, 2));

        GameRegistry.registerBlock(crusher, Names.Blocks.CRUSHER);
        GameRegistry.registerBlock(centrifuge, Names.Blocks.CENTRIFUGE);
        GameRegistry.registerBlock(powerFurnace, Names.Blocks.POWERFURNACE);
        GameRegistry.registerBlock(electronicsWorkbench, Names.Blocks.ELECTRONICS_WORKBENCH);
        GameRegistry.registerBlock(solderingStation, Names.Blocks.SOLDERING_STATION);

        // Magic
        GameRegistry.registerBlock(oreCrystal, Names.Blocks.ORECRYSTAL);
        GameRegistry.registerBlock(stoneHardened, Names.Blocks.STONE_HARDENED);
        GameRegistry.registerBlock(crystalCluster, Names.Blocks.CRYSTAL_CLUSTER);

        GameRegistry.registerBlock(infuser, Names.Blocks.INFUSER);
        GameRegistry.registerBlock(elementalExtractor, Names.Blocks.ELEMENTAL_EXTRACTOR);
    }
}
