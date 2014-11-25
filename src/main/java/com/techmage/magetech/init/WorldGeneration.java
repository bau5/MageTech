package com.techmage.magetech.init;

import com.techmage.magetech.world.OreGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class WorldGeneration
{

    public static void init()
    {
        GameRegistry.registerWorldGenerator(new OreGenerator(), 1);
    }

}
