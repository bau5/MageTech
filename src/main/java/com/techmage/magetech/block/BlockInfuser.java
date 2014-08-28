package com.techmage.magetech.block;

import com.techmage.magetech.MageTech;
import com.techmage.magetech.reference.GuiID;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.tileentity.TileEntityInfuser;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInfuser extends BlockMageTech implements ITileEntityProvider
{
    public BlockInfuser()
    {
        super(Material.iron);
        this.setHardness(2.0f);
        this.setBlockName(Names.Blocks.INFUSER);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityInfuser();
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
                if (world.getTileEntity(x, y, z) instanceof TileEntityInfuser)
                {
                    player.openGui(MageTech.instance, GuiID.INFUSER.ordinal(), world, x, y, z);
                }
            }

            return true;
        }
    }
}

