package com.techmage.magetech.block;

import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class BlockItemOre extends ItemBlock
{

    public BlockItemOre(Block block)
    {
        super(block);
        this.hasSubtypes = true;
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, Names.Blocks.ORE);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("tile.%s%s%s", Textures.RESOURCE_PREFIX, Names.Blocks.ORE, Names.Blocks.ORE_SUBTYPES[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Names.Blocks.ORE_SUBTYPES.length - 1)]);
    }

    public int getMetadata(int meta)
    {
        return meta;
    }

}
