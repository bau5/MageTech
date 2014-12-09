package com.techmage.magetech.network;

import com.techmage.magetech.tileentity.TileEntityPowerFurnace;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class PacketPowerFurnaceSetMode implements IMessage
{

    private int xCoord;
    private int yCoord;
    private int zCoord;
    private boolean mode;

    public PacketPowerFurnaceSetMode() { }

    public PacketPowerFurnaceSetMode(int xCoord, int yCoord, int zCoord, boolean mode)
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

    public static class Handler implements IMessageHandler<PacketPowerFurnaceSetMode, IMessage>
    {
        @Override
        public IMessage onMessage(PacketPowerFurnaceSetMode msg, MessageContext ctx)
        {
            if (ctx.side == Side.SERVER)
            {
                TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(msg.xCoord, msg.yCoord, msg.zCoord);
                TileEntityPowerFurnace powerFurnace = (TileEntityPowerFurnace) tile;
                ((TileEntityPowerFurnace) tile).setMode(msg.mode);
                ctx.getServerHandler().playerEntity.worldObj.markBlockForUpdate(msg.xCoord, msg.yCoord, msg.zCoord);
            }
            return null;
        }
    }
}