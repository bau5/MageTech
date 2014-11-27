package com.techmage.magetech.handler;

import com.techmage.magetech.client.gui.inventory.GuiCrusher;
import com.techmage.magetech.client.gui.inventory.GuiElectronicsWorkbench;
import com.techmage.magetech.client.gui.inventory.GuiInfuser;
import com.techmage.magetech.init.ModBlocks;
import com.techmage.magetech.inventory.ContainerCrusher;
import com.techmage.magetech.inventory.ContainerElectronicsWorkbench;
import com.techmage.magetech.inventory.ContainerInfuser;
import com.techmage.magetech.reference.GuiID;
import com.techmage.magetech.tileentity.TileEntityCrusher;
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
        if (id == GuiID.CRUSHER.ordinal())
        {
            TileEntityCrusher tileEntityCrusher = (TileEntityCrusher) world.getTileEntity(x, y, z);
            return new ContainerCrusher(entityPlayer.inventory, tileEntityCrusher);
        }
        if (id == GuiID.ELECTRONICS_WORKBENCH.ordinal())
        {
            return id == GuiID.ELECTRONICS_WORKBENCH.ordinal() && world.getBlock(x, y, z) == ModBlocks.electronicsWorkbench ? new ContainerElectronicsWorkbench(entityPlayer.inventory, world, x, y, z) : null;
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
        if (id == GuiID.CRUSHER.ordinal())
        {
            TileEntityCrusher tileEntityCrusher = (TileEntityCrusher) world.getTileEntity(x, y, z);
            return new GuiCrusher(entityPlayer.inventory, tileEntityCrusher);
        }
        if (id == GuiID.ELECTRONICS_WORKBENCH.ordinal())
        {
            return id == GuiID.ELECTRONICS_WORKBENCH.ordinal() && world.getBlock(x, y, z) == ModBlocks.electronicsWorkbench ? new GuiElectronicsWorkbench(entityPlayer.inventory, world, x, y, z) : null;
        }

        return null;
    }
}