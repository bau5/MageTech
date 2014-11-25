package com.techmage.magetech.item;

import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;

public class ItemResin extends ItemMageTech
{
    public ItemResin()
    {
        this.setUnlocalizedName(Names.Items.RESIN);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons (IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon (Textures.RESOURCE_PREFIX + Names.Items.RESIN);
    }

}
