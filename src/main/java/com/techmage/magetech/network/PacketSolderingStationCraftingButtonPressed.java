package com.techmage.magetech.network;

import com.techmage.magetech.tileentity.TileEntityPowerFurnace;
import com.techmage.magetech.tileentity.TileEntitySolderingStation;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class PacketSolderingStationCraftingButtonPressed implements IMessage
{

    private int xCoord;
    private int yCoord;
    private int zCoord;
    private boolean mode;

    public PacketSolderingStationCraftingButtonPressed() { }

    public PacketSolderingStationCraftingButtonPressed(int xCoord, int yCoord, int zCoord, boolean mode)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
        this.mode = mode;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        xCoord = buf.readInt();
        yCoord = buf.readInt();
        zCoord = buf.readInt();
        mode = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(xCoord);
        buf.writeInt(yCoord);
        buf.writeInt(zCoord);
        buf.writeBoolean(mode);
    }

    public static class Handler implements IMessageHandler<PacketSolderingStationCraftingButtonPressed, IMessage>
    {
        @Override
        public IMessage onMessage(PacketSolderingStationCraftingButtonPressed msg, MessageContext ctx)
        {
            if (ctx.side == Side.SERVER)
            {
                TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(msg.xCoord, msg.yCoord, msg.zCoord);
                TileEntitySolderingStation solderingStation = (TileEntitySolderingStation) tile;
                ((TileEntitySolderingStation) tile).setCraftingButtonPressed(msg.mode);
                ctx.getServerHandler().playerEntity.worldObj.markBlockForUpdate(msg.xCoord, msg.yCoord, msg.zCoord);
            }
            return null;
        }
    }
}