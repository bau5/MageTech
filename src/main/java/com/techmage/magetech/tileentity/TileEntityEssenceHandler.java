package com.techmage.magetech.tileentity;

import com.techmage.magetech.utility.Location;
import com.techmage.magetech.utility.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

public class TileEntityEssenceHandler extends TileEntityMageTech
{

    public int maxEssence;
    public int storedEssence;

    public ForgeDirection[] ConnectionSides = new ForgeDirection[6];

    public ArrayList<Location> Provider = new ArrayList<Location>();
    public ArrayList<Location> Requester = new ArrayList<Location>();
    public ArrayList<Location> Nodes = new ArrayList<Location>();

    public ArrayList<Location> clonedProvider = new ArrayList<Location>();
    public ArrayList<Location> clonedRequester = new ArrayList<Location>();
    public ArrayList<Location> clonedNodes = new ArrayList<Location>();

    public boolean isReset = false;
    public int EssenceHandlerType = 3;

    public ArrayList<Location> getConnectedProvider()
    {
        return this.Provider;
    }

    public ArrayList<Location> getConnectedRequester()
    {
        return this.Requester;
    }

    public ArrayList<Location> getConnectedNodes()
    {
        return this.Nodes;
    }

    public void addConnectedProvider(Location provider)
    {
        this.Provider.add(provider);
    }

    public void addConnectedRequester(Location requester)
    {
        this.Requester.add(requester);
    }

    public void addConnectedNode(Location node)
    {
        this.Nodes.add(node);
    }

    public ForgeDirection[] getConnectionSides()
    {
        return this.ConnectionSides;
    }

    public void setConnectionSides(ForgeDirection... dir)
    {
        for (int i = 0; i < dir.length; i ++)
        {
            ConnectionSides[i] = dir[i];
        }
    }

    public boolean canStoreEssence(int amount)
    {
        return this.getStoredEssence() + amount <= this.getMaxEssence() ? true : false;
    }

    public int getStoredEssence()
    {
        return this.storedEssence;
    }

    public int getMaxEssence()
    {
        return this.maxEssence;
    }

    public int getAvaibleEssenceInNetwork()
    {
        ArrayList<Location> provider = getConnectedProvider();
        int avaibleEssence = 0;

        for (Location loc : provider)
        {
            TileEntityEssenceHandler tile = (TileEntityEssenceHandler) this.worldObj.getTileEntity(loc.x, loc.y, loc.z);
            avaibleEssence += tile.getStoredEssence();
        }

        return avaibleEssence;

    }

    public void requestEssenceFromNetwork(int amount)
    {
        ArrayList<Location> provider = getConnectedProvider();
        int remainAmount = amount;

        for (Location loc : provider)
        {
            TileEntityEssenceHandler tile = (TileEntityEssenceHandler) this.worldObj.getTileEntity(loc.x, loc.y, loc.z);
            if (tile.getStoredEssence() > 0)
            {
                int avaibleEssence = tile.getStoredEssence();

                if (avaibleEssence >= remainAmount)
                {
                    tile.removeEssence(remainAmount);
                    storeEssence(remainAmount);
                    remainAmount = 0;
                }
                else
                {
                    tile.removeEssence(avaibleEssence);
                    storeEssence(avaibleEssence);
                    remainAmount -= avaibleEssence;
                }
            }

            if (remainAmount == 0)
                return;
        }
    }

    public void removeEssence(int amount)
    {
        this.storedEssence -= amount;
    }

    public void storeEssence(int amount)
    {
        this.storedEssence += amount;
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

    public boolean isConnectionSide(ForgeDirection side)
    {
        for (int i = 0; i < this.getConnectionSides().length; i ++)
        {
            if (this.getConnectionSides()[i] == side)
                return true;
        }

        return false;
    }

    public ArrayList<Location> getConnections(int x, int y, int z)
    {

        ArrayList<Location> connections = new ArrayList<Location>();

        for (int i = 0; i < getConnectionSides().length; i ++)
        {
            if (getConnectionSides()[i] == ForgeDirection.NORTH)
            {
                for (int j = 1; j < 9; j ++)
                {
                    if (this.worldObj.getBlock(x, y, z - j).hasTileEntity())
                    {
                        if (this.worldObj.getTileEntity(x, y, z - j) instanceof TileEntityEssenceHandler)
                        {
                            if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(x, y, z - j)).isConnectionSide(getOppositeSite(ForgeDirection.NORTH)))
                            {
                                Location connection = new Location(x, y, z - j);
                                connections.add(connection);
                            }
                        }
                    }
                    if (this.worldObj.getBlock(x, y, z - j) != Blocks.air)
                        break;
                }
            }

            if (getConnectionSides()[i] == ForgeDirection.EAST)
            {
                for (int j = 1; j < 9; j ++)
                {
                    if (this.worldObj.getBlock(x + j, y, z).hasTileEntity())
                    {
                        if (this.worldObj.getTileEntity(x + j, y, z) instanceof TileEntityEssenceHandler)
                        {
                            if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(x + j, y, z)).isConnectionSide(getOppositeSite(ForgeDirection.EAST)))
                            {
                                Location connection = new Location(x + j, y, z);
                                connections.add(connection);
                            }
                        }
                    }
                    if (this.worldObj.getBlock(x + j, y, z) != Blocks.air)
                        break;
                }
            }

            if (getConnectionSides()[i] == ForgeDirection.SOUTH)
            {
                for (int j = 1; j < 9; j ++)
                {
                    if (this.worldObj.getBlock(x, y, z + j).hasTileEntity())
                    {
                        if (this.worldObj.getTileEntity(x, y, z + j) instanceof TileEntityEssenceHandler)
                        {
                            if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(x, y, z + j)).isConnectionSide(getOppositeSite(ForgeDirection.SOUTH)))
                            {
                                Location connection = new Location(x, y, z + j);
                                connections.add(connection);
                            }
                        }
                    }
                    if (this.worldObj.getBlock(x, y, z + j) != Blocks.air)
                        break;
                }
            }

            if (getConnectionSides()[i] == ForgeDirection.WEST)
            {
                for (int j = 1; j < 9; j ++)
                {
                    if (this.worldObj.getBlock(x - j, y, z).hasTileEntity())
                    {
                        if (this.worldObj.getTileEntity(x - j, y, z) instanceof TileEntityEssenceHandler)
                        {
                            if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(x - j, y, z)).isConnectionSide(getOppositeSite(ForgeDirection.WEST)))
                            {
                                Location connection = new Location(x - j, y, z);
                                connections.add(connection);
                            }
                        }
                    }
                    if (this.worldObj.getBlock(x - j, y, z) != Blocks.air)
                        break;
                }
            }

            if (getConnectionSides()[i] == ForgeDirection.UP)
            {
                for (int j = 1; j < 9; j ++)
                {
                    if (this.worldObj.getBlock(x, y + j, z).hasTileEntity())
                    {
                        if (this.worldObj.getTileEntity(x, y + j, z) instanceof TileEntityEssenceHandler)
                        {
                            if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(x, y + j, z)).isConnectionSide(getOppositeSite(ForgeDirection.UP)))
                            {
                                Location connection = new Location(x, y + j, z);
                                connections.add(connection);
                            }
                        }
                    }
                    if (this.worldObj.getBlock(x, y + j, z) != Blocks.air)
                        break;
                }
            }

            if (getConnectionSides()[i] == ForgeDirection.DOWN)
            {
                for (int j = 1; j < 9; j ++)
                {
                    if (this.worldObj.getBlock(x, y - j, z).hasTileEntity())
                    {
                        if (this.worldObj.getTileEntity(x, y - j, z) instanceof TileEntityEssenceHandler)
                        {
                            if (((TileEntityEssenceHandler) this.worldObj.getTileEntity(x, y - j, z)).isConnectionSide(getOppositeSite(ForgeDirection.DOWN)))
                            {
                                Location connection = new Location(x, y - j, z);
                                connections.add(connection);
                            }
                        }
                    }
                    if (this.worldObj.getBlock(x, y - j, z) != Blocks.air)
                        break;
                }
            }

        }

        return connections;
    }

    public int getEssenceHandlerTypeOwn()
    {
        return this.EssenceHandlerType;
    }

    public int getEssenceHandlerType(Location loc)
    {
        TileEntityEssenceHandler tile = (TileEntityEssenceHandler) this.worldObj.getTileEntity(loc.x, loc.y, loc.z);
        return tile.getEssenceHandlerTypeOwn();
    }

    public boolean isConnected(Location loc, int type)
    {
        switch (type)
        {
            case 0:
                ArrayList<Location> connectedProvider = getConnectedProvider();
                for (Location provider : connectedProvider)
                {
                    if (provider.compareLocation(loc))
                        return true;
                }
                return false;
            case 1:
                ArrayList<Location> connectedRequester = getConnectedRequester();
                for (Location requester : connectedRequester)
                {
                    if (requester.compareLocation(loc))
                        return true;
                }
                return false;
            case 2:
                ArrayList<Location> connectedNode = getConnectedNodes();
                for (Location node : connectedNode)
                {
                    if (node.compareLocation(loc))
                        return true;
                }
                return false;
            default:
                return true;
        }
    }

    public void createConnection(Location loc, int type)
    {
        switch (type)
        {
            case 0:
                addConnectedProvider(loc);
                break;
            case 1:
                addConnectedRequester(loc);
                break;
            case 2:
                addConnectedNode(loc);
                break;
        }
    }

    public boolean connectionsExist()
    {
        if (!providerConnectionsExist())
            return false;
        if (!requesterConnectionsExist())
            return false;
        if (!nodeConnectionsExist())
            return false;
        return true;
    }

    public boolean providerConnectionsExist()
    {
        ArrayList<Location> provider = getConnectedProvider();
        for (Location loc : provider)
        {
            if (this.worldObj.getBlock(loc.x, loc.y, loc.z) == Blocks.air)
                return false;
        }
        return true;
    }

    public boolean requesterConnectionsExist()
    {
        ArrayList<Location> requester = getConnectedRequester();
        for (Location loc : requester)
        {
            if (this.worldObj.getBlock(loc.x, loc.y, loc.z) == Blocks.air)
                return false;
        }
        return true;
    }

    public boolean nodeConnectionsExist()
    {
        ArrayList<Location> nodes = getConnectedNodes();
        for (Location loc : nodes)
        {
            if (this.worldObj.getBlock(loc.x, loc.y, loc.z) == Blocks.air)
                return false;
        }
        return true;
    }

    public void resetEssenceNetwork()
    {
        cloneConnections();
        resetConnections();
        resetProvider();
        resetRequester();
        resetNodes();
    }

    public void cloneConnections()
    {
        this.clonedProvider = this.Provider;
        this.clonedRequester = this.Requester;
        this.clonedNodes = this.Nodes;
    }

    public ArrayList<Location> getClonedProvider()
    {
        return this.clonedProvider;
    }

    public ArrayList<Location> getClonedRequester()
    {
        return this.clonedRequester;
    }

    public ArrayList<Location> getClonedNodes()
    {
        return this.clonedNodes;
    }

    public void resetConnections()
    {
        this.Provider = new ArrayList<Location>();
        this.Requester = new ArrayList<Location>();
        this.Nodes = new ArrayList<Location>();
    }

    public void resetProvider()
    {
        ArrayList<Location> provider = getClonedProvider();

        for (Location loc : provider)
        {
            TileEntityEssenceHandler tile = (TileEntityEssenceHandler) this.worldObj.getTileEntity(loc.x, loc.y, loc.z);
            if (tile != null)
            {
                if (!tile.isReset)
                    tile.resetEssenceNetwork();
            }
        }
    }

    public void resetRequester()
    {
        ArrayList<Location> requester = getClonedRequester();

        for (Location loc : requester)
        {
            TileEntityEssenceHandler tile = (TileEntityEssenceHandler) this.worldObj.getTileEntity(loc.x, loc.y, loc.z);
            if (tile != null)
            {
                if (!tile.isReset)
                    tile.resetEssenceNetwork();
            }
        }
    }

    public void resetNodes()
    {
        ArrayList<Location> nodes = getClonedNodes();

        for (Location loc : nodes)
        {
            TileEntityEssenceHandler tile = (TileEntityEssenceHandler) this.worldObj.getTileEntity(loc.x, loc.y, loc.z);
            if (tile != null)
            {
                if (!tile.isReset)
                 tile.resetEssenceNetwork();
            }
        }
    }

    public void transferProviderConnections(ArrayList<Location> connections)
    {
        for (Location provider : connections)
        {
            if (!isConnected(provider, 0))
                addConnectedProvider(provider);
        }
    }

    public void transferRequesterConnections(ArrayList<Location> connections)
    {
        for (Location requester : connections)
        {
            if (!isConnected(requester, 1))
                addConnectedRequester(requester);
        }
    }


    public void addNodeConnections()
    {
        ArrayList<Location> ConnectedNodes = getConnectedNodes();

        for (Location node : ConnectedNodes)
        {
            if (this.worldObj.getBlock(node.x, node.y, node.z) != Blocks.air)
            {
                TileEntityEssenceHandler tile = (TileEntityEssenceHandler) this.worldObj.getTileEntity(node.x, node.y, node.z);
                transferProviderConnections(tile.getConnectedProvider());
                transferRequesterConnections(tile.getConnectedRequester());
            }
        }
    }
}
