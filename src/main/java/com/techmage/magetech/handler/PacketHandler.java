package com.techmage.magetech.handler;

import com.techmage.magetech.network.PacketPowerFurnaceSetMode;
import com.techmage.magetech.network.PacketSolderingStationCraftingButtonPressed;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler
{
    public static final SimpleNetworkWrapper packetReq = NetworkRegistry.INSTANCE.newSimpleChannel("magetech");

    public static void init()
    {
        packetReq.registerMessage(PacketPowerFurnaceSetMode.Handler.class, PacketPowerFurnaceSetMode.class, 0, Side.SERVER);
        packetReq.registerMessage(PacketSolderingStationCraftingButtonPressed.Handler.class, PacketSolderingStationCraftingButtonPressed.class, 1, Side.SERVER);
    };
}
