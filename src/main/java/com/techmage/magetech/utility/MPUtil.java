package com.techmage.magetech.utility;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;

public class MPUtil
{

    public static TileEntity[] getNeighborTiles(World world, int x, int y, int z)
    {
        ArrayList<TileEntity> tiles = JavaHelp.newArrayList();
        tiles.add(world.getTileEntity(x + 1, y, z));
        tiles.add(world.getTileEntity(x - 1, y, z));
        tiles.add(world.getTileEntity(x, y + 1, z));
        tiles.add(world.getTileEntity(x, y - 1, z));
        tiles.add(world.getTileEntity(x, y, z + 1));
        tiles.add(world.getTileEntity(x, y, z - 1));
        return tiles.toArray(new TileEntity[0]);
    }

    public static boolean isServerSide()
    {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER;
    }

    public static boolean isClientSide()
    {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;
    }

}
