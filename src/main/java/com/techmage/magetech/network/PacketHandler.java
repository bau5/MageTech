package com.techmage.magetech.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler
{
    public static final SimpleNetworkWrapper packetReq = NetworkRegistry.INSTANCE.newSimpleChannel("magetech");

    public static void init()
    {
        packetReq.registerMessage(DoBlockUpdate.Handler.class, DoBlockUpdate.class, 0, Side.SERVER);
    };
}
