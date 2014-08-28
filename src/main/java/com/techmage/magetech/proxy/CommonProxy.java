package com.techmage.magetech.proxy;

import com.techmage.magetech.reference.Names;
import com.techmage.magetech.tileentity.TileEntityInfuser;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    @Override
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityInfuser.class, Names.Blocks.INFUSER);
    }
}
