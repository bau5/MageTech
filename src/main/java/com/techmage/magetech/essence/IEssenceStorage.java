package com.techmage.magetech.essence;

public interface IEssenceStorage
{

    public void storeEssence (int amount);

    public void removeEssence (int amount);

    public int getStoredEssence();

    public int getMaxEssence();

}
