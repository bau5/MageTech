package com.techmage.magetech.block;

import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class BlockOre extends BlockMageTech {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockOre() {
        super(Material.rock);
        this.setHardness(2.0f);
        this.setBlockName(Names.Blocks.ORE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta < 0 || meta >= this.icons.length) {
            meta = 0;
        }
        return this.icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[Names.Blocks.ORE_SUBTYPES.length];

        for (int i = 0; i < this.icons.length; i++) {
            icons[i] = iconRegister.registerIcon(Textures.RESOURCE_PREFIX + Names.Blocks.ORE + Names.Blocks.ORE_SUBTYPES[i]);
        }
    }

    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
        for (int meta = 0; meta < Names.Blocks.ORE_SUBTYPES.length; ++meta) {
            list.add(new ItemStack(this, 1, meta));
        }
    }
}
