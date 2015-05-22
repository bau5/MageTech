package com.techmage.magetech.tileentity;

import com.techmage.magetech.entity.EntityEssenceBeam;
import com.techmage.magetech.utility.Location;
import com.techmage.magetech.utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import java.util.ArrayList;

public class TileEntityEssenceNode extends TileEntityEssenceHandler
{

    public boolean init = false;

    private static final float ESSENCEBEAM_OFFSET = 2.0F / 16.0F;

    public int storedEssence = 0;
    public int maxEssence = 50;

    public ArrayList<Location> dirConnections = new ArrayList<Location>();

    public ArrayList<Location> Provider = new ArrayList<Location>();
    public ArrayList<Location> Requester = new ArrayList<Location>();
    public ArrayList<Location> Nodes = new ArrayList<Location>();

    public ArrayList<Location> clonedProvider = new ArrayList<Location>();
    public ArrayList<Location> clonedRequester = new ArrayList<Location>();
    public ArrayList<Location> clonedNodes = new ArrayList<Location>();

    public Location essenceBeamTarget = new Location(0, 0, 0);

    public boolean isReset = false;
    public int EssenceHandlerType = 2;

    public TileEntityEssenceNode()
    {
        this.setConnectionSides(ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.UP, ForgeDirection.DOWN);
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
    public ArrayList<Location> getDirConnections()
    {
        return this.dirConnections;
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
            dirConnections = Connections;

            for (Location loc : Connections)
            {
                int type = getEssenceHandlerType(loc);

                if (!isConnected(loc, type))
                {
                    createConnection(loc, type);
                    World world = Minecraft.getMinecraft().theWorld;
                    EntityEssenceBeam essenceBeam = new EntityEssenceBeam(world, new Location(this.xCoord, this.yCoord, this.zCoord), loc);
                    if (world != null)
                        world.spawnEntityInWorld(essenceBeam);
                }
            }

            addNodeConnections();

        }
    }
}

