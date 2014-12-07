package com.techmage.magetech.crafting;

import com.techmage.magetech.init.ModBlocks;
import com.techmage.magetech.init.ModItems;
import com.techmage.magetech.utility.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecipesInfuser
{

    private static final RecipesInfuser infusingBase = new RecipesInfuser();

    public static RecipesInfuser infusing()
    {
        return infusingBase;
    }

    public List<ItemStack> inputs1 = new ArrayList<ItemStack>();
    public List<ItemStack> inputs2 = new ArrayList<ItemStack>();
    public List<ItemStack> inputs3 = new ArrayList<ItemStack>();
    public List<ItemStack> outputs = new ArrayList<ItemStack>();
    public List<Integer> essence = new ArrayList<Integer>();

    private RecipesInfuser() { }

    public void addRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack output, int Essence)
    {
        outputs.add(output);
        inputs1.add(input1);
        inputs2.add(input2);
        inputs3.add(input3);
        essence.add(Essence);
    }

    public void addOreRecipe(String input1, ItemStack input2, ItemStack input3, ItemStack output, int Essence)
    {
        ArrayList<ItemStack> in = OreDictionary.getOres(input1);
        Iterator<ItemStack> it = in.iterator();
        while (it.hasNext())
        {
            outputs.add(output);
            inputs1.add(it.next());
            inputs2.add(input2);
            inputs3.add(input3);
            essence.add(Essence);
        }
    }

    public ItemStack getCraftingResult(ItemStack input1, ItemStack input2, ItemStack input3)
    {
        for (int i = 0; i < outputs.size(); i ++)
        {
            if (inputs1.get(i).isItemEqual(input1))
            {
                if (inputs2.get(i).isItemEqual(input2) && inputs3.get(i).isItemEqual(input3))
                    return outputs.get(i);
                if (inputs2.get(i).isItemEqual(input3) && inputs3.get(i).isItemEqual(input2))
                    return outputs.get(i);
            }
            if (inputs1.get(i).isItemEqual(input2))
            {
                if (inputs2.get(i).isItemEqual(input1) && inputs3.get(i).isItemEqual(input3))
                    return outputs.get(i);
                if (inputs2.get(i).isItemEqual(input3) && inputs3.get(i).isItemEqual(input1))
                    return outputs.get(i);
            }
            if (inputs1.get(i).isItemEqual(input3))
            {
                if (inputs2.get(i).isItemEqual(input1) && inputs3.get(i).isItemEqual(input2))
                    return outputs.get(i);
                if (inputs2.get(i).isItemEqual(input2) && inputs3.get(i).isItemEqual(input1))
                    return outputs.get(i);
            }
        }
        return null;
    }

    public int getEssenceCost(ItemStack input1, ItemStack input2, ItemStack input3)
    {
        for (int i = 0; i < outputs.size(); i ++)
        {
            if (inputs1.get(i).isItemEqual(input1))
            {
                if (inputs2.get(i).isItemEqual(input2) && inputs3.get(i).isItemEqual(input3))
                    return essence.get(i);
                if (inputs2.get(i).isItemEqual(input3) && inputs3.get(i).isItemEqual(input2))
                    return essence.get(i);
            }
            if (inputs1.get(i).isItemEqual(input2))
            {
                if (inputs2.get(i).isItemEqual(input1) && inputs3.get(i).isItemEqual(input3))
                    return essence.get(i);
                if (inputs2.get(i).isItemEqual(input3) && inputs3.get(i).isItemEqual(input1))
                    return essence.get(i);
            }
            if (inputs1.get(i).isItemEqual(input3))
            {
                if (inputs2.get(i).isItemEqual(input1) && inputs3.get(i).isItemEqual(input2))
                    return essence.get(i);
                if (inputs2.get(i).isItemEqual(input2) && inputs3.get(i).isItemEqual(input1))
                    return essence.get(i);
            }
        }
        return 0;
    }

    public boolean isCraftingComponent(ItemStack input)
    {
        for (int i = 0; i < outputs.size(); i ++)
        {
            if (inputs1.get(i).isItemEqual(input) || inputs2.get(i).isItemEqual(input) || inputs3.get(i).isItemEqual(input))
                return true;
        }
        return false;
    }

}
