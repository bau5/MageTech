package com.techmage.magetech.crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecipesCentrifuge
{

    private static final RecipesCentrifuge centrifugeBase = new RecipesCentrifuge();

    public static RecipesCentrifuge centrifuge()
    {
        return centrifugeBase;
    }

    List<ItemStack> outputs = new ArrayList<ItemStack>();
    List<ItemStack> inputs = new ArrayList<ItemStack>();

    private RecipesCentrifuge() { }

    public void addRecipe(ItemStack output, ItemStack input)
    {
        outputs.add(output);
        inputs.add(input);
    }

    public ItemStack getCraftingResult(ItemStack input)
    {
        Iterator<ItemStack> it = inputs.iterator();
        while (it.hasNext())
        {
            if (inputs.get(inputs.size() - 1).isItemEqual(input))
                return outputs.get(outputs.size() - 1);
            else if (it.next().isItemEqual(input))
                return outputs.get(inputs.indexOf(it.next()) - 1);
        }
        return null;
    }

}
