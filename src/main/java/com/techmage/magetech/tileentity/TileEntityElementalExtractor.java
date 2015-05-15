package com.techmage.magetech.tileentity;

import com.techmage.magetech.init.ModBlocks;
import com.techmage.magetech.utility.LogHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityElementalExtractor extends TileEntityEssenceHandler
{
    public int productionTime = 0;
    public int storedEssence = 0;
    public int maxEssence = 1000;

    public TileEntityElementalExtractor()
    {

        this.setSenderSites(ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST);
        this.setReceiverSites(ForgeDirection.NORTH, ForgeDirection.SOUTH);

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
    public void updateEntity()
    {

    if (!worldObj.isRemote)
    {
        if (productionTime > 100)
        {
            productionTime = 0;
            this.collectEssence();
        }
        else
            productionTime++;

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

    public void collectEssence()
    {

        if (!this.canStoreEssence(10))
            return;

        if (worldObj.getBlock(this.xCoord + 3, this.yCoord, this.zCoord) == ModBlocks.crystalCluster)
        {
            TileEntityCrystalCluster cluster = (TileEntityCrystalCluster) worldObj.getTileEntity(this.xCoord + 3, this.yCoord, this.zCoord);
            if (cluster.getEssenceLeft() >= 1)
            {
                cluster.collectEssence();
                this.storedEssence += 10;
            }
        }

        if (!this.canStoreEssence(1))
            return;

        if (worldObj.getBlock(this.xCoord + 2, this.yCoord, this.zCoord + 2) == ModBlocks.crystalCluster)
        {
            TileEntityCrystalCluster cluster = (TileEntityCrystalCluster) worldObj.getTileEntity(this.xCoord + 2, this.yCoord, this.zCoord + 2);
            if (cluster.getEssenceLeft() >= 1)
            {
                cluster.collectEssence();
                this.storedEssence += 10;
            }
        }

        if (!this.canStoreEssence(1))
            return;

        if (worldObj.getBlock(this.xCoord + 2, this.yCoord, this.zCoord - 2) == ModBlocks.crystalCluster)
        {
            TileEntityCrystalCluster cluster = (TileEntityCrystalCluster) worldObj.getTileEntity(this.xCoord + 2, this.yCoord, this.zCoord - 2);
            if (cluster.getEssenceLeft() >= 1)
            {
                cluster.collectEssence();
                this.storedEssence += 10;
            }
        }

        if (!this.canStoreEssence(1))
            return;

        if (worldObj.getBlock(this.xCoord - 3, this.yCoord, this.zCoord) == ModBlocks.crystalCluster)
        {
            TileEntityCrystalCluster cluster = (TileEntityCrystalCluster) worldObj.getTileEntity(this.xCoord - 3, this.yCoord, this.zCoord);
            if (cluster.getEssenceLeft() >= 1)
            {
                cluster.collectEssence();
                this.storedEssence += 10;
            }
        }

        if (!this.canStoreEssence(1))
            return;

        if (worldObj.getBlock(this.xCoord - 2, this.yCoord, this.zCoord + 2) == ModBlocks.crystalCluster) {
            TileEntityCrystalCluster cluster = (TileEntityCrystalCluster) worldObj.getTileEntity(this.xCoord - 2, this.yCoord, this.zCoord + 2);
            if (cluster.getEssenceLeft() >= 1) {
                cluster.collectEssence();
                this.storedEssence += 10;
            }
        }

        if (!this.canStoreEssence(1))
            return;

        if (worldObj.getBlock(this.xCoord - 2, this.yCoord, this.zCoord - 2) == ModBlocks.crystalCluster)
        {
            TileEntityCrystalCluster cluster = (TileEntityCrystalCluster) worldObj.getTileEntity(this.xCoord - 2, this.yCoord, this.zCoord - 2);
            if (cluster.getEssenceLeft() >= 1)
            {
                cluster.collectEssence();
                this.storedEssence += 10;
            }
        }

        if (!this.canStoreEssence(1))
            return;

        if (worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord + 3) == ModBlocks.crystalCluster)
        {
            TileEntityCrystalCluster cluster = (TileEntityCrystalCluster) worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 3);
            if (cluster.getEssenceLeft() >= 1)
            {
                cluster.collectEssence();
                this.storedEssence += 10;
            }
        }

        if (!this.canStoreEssence(1))
            return;

        if (worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord - 3) == ModBlocks.crystalCluster)
        {
            TileEntityCrystalCluster cluster = (TileEntityCrystalCluster) worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 3);
            if (cluster.getEssenceLeft() >= 1)
            {
                cluster.collectEssence();
                this.storedEssence += 10;
            }
        }
    }
}

