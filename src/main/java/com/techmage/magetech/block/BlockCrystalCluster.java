package com.techmage.magetech.block;

import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import com.techmage.magetech.tileentity.TileEntityCrystalCluster;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCrystalCluster extends BlockMageTech_Magic implements ITileEntityProvider
{
    public BlockCrystalCluster()
    {
        super(Material.iron);
        this.setHardness(2.0f);
        this.setBlockName(Names.Blocks.CRYSTAL_CLUSTER);
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
        return new TileEntityCrystalCluster();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister)
    {
        topIcon = iconRegister.registerIcon (Textures.RESOURCE_PREFIX + Names.Blocks.CRYSTAL_CLUSTER + "_top");
        bottomIcon = iconRegister.registerIcon (Textures.RESOURCE_PREFIX + Names.Blocks.CRYSTAL_CLUSTER + "_bottom");
        sideIcon = iconRegister.registerIcon (Textures.RESOURCE_PREFIX + Names.Blocks.CRYSTAL_CLUSTER +  "_side");
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

