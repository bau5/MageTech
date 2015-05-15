package com.techmage.magetech.block.renderer;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemCrystalClusterRenderer implements IItemRenderer
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
                (new TileEntityCrystalClusterRenderer()).renderInventory(0.0D, 0.0D, 0.0D);
                break;
            default:
                (new TileEntityCrystalClusterRenderer()).renderHand(0.0D, 0.0D, 0.0D);
        }
    }

}
