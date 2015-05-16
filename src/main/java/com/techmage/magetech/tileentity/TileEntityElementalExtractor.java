package com.techmage.magetech.tileentity;

import com.techmage.magetech.init.ModBlocks;
import com.techmage.magetech.utility.Location;
import com.techmage.magetech.utility.LogHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

public class TileEntityElementalExtractor extends TileEntityEssenceHandler
{
    public int productionTime = 0;

    public int storedEssence = 0;
    public int maxEssence = 1000;

    public ArrayList<Location> Provider = new ArrayList<Location>();
    public ArrayList<Location> Requester = new ArrayList<Location>();
    public ArrayList<Location> Nodes = new ArrayList<Location>();

    public ArrayList<Location> clonedProvider = new ArrayList<Location>();
    public ArrayList<Location> clonedRequester = new ArrayList<Location>();
    public ArrayList<Location> clonedNodes = new ArrayList<Location>();

    public boolean isReset = false;
    public int EssenceHandlerType = 0;

    public TileEntityElementalExtractor()
    {
        this.setConnectionSides(ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST);
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
    public int getEssenceHandlerTypeOwn()
    {
        return this.EssenceHandlerType;
    }

    @Override
    public void cloneConnections()
    {
        this.clonedProvider = this.Provider;
        this.clonedRequester = this.Requester;
        this.clonedNodes = this.Nodes;
    }

    @Override
    public ArrayList<Location> getClonedProvider()
    {
        return this.clonedProvider;
    }

    @Override
    public ArrayList<Location> getClonedRequester()
    {
        return this.clonedRequester;
    }

    @Override
    public ArrayList<Location> getClonedNodes()
    {
        return this.clonedNodes;
    }

    @Override
    public void resetConnections()
    {
        this.Provider = new ArrayList<Location>();
        this.Requester = new ArrayList<Location>();
        this.Nodes = new ArrayList<Location>();
        this.isReset = true;
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
    public void storeEssence(int amount)
    {
        this.storedEssence += amount;
    }

    @Override
    public ArrayList<Location> getConnectedProvider()
    {
        return this.Provider;
    }

    @Override
    public ArrayList<Location> getConnectedRequester()
    {
        return this.Requester;
    }

    @Override
    public ArrayList<Location> getConnectedNodes()
    {
        return this.Nodes;
    }

    @Override
    public void addConnectedProvider(Location provider)
    {
        this.Provider.add(provider);
    }

    @Override
    public void addConnectedRequester(Location requester)
    {
        this.Requester.add(requester);
    }

    @Override
    public void addConnectedNode(Location node)
    {
        this.Nodes.add(node);
    }

    @Override
    public void updateEntity()
    {

        if (!worldObj.isRemote)
        {

            this.isReset = false;

            if (!connectionsExist())
                resetEssenceNetwork();

            ArrayList<Location> Connections = getConnections(this.xCoord, this.yCoord, this.zCoord);

            for (Location loc : Connections)
            {
                int type = getEssenceHandlerType(loc);

                if (type != this.getEssenceHandlerTypeOwn())
                {
                    if (!isConnected(loc, type))
                    {
                        createConnection(loc, type);
                    }
                }
            }

            addNodeConnections();

            if (productionTime > 100)
            {
                productionTime = 0;
                this.collectEssence();
            }
            else
                productionTime++;

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
                storeEssence(10);
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
                storeEssence(10);
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
                storeEssence(10);
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
                storeEssence(10);
            }
        }

        if (!this.canStoreEssence(1))
            return;

        if (worldObj.getBlock(this.xCoord - 2, this.yCoord, this.zCoord + 2) == ModBlocks.crystalCluster)
        {
            TileEntityCrystalCluster cluster = (TileEntityCrystalCluster) worldObj.getTileEntity(this.xCoord - 2, this.yCoord, this.zCoord + 2);
            if (cluster.getEssenceLeft() >= 1)
            {
                cluster.collectEssence();
                storeEssence(10);
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
                storeEssence(10);
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
                storeEssence(10);
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
                storeEssence(10);
            }
        }
    }
}

