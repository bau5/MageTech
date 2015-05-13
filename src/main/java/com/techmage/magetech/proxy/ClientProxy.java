package com.techmage.magetech.proxy;

import com.techmage.magetech.reference.Names;
import com.techmage.magetech.tileentity.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy
{

    @Override
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCrusher.class, Names.Blocks.CRUSHER);
        GameRegistry.registerTileEntity(TileEntityCentrifuge.class, Names.Blocks.CENTRIFUGE);
        GameRegistry.registerTileEntity(TileEntityPowerFurnace.class, Names.Blocks.POWERFURNACE);
        GameRegistry.registerTileEntity(TileEntitySolderingStation.class, Names.Blocks.SOLDERING_STATION);

        GameRegistry.registerTileEntity(TileEntityInfuser.class, Names.Blocks.INFUSER);
        GameRegistry.registerTileEntity(TileEntityElementalExtractor.class, Names.Blocks.ELEMENTAL_EXTRACTOR);
        GameRegistry.registerTileEntity(TileEntityCrystalCluster.class, Names.Blocks.CRYSTAL_CLUSTER);
    }
}
