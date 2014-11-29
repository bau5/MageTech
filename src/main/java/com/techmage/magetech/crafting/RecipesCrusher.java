package com.techmage.magetech.crafting;

import com.techmage.magetech.utility.LogHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class RecipesCrusher
{

    private static final RecipesCrusher crushingBase = new RecipesCrusher();

    public static RecipesCrusher crushing()
    {
        return crushingBase;
    }

    public ItemStack[][] CrushingRecipes = new ItemStack[16][16];
    public int[] OutputSize = new int[CrushingRecipes.length];

    private RecipesCrusher() { }

    public void addRecipe(ItemStack output, ItemStack input, int outputSize)
    {
        for (int i = 0; i < CrushingRecipes.length; i ++)
        {
            if (CrushingRecipes[i][0] == null)
            {
                CrushingRecipes[i][0] = output;
                CrushingRecipes[i][1] = input;
                OutputSize[i] = outputSize;
                break;
            }
            else if (CrushingRecipes[i][0] == output)
            {
                for (int j = 0; j < CrushingRecipes.length; j ++)
                {
                    if (CrushingRecipes[i][j] == null)
                    {
                        CrushingRecipes[i][j] = input;
                        break;
                    }
                }
            }
        }
    }

    public void addOreRecipe(ItemStack output, String input, int outputSize)
    {
        ArrayList<ItemStack> inputs = OreDictionary.getOres(input);
        ItemStack[] inputArray = inputs.toArray(new ItemStack[inputs.size()]);
        for (int i = 0; i < inputArray.length; i ++)
        {
            for (int j = 0; j < CrushingRecipes.length; j ++)
            {
                if (CrushingRecipes[j][0] == null)
                {
                    CrushingRecipes[j][0] = output;
                    CrushingRecipes[j][1] = inputArray[i];
                    OutputSize[i] = outputSize;
                    break;
                }
                if (CrushingRecipes[j][0].isItemEqual(output))
                {
                    for (int k = 0; k < CrushingRecipes.length; k ++)
                    {
                        if (CrushingRecipes[j][k] == null)
                        {
                            CrushingRecipes[j][k] = inputArray[i];
                            break;
                        }
                    }
                }
            }
        }
    }

    public ItemStack getCraftingResult(ItemStack input)
    {
        for (int i = 0; i < CrushingRecipes.length; i ++)
        {
            for (int j = 0; j < CrushingRecipes.length; j ++)
            {
                if (CrushingRecipes[i][j] != null)
                {
                    if (CrushingRecipes[i][j].isItemEqual(input))
                    {
                        return CrushingRecipes[i][0];
                    }
                }
            }
        }
        return null;
    }

    public int getOutputSize(ItemStack output)
    {
        for (int i = 0; i < CrushingRecipes.length; i ++)
        {
            if (CrushingRecipes[i][0].isItemEqual(output))
            {
                return OutputSize[i];
            }
        }
        return 0;
    }

}
