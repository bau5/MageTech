package com.techmage.magetech.init;

import com.techmage.magetech.item.crafting.RecipesInfuser;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes
{

    public static void init()
    {
        initModRecipes();
    }

    public static void initModRecipes()
    {

        GameRegistry.addRecipe(new ItemStack(ModBlocks.infuser), "lsl", "lcl", "SSS", 's', Blocks.stone_slab, 'S', Blocks.stone, 'c', ModItems.crystal, 'l', ModItems.ingot);

    }

}
