package com.techmage.magetech.block;

import com.techmage.magetech.power.IConnector;
import com.techmage.magetech.tileentity.TileEntityMPWire;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMPWire extends BlockMageTech implements ITileEntityProvider
{
    public BlockMPWire()
    {
        super(Material.iron);
        this.setHardness(0.3F);
        float f = 0.3F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        boolean flag = this.canConnectTo(world, x, y, z - 1, x, y, z);
        boolean flag1 = this.canConnectTo(world, x, y, z + 1, x, y, z);
        boolean flag2 = this.canConnectTo(world, x - 1, y, z, x, y, z);
        boolean flag3 = this.canConnectTo(world, x + 1, y, z, x, y, z);
        float f = 0.2F;
        float f1 = 0.8F;
        float f2 = 0.2F;
        float f3 = 0.8F;

        if (flag)
        {
            f2 = 0.0F;
        }

        if (flag1)
        {
            f3 = 1.0F;
        }

        if (flag2)
        {
            f = 0.0F;
        }

        if (flag3)
        {
            f1 = 1.0F;
        }

        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }

    public boolean canConnectTo(IBlockAccess world, int x, int y, int z, int x1, int y1, int z1)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof IConnector)
        {
            //return ((IConnector) tile).canConnect((World) world, x1, y1, z1);
            return true;
           }
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int flag)
    {
        ((TileEntityMPWire) world.getTileEntity(x, y, z)).breakBlock();
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        ((TileEntityMPWire) world.getTileEntity(x, y, z)).onBlockAdded();
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityMPWire();
    }
}
