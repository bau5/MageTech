package com.techmage.magetech.essence;

import net.minecraftforge.common.util.ForgeDirection;

public interface IEssenceReceiver
{

    public void setReceiverSites(ForgeDirection... dir);

    public ForgeDirection[] getReceiverSites();

    public void ReceiveEssence(int amount, int[] source);

}
