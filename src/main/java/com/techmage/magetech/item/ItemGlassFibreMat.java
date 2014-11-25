package com.techmage.magetech.item;

import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;

public class ItemGlassFibreMat extends ItemMageTech
{
    public ItemGlassFibreMat()
    {
        this.setUnlocalizedName(Names.Items.GLASSFIBREMAT);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons (IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon (Textures.RESOURCE_PREFIX + Names.Items.GLASSFIBREMAT);
    }

}
