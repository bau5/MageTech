package com.techmage.magetech.client.render.item;

import com.techmage.magetech.client.render.tileentity.TileEntityEssenceNodeRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemEssenceNodeRenderer implements IItemRenderer
{

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
            case INVENTORY:
                (new TileEntityEssenceNodeRenderer()).renderInventory(0.0D, 0.0D, 0.0D);
                break;
            default:
                (new TileEntityEssenceNodeRenderer()).renderHand(0.0D, 0.0D, 0.0D);
        }
    }

}
