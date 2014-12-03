package com.techmage.magetech.handler;

import com.techmage.magetech.client.gui.inventory.*;
import com.techmage.magetech.init.ModBlocks;
import com.techmage.magetech.inventory.*;
import com.techmage.magetech.reference.GuiID;
import com.techmage.magetech.tileentity.TileEntityCentrifuge;
import com.techmage.magetech.tileentity.TileEntityCrusher;
import com.techmage.magetech.tileentity.TileEntityInfuser;
import com.techmage.magetech.tileentity.TileEntityPowerFurnace;
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
        if (id == GuiID.CENTRIFUGE.ordinal())
        {
            TileEntityCentrifuge tileEntityCentrifuge = (TileEntityCentrifuge) world.getTileEntity(x, y, z);
            return new ContainerCentrifuge(entityPlayer.inventory, tileEntityCentrifuge);
        }
        if (id == GuiID.POWERFURNACE.ordinal())
        {
            TileEntityPowerFurnace tileEntityPowerFurnace = (TileEntityPowerFurnace) world.getTileEntity(x, y, z);
            return new ContainerPowerFurnace(entityPlayer.inventory, tileEntityPowerFurnace);
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
        if (id == GuiID.CENTRIFUGE.ordinal())
        {
            TileEntityCentrifuge tileEntityCentrifuge = (TileEntityCentrifuge) world.getTileEntity(x, y, z);
            return new GuiCentrifuge(entityPlayer.inventory, tileEntityCentrifuge);
        }
        if (id == GuiID.POWERFURNACE.ordinal())
        {
            TileEntityPowerFurnace tileEntityPowerFurnace = (TileEntityPowerFurnace) world.getTileEntity(x, y, z);
            return new GuiPowerFurnace(entityPlayer.inventory, tileEntityPowerFurnace);
        }

        return null;
    }
}