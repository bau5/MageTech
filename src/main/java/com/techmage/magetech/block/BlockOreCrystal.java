package com.techmage.magetech.block;

import com.techmage.magetech.init.ModItems;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockOreCrystal extends BlockMageTech_Magic
{

    public BlockOreCrystal()
    {
        super(Material.rock);
        this.setHardness(2.0f);
        this.setHarvestLevel("pickaxe", 3);
        this.setBlockName(Names.Blocks.ORECRYSTAL);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon (Textures.RESOURCE_PREFIX + Names.Blocks.ORECRYSTAL);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return ModItems.crystal;
    }

    public int quantityDropped(Random p_149745_1_)
    {
        return 2 + p_149745_1_.nextInt(2);
    }

}