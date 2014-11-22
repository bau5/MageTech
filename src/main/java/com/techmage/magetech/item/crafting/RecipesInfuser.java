package com.techmage.magetech.item.crafting;

import com.techmage.magetech.init.ModBlocks;
import com.techmage.magetech.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class RecipesInfuser
{

    private static final RecipesInfuser infusingBase = new RecipesInfuser();

    public static RecipesInfuser infusing()
    {
        return infusingBase;
    }

    public ItemStack[][][][] InfusingRecipes = new ItemStack[8][8][8][8];
    public Integer[] InfusingEssence = new Integer[8];

    private RecipesInfuser()
    {
        this.addRecipe(new ItemStack(ModItems.crystal, 1, 0), new ItemStack(Items.blaze_powder), new ItemStack(Items.magma_cream), new ItemStack(ModItems.crystal, 1, 1), 100);
        this.addRecipe(new ItemStack(ModItems.crystal, 1, 0), new ItemStack(Items.potionitem), new ItemStack(Blocks.ice), new ItemStack(ModItems.crystal, 1, 2), 100);
        this.addRecipe(new ItemStack(ModItems.crystal, 1, 0), new ItemStack(Blocks.stone), new ItemStack(Blocks.dirt), new ItemStack(ModItems.crystal, 1, 3), 100);
        this.addRecipe(new ItemStack(ModItems.crystal, 1, 0), new ItemStack(Items.feather), new ItemStack(Items.ghast_tear), new ItemStack(ModItems.crystal, 1, 4), 100);
        this.addOreRecipe("ingotLead", new ItemStack(Blocks.stone), new ItemStack(Blocks.stone), new ItemStack(ModBlocks.stoneHardened), 100);
    }

    public void addRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack output, int Essence)
    {
        for (int i = 0; i < InfusingRecipes.length; i ++)
        {
            if (InfusingRecipes[i][0][0][0] == null)
            {
                InfusingRecipes[i][0][0][0] = output;
                InfusingRecipes[i][1][0][0] = input1;
                InfusingRecipes[i][1][1][0] = input2;
                InfusingRecipes[i][1][1][1] = input3;
                InfusingEssence[i] = Essence;
                break;
            }
        }
    }

    public void addOreRecipe(String input1, ItemStack input2, ItemStack input3, ItemStack output, int Essence)
    {
        ArrayList<ItemStack> inputs1 = OreDictionary.getOres(input1);
        ItemStack[] inputArray = inputs1.toArray(new ItemStack[inputs1.size()]);
        for (int i = 0; i < inputArray.length; i ++)
        {
            for (int j = 0; j < InfusingRecipes.length; j ++)
            {
                if (InfusingRecipes[j][0][0][0] == null)
                {
                    InfusingRecipes[j][0][0][0] = output;
                    InfusingRecipes[j][1][0][0] = inputArray[i];
                    InfusingRecipes[j][1][1][0] = input2;
                    InfusingRecipes[j][1][1][1] = input3;
                    InfusingEssence[j] = Essence;
                    break;
                }
            }
        }
    }

    public ItemStack getCraftingResult(ItemStack input1, ItemStack input2, ItemStack input3)
    {
        for (int i = 0; i < InfusingRecipes.length; i ++)
        {
            if (InfusingRecipes[i][0][0][0] != null)
            {
                if (InfusingRecipes[i][1][0][0].isItemEqual(input1))
                {
                    if (InfusingRecipes[i][1][1][0].isItemEqual(input2))
                    {
                        if (InfusingRecipes[i][1][1][1].isItemEqual(input3))
                        {
                            return InfusingRecipes[i][0][0][0];
                        }
                    }
                    else if (InfusingRecipes[i][1][1][0].isItemEqual(input3))
                    {
                        if (InfusingRecipes[i][1][1][1].isItemEqual(input2))
                        {
                            return InfusingRecipes[i][0][0][0];
                        }
                    }
                }

                else if (InfusingRecipes[i][1][0][0].isItemEqual(input2))
                {
                    if (InfusingRecipes[i][1][1][0].isItemEqual(input1))
                    {
                        if (InfusingRecipes[i][1][1][1].isItemEqual(input3))
                        {
                            return InfusingRecipes[i][0][0][0];
                        }
                    }
                    else if (InfusingRecipes[i][1][1][0].isItemEqual(input3))
                    {
                        if (InfusingRecipes[i][1][1][1].isItemEqual(input1))
                        {
                            return InfusingRecipes[i][0][0][0];
                        }
                    }
                }

                else if (InfusingRecipes[i][1][0][0].isItemEqual(input3))
                {
                    if (InfusingRecipes[i][1][1][0].isItemEqual(input1))
                    {
                        if (InfusingRecipes[i][1][1][1].isItemEqual(input2))
                        {
                            return InfusingRecipes[i][0][0][0];
                        }
                    }
                    else if (InfusingRecipes[i][1][1][0].isItemEqual(input2))
                    {
                        if (InfusingRecipes[i][1][1][1].isItemEqual(input1))
                        {
                            return InfusingRecipes[i][0][0][0];
                        }
                    }
                }
            }
        }

        return null;

    }

    public int getEssenceCost(ItemStack input1, ItemStack input2, ItemStack input3)
    {
        for (int i = 0; i < InfusingRecipes.length; i ++)
        {
            if (InfusingRecipes[i][0][0][0] != null)
            {
                if (InfusingRecipes[i][1][0][0].isItemEqual(input1))
                {
                    if (InfusingRecipes[i][1][1][0].isItemEqual(input2))
                    {
                        if (InfusingRecipes[i][1][1][1].isItemEqual(input3))
                        {
                            return InfusingEssence[i];
                        }
                    }
                    else if (InfusingRecipes[i][1][1][0].isItemEqual(input3))
                    {
                        if (InfusingRecipes[i][1][1][1].isItemEqual(input2))
                        {
                            return InfusingEssence[i];
                        }
                    }
                }

                else if (InfusingRecipes[i][1][0][0].isItemEqual(input2))
                {
                    if (InfusingRecipes[i][1][1][0].isItemEqual(input1))
                    {
                        if (InfusingRecipes[i][1][1][1].isItemEqual(input3))
                        {
                            return InfusingEssence[i];
                        }
                    }
                    else if (InfusingRecipes[i][1][1][0].isItemEqual(input3))
                    {
                        if (InfusingRecipes[i][1][1][1].isItemEqual(input1))
                        {
                            return InfusingEssence[i];
                        }
                    }
                }

                else if (InfusingRecipes[i][1][0][0].isItemEqual(input3))
                {
                    if (InfusingRecipes[i][1][1][0].isItemEqual(input1))
                    {
                        if (InfusingRecipes[i][1][1][1].isItemEqual(input2))
                        {
                            return InfusingEssence[i];
                        }
                    }
                    else if (InfusingRecipes[i][1][1][0].isItemEqual(input2))
                    {
                        if (InfusingRecipes[i][1][1][1].isItemEqual(input1))
                        {
                            return InfusingEssence[i];
                        }
                    }
                }
            }
        }

        return 0;

    }

}
