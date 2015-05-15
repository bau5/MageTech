package com.techmage.magetech.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileEntityEssenceNode extends TileEntityEssenceHandler
{
    public int storedEssence = 0;
    public int maxEssence = 50;

    public List<int[]> sources = new ArrayList<int[]>();
    public ArrayList<ForgeDirection> defSenderSites = new ArrayList(Arrays.asList(ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST));

    public TileEntityEssenceNode()
    {
        this.setReceiverSites(ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("storedEssence", (short)this.storedEssence);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        this.storedEssence = nbtTagCompound.getShort("storedEssence");
    }

    @Override
    public int getStoredEssence()
    {
        return this.storedEssence;
    }

    @Override
    public int getMaxEssence()
    {
        return this.maxEssence;
    }

    @Override
    public void removeEssence(int amount)
    {
        this.storedEssence -= amount;
    }

    @Override
    public void ReceiveEssence(int amount, int[] source)
    {
        this.storedEssence += amount;
        if (!this.sources.contains(source))
        {
            this.sources.add(source);
        }
    }

    @Override
    public void updateEntity()
    {

    if (!worldObj.isRemote)
    {

        List<ForgeDirection> getEssenceFrom = new ArrayList<ForgeDirection>();

        for (int i = 0; i < this.sources.size(); i ++)
        {
            if (this.worldObj.getBlock(this.sources.get(i)[0], this.sources.get(i)[1], this.sources.get(i)[2]).hasTileEntity())
            {
                if (this.worldObj.getTileEntity(this.sources.get(i)[0], this.sources.get(i)[1], this.sources.get(i)[2]) instanceof TileEntityEssenceHandler)
                {
                    TileEntityEssenceHandler source = (TileEntityEssenceHandler) this.worldObj.getTileEntity(this.sources.get(i)[0], this.sources.get(i)[1], this.sources.get(i)[2]);

                    if (source.xCoord == this.xCoord && source.yCoord == this.yCoord && source.zCoord < this.zCoord)
                        getEssenceFrom.add(ForgeDirection.NORTH);

                    if (source.xCoord == this.xCoord && source.yCoord == this.yCoord && source.zCoord > this.zCoord)
                        getEssenceFrom.add(ForgeDirection.SOUTH);

                    if (source.xCoord < this.xCoord && source.yCoord == this.yCoord && source.zCoord == this.zCoord)
                        getEssenceFrom.add(ForgeDirection.WEST);

                    if (source.xCoord > this.xCoord && source.yCoord == this.yCoord && source.zCoord == this.zCoord)
                        getEssenceFrom.add(ForgeDirection.EAST);

                    if (source.xCoord == this.xCoord && source.yCoord < this.yCoord && source.zCoord == this.zCoord)
                        getEssenceFrom.add(ForgeDirection.DOWN);

                    if (source.xCoord == this.xCoord && source.yCoord > this.yCoord && source.zCoord == this.zCoord)
                        getEssenceFrom.add(ForgeDirection.UP);
                }
                else
                    this.sources.remove(i);
            }
        }

        ArrayList<ForgeDirection> senderSites = this.defSenderSites;
        senderSites.removeAll(getEssenceFrom);

        this.setSenderSites(senderSites);

        if (!this.getDest().isEmpty())
        {
            for (int i = 0; i < this.getDest().size(); i++)
                if (this.getStoredEssence() > 0)
                {
                    this.SendEssence(1, this.getDest().get(i));
                }
            }
        }
    }
}

