package com.techmage.magetech.handler;

import com.techmage.magetech.client.gui.inventory.GuiInfuser;
import com.techmage.magetech.inventory.ContainerInfuser;
import com.techmage.magetech.reference.GuiID;
import com.techmage.magetech.tileentity.TileEntityInfuser;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        if (id == GuiID.INFUSER.ordinal())
        {
            TileEntityInfuser tileEntityInfuser = (TileEntityInfuser) world.getTileEntity(x, y, z);
            return new ContainerInfuser(entityPlayer.inventory, tileEntityInfuser);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        if (id == GuiID.INFUSER.ordinal())
        {
            TileEntityInfuser tileEntityInfuser = (TileEntityInfuser) world.getTileEntity(x, y, z);
            return new GuiInfuser(entityPlayer.inventory, tileEntityInfuser);
        }

        return null;
    }
}