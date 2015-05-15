package com.techmage.magetech.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityCrystalCluster extends TileEntityMageTech
{
    public int EssenceLeft = 100;

    public TileEntityCrystalCluster() { }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("EssenceLeft", (short)this.EssenceLeft);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        this.EssenceLeft = nbtTagCompound.getShort("EssenceLeft");
    }

    @Override
    public void updateEntity() { }

    public int getEssenceLeft()
    {
        return this.EssenceLeft;
    }

    public void collectEssence()
    {
        if (this.EssenceLeft > 1)
            this.EssenceLeft --;
        else
            worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.air);
    }

}

