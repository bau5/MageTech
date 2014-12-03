package com.techmage.magetech.crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class RecipesCentrifuge
{

    private static final RecipesCentrifuge centrifugeBase = new RecipesCentrifuge();

    public static RecipesCentrifuge centrifuge()
    {
        return centrifugeBase;
    }

    public ItemStack[][] CentrifugeRecipes = new ItemStack[16][16];

    private RecipesCentrifuge() { }

    public void addRecipe(ItemStack output, ItemStack input)
    {
        for (int i = 0; i < CentrifugeRecipes.length; i ++)
        {
            if (CentrifugeRecipes[i][0] == null)
            {
                CentrifugeRecipes[i][0] = output;
                CentrifugeRecipes[i][1] = input;
                break;
            }
            else if (CentrifugeRecipes[i][0] == output)
            {
                for (int j = 0; j < CentrifugeRecipes.length; j ++)
                {
                    if (CentrifugeRecipes[i][j] == null)
                    {
                        CentrifugeRecipes[i][j] = input;
                        break;
                    }
                }
            }
        }
    }

    public ItemStack getCraftingResult(ItemStack input)
    {
        for (int i = 0; i < CentrifugeRecipes.length; i ++)
        {
            for (int j = 0; j < CentrifugeRecipes.length; j ++)
            {
                if (CentrifugeRecipes[i][j] != null)
                {
                    if (CentrifugeRecipes[i][j].isItemEqual(input))
                    {
                        return CentrifugeRecipes[i][0];
                    }
                }
            }
        }
        return null;
    }

}
