package com.techmage.magetech.init;

import com.techmage.magetech.block.BlockInfuser;
import com.techmage.magetech.block.BlockMageTech;
import com.techmage.magetech.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
    public static final BlockMageTech infuser = new BlockInfuser();

    public static void init()
    {
        GameRegistry.registerBlock(infuser, Names.Blocks.INFUSER);
    }
}
