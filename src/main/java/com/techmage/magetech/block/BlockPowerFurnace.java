package com.techmage.magetech.block;

import com.techmage.magetech.MageTech;
import com.techmage.magetech.reference.GuiID;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import com.techmage.magetech.tileentity.TileEntityPowerFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPowerFurnace extends BlockMageTech_Tech implements ITileEntityProvider
{
    public BlockPowerFurnace()
    {
        super(Material.iron);
        this.setHardness(2.0f);
        this.setBlockName(Names.Blocks.POWERFURNACE);
    }

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon bottomIcon;
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;
    @SideOnly(Side.CLIENT)

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityPowerFurnace();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!world.isRemote)
            {
                if (world.getTileEntity(x, y, z) instanceof TileEntityPowerFurnace)
                {
                    player.openGui(MageTech.instance, GuiID.POWERFURNACE.ordinal(), world, x, y, z);
                }
            }

            return true;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister)
    {
        topIcon = iconRegister.registerIcon (Textures.RESOURCE_PREFIX + Names.Blocks.POWERFURNACE + "_top");
        bottomIcon = iconRegister.registerIcon (Textures.RESOURCE_PREFIX + Names.Blocks.POWERFURNACE + "_bottom");
        sideIcon = iconRegister.registerIcon (Textures.RESOURCE_PREFIX + Names.Blocks.POWERFURNACE +  "_side");
    }


    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int metadata)
    {
        if (side == 0)
        {
            return bottomIcon;
        }
        else if (side == 1)
        {
            return topIcon;
        }
        else
        {
            return sideIcon;
        }
    }

}