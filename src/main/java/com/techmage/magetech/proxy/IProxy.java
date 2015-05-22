package com.techmage.magetech.proxy;

public interface IProxy
{
    public abstract void registerTileEntities();

    public abstract void bindTileEntityRenderer();

    public abstract void bindEntityRenderer();

    public abstract void registerKeyBindings();
}
