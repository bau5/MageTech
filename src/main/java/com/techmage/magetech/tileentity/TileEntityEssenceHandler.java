package com.techmage.magetech.tileentity;

import com.techmage.magetech.essence.IEssenceReceiver;
import com.techmage.magetech.essence.IEssenceSender;
import com.techmage.magetech.essence.IEssenceStorage;
import com.techmage.magetech.utility.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class TileEntityEssenceHandler extends TileEntityMageTech  implements IEssenceStorage, IEssenceSender, IEssenceReceiver
{

    public int maxEssence;
    public int storedEssence;

    public ForgeDirection[] ReceiverSites = new ForgeDirection[6];
    public ForgeDirection[] SenderSites = new ForgeDirection[6];

    @Override
    public void setReceiverSites(ForgeDirection... dir)
    {
        for (int i = 0; i < dir.length; i ++)
        {
            ReceiverSites[i] = dir[i];
        }
    }

    @Override
    public ForgeDirection[] getReceiverSites()
    {
        return this.ReceiverSites;
    }

    @Override
    public void ReceiveEssence(int amount, int[] source)
    {
        storeEssence(amount);
    }

    @Override
    public void setSenderSites(ForgeDirection... dir)
    {
        for (int i = 0; i < dir.length; i ++)
        {
            SenderSites[i] = dir[i];
        }
    }

    @Override
    public void setSenderSites(ArrayList<ForgeDirection> dir)
    {
        for (int i = 0; i < dir.size(); i ++)
        {
            SenderSites[i] = dir.get(i);
        }
    }

    @Override
    public ForgeDirection[] getSenderSites()
    {
        return this.SenderSites;
    }

    @Override
    public void SendEssence(int amount, int[] dest)
    {
        if (this.worldObj.getBlock(dest[0], dest[1], dest[2]).hasTileEntity())
        {
            if (this.worldObj.getTileEntity(dest[0], dest[1], dest[2]) instanceof TileEntityEssenceHandler)
            {
                if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(dest[0], dest[1], dest[2])).canStoreEssence(amount))
                {
                    int[] source = {this.xCoord, this.yCoord, this.zCoord};
                    ((TileEntityEssenceHandler) this.worldObj.getTileEntity(dest[0], dest[1], dest[2])).ReceiveEssence(amount, source);
                    this.removeEssence(amount);
                }
            }
        }
    }

    public boolean canStoreEssence(int amount)
    {
        return this.getStoredEssence() + amount <= this.getMaxEssence() ? true : false;
    }

    @Override
    public void storeEssence(int amount)
    {
        this.storedEssence += amount;
    }

    @Override
    public void removeEssence(int amount)
    {
        this.storedEssence -= amount;
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

    public ForgeDirection getOppositeSite(ForgeDirection site)
    {
        if (site == ForgeDirection.NORTH)
            return ForgeDirection.SOUTH;
        if (site == ForgeDirection.SOUTH)
            return ForgeDirection.NORTH;

        if (site == ForgeDirection.EAST)
            return ForgeDirection.WEST;
        if (site == ForgeDirection.WEST)
            return ForgeDirection.EAST;

        if (site == ForgeDirection.UP)
            return ForgeDirection.DOWN;
        if (site == ForgeDirection.DOWN)
            return ForgeDirection.UP;

        else
            return null;
    }

    public boolean isReceiverSide(ForgeDirection site)
    {
        for (int i = 0; i < this.getReceiverSites().length; i ++)
        {
            if (this.getReceiverSites()[i] == site)
                return true;
        }

        return false;
    }

    public List <int[]> getDest()
    {

        List<int[]> destination = new ArrayList<int[]>();

        for (int i = 0; i < getSenderSites().length; i ++)
        {
            if (getSenderSites()[i] == ForgeDirection.NORTH)
            {
                for (int j = 1; j < 9; j ++)
                {
                    if (this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord - j).hasTileEntity())
                    {
                        if (this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - j) instanceof TileEntityEssenceHandler)
                        {
                            if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - j)).isReceiverSide(getOppositeSite(ForgeDirection.NORTH)))
                            {
                                int[] dest = {this.xCoord, this.yCoord, this.zCoord - j};
                                destination.add(dest);
                            }
                        }
                    }
                    if (this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord - j) != Blocks.air)
                        break;
                }
            }

            if (getSenderSites()[i] == ForgeDirection.EAST)
            {
                for (int j = 1; j < 9; j ++)
                {
                    if (this.worldObj.getBlock(this.xCoord + j, this.yCoord, this.zCoord).hasTileEntity())
                    {
                        if (this.worldObj.getTileEntity(this.xCoord + j, this.yCoord, this.zCoord) instanceof TileEntityEssenceHandler)
                        {
                            if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(this.xCoord + j, this.yCoord, this.zCoord)).isReceiverSide(getOppositeSite(ForgeDirection.EAST)))
                            {
                                int[] dest = {this.xCoord + j, this.yCoord, this.zCoord};
                                destination.add(dest);
                            }
                        }
                    }
                    if (this.worldObj.getBlock(this.xCoord + j, this.yCoord, this.zCoord) != Blocks.air)
                        break;
                }
            }

            if (getSenderSites()[i] == ForgeDirection.SOUTH)
            {
                for (int j = 1; j < 9; j ++)
                {
                    if (this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord + j).hasTileEntity())
                    {
                        if (this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + j) instanceof TileEntityEssenceHandler)
                        {
                            if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + j)).isReceiverSide(getOppositeSite(ForgeDirection.SOUTH)))
                            {
                                int[] dest = {this.xCoord, this.yCoord, this.zCoord + j};
                                destination.add(dest);
                            }
                        }
                    }
                    if (this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord + j) != Blocks.air)
                        break;
                }
            }

            if (getSenderSites()[i] == ForgeDirection.WEST)
            {
                for (int j = 1; j < 9; j ++)
                {
                    if (this.worldObj.getBlock(this.xCoord - j, this.yCoord, this.zCoord).hasTileEntity())
                    {
                        if (this.worldObj.getTileEntity(this.xCoord - j, this.yCoord, this.zCoord) instanceof TileEntityEssenceHandler)
                        {
                            if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(this.xCoord - j, this.yCoord, this.zCoord)).isReceiverSide(getOppositeSite(ForgeDirection.WEST)))
                            {
                                int[] dest = {this.xCoord - j, this.yCoord, this.zCoord};
                                destination.add(dest);
                            }
                        }
                    }
                    if (this.worldObj.getBlock(this.xCoord - j, this.yCoord, this.zCoord) != Blocks.air)
                        break;
                }
            }

            if (getSenderSites()[i] == ForgeDirection.UP)
            {
                for (int j = 1; j < 9; j ++)
                {
                    if (this.worldObj.getBlock(this.xCoord, this.yCoord + j, this.zCoord).hasTileEntity())
                    {
                        if (this.worldObj.getTileEntity(this.xCoord, this.yCoord + j, this.zCoord) instanceof TileEntityEssenceHandler)
                        {
                            if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(this.xCoord, this.yCoord + j, this.zCoord)).isReceiverSide(getOppositeSite(ForgeDirection.UP)))
                            {
                                int[] dest = {this.xCoord, this.yCoord + j, this.zCoord};
                                destination.add(dest);
                            }
                        }
                    }
                    if (this.worldObj.getBlock(this.xCoord, this.yCoord + j, this.zCoord) != Blocks.air)
                        break;
                }
            }

            if (getSenderSites()[i] == ForgeDirection.DOWN)
            {
                for (int j = 1; j < 9; j ++)
                {
                    if (this.worldObj.getBlock(this.xCoord, this.yCoord - j, this.zCoord).hasTileEntity())
                    {
                        if (this.worldObj.getTileEntity(this.xCoord, this.yCoord - j, this.zCoord) instanceof TileEntityEssenceHandler)
                        {
                            if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(this.xCoord, this.yCoord - j, this.zCoord)).isReceiverSide(getOppositeSite(ForgeDirection.DOWN)))
                            {
                                int[] dest = {this.xCoord, this.yCoord - j, this.zCoord};
                                destination.add(dest);
                            }
                        }
                    }
                    if (this.worldObj.getBlock(this.xCoord, this.yCoord - j, this.zCoord) != Blocks.air)
                        break;
                }
            }

        }

        return destination;
    }

}
