package com.techmage.magetech.power;

import net.minecraft.world.World;

public interface IConnector
{
    public boolean canConnect(World world, int x, int y, int z);
}