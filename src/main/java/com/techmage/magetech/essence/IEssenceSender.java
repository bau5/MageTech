package com.techmage.magetech.essence;

import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

public interface IEssenceSender
{

    public void setSenderSites(ForgeDirection ... dir);

    public void setSenderSites(ArrayList<ForgeDirection> dir);

    public ForgeDirection[] getSenderSites();

    public void SendEssence(int amount, int[] dest);

}
