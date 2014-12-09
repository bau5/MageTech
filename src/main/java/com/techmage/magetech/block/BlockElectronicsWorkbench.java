package com.techmage.magetech.block;

import com.techmage.magetech.MageTech;
import com.techmage.magetech.reference.GuiID;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockElectronicsWorkbench extends BlockMageTech_Tech
{
    public BlockElectronicsWorkbench()
    {
        super(Material.wood);
        this.setHardness(1.0f);
        this.setBlockName(Names.Blocks.ELECTRONICS_WORKBENCH);
    }

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon bottomIcon;
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;
    @SideOnly(Side.CLIENT)

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (!player.isSneaking())
        {
            player.openGui(MageTech.instance, GuiID.ELECTRONICS_WORKBENCH.ordinal(), world, x, y, z);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister)
    {
        topIcon = iconRegister.registerIcon (Textures.RESOURCE_PREFIX + Names.Blocks.ELECTRONICS_WORKBENCH + "_top");
        bottomIcon = iconRegister.registerIcon (Textures.RESOURCE_PREFIX + Names.Blocks.ELECTRONICS_WORKBENCH + "_bottom");
        sideIcon = iconRegister.registerIcon (Textures.RESOURCE_PREFIX + Names.Blocks.ELECTRONICS_WORKBENCH +  "_side");
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

