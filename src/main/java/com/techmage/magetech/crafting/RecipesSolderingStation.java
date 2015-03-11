package com.techmage.magetech.crafting;

import com.techmage.magetech.utility.LogHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecipesSolderingStation
{

    private static final RecipesSolderingStation solderingBase = new RecipesSolderingStation();

    public static RecipesSolderingStation soldering()
    {
        return solderingBase;
    }

    ItemStack[][] recipe = new ItemStack[16][16];
    int[] solder = new int[recipe.length];

    private RecipesSolderingStation() { }

    public void addRecipe (ItemStack output, ItemStack ... input)
    {
        for (int i = 0; i < recipe.length; i ++)
        {
            if (recipe[i][0] == null)
            {
                recipe[i][0] = output;
                int items = 9;

                for (int j = 1; j < 10; j ++)
                {
                    recipe[i][j] = input[j - 1];
                    if (input[j - 1] == null)
                    {
                        items --;
                    }
                }
                solder[i] = items;
                break;
            }
        }
    }

    public ItemStack getCraftingResult (ItemStack[] input)
    {
        int correct = 0;

        for (int i = 0; i < recipe.length; i ++)
        {
            for (int j = 0; j < 9; j ++)
            {
                if (input[j] == null && recipe[i][j + 1] == null)
                {
                    correct ++;
                }
                else if (input[j] == null || recipe[i][j + 1] == null) { }
                else if (input[j].isItemEqual(recipe[i][j + 1]))
                {
                    correct ++;
                }
            }

            if (correct == 9)
            {
                return recipe[i][0];
            }

        }
        return null;
    }

    public int getNeededSolder (ItemStack output)
    {
        for (int i = 0; i < recipe.length; i ++)
        {
            if (recipe[i][0].isItemEqual(output))
            {
                return solder[i];
            }
        }
        return 0;
    }
}
