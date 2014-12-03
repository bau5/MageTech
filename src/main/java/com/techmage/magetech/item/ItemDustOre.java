package com.techmage.magetech.item;

import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;

public class ItemDustOre extends ItemMageTech
{
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public ItemDustOre()
    {
        super();
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Names.Items.DUSTORE);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, Names.Items.DUSTORE);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s%s", Textures.RESOURCE_PREFIX, Names.Items.DUSTORE, Names.Items.DUSTORE_SUBTYPES[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Names.Items.DUSTORE_SUBTYPES.length - 1)]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < Names.Items.DUSTORE_SUBTYPES.length; ++meta)
        {
            list.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        return icons[MathHelper.clamp_int(meta, 0, Names.Items.DUSTORE_SUBTYPES.length - 1)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[Names.Items.DUSTORE_SUBTYPES.length];

        for (int i = 0; i < Names.Items.DUSTORE_SUBTYPES.length; i++)
        {
            icons[i] = iconRegister.registerIcon(Textures.RESOURCE_PREFIX + Names.Items.DUSTORE + Names.Items.DUSTORE_SUBTYPES[i]);
        }
    }
}

