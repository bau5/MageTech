package com.techmage.magetech.essence;

import net.minecraftforge.common.util.ForgeDirection;

public interface IEssenceSender
{

    public void setSenderSites(ForgeDirection ... dir);

    public ForgeDirection[] getSenderSites();

    public void SendEssence(int amount, int[] dest);

}
