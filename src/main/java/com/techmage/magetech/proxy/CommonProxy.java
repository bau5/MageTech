package com.techmage.magetech.proxy;

import com.techmage.magetech.reference.Names;
import com.techmage.magetech.renderer.TileEntityMPWireRenderer;
import com.techmage.magetech.tileentity.TileEntityInfuser;
import com.techmage.magetech.tileentity.TileEntityMPWire;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    @Override
    public void registerTileEntities() { }
}
