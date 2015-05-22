package com.techmage.magetech.init;

import com.techmage.magetech.MageTech;
import com.techmage.magetech.entity.EntityEssenceBeam;
import cpw.mods.fml.common.registry.EntityRegistry;

public class Entity
{

    public static void init()
    {
        EntityRegistry.registerModEntity(EntityEssenceBeam.class, "essenceBeam", 0, MageTech.instance, 80, 1, true);
    }

}
