package com.techmage.magetech.block;

import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import com.techmage.magetech.tileentity.TileEntityCrystalCluster;
import com.techmage.magetech.tileentity.TileEntityEssenceNode;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockEssenceNode extends BlockMageTech_Magic implements ITileEntityProvider
{
    public BlockEssenceNode()
    {
        super(Material.iron);
        this.setHardness(2.0f);
        this.setBlockName(Names.Blocks.ESSENCE_NODE);
        this.useNeighborBrightness = true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityEssenceNode();
    }

    public int getRenderType()
    {
        return -1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

}

