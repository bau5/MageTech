package com.techmage.magetech.crafting;

import com.techmage.magetech.utility.LogHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecipesCrusher
{

    private static final RecipesCrusher crushingBase = new RecipesCrusher();

    public static RecipesCrusher crushing()
    {
        return crushingBase;
    }

    List<ItemStack> outputs = new ArrayList<ItemStack>();
    List<ItemStack> inputs = new ArrayList<ItemStack>();
    List<Integer> size = new ArrayList<Integer>();

    private RecipesCrusher() { }

    public void addRecipe(ItemStack output, ItemStack input, int outputSize)
    {
        outputs.add(output);
        inputs.add(input);
        size.add(outputSize);
    }

    public void addOreRecipe(ItemStack output, String input, int outputSize)
    {
        ArrayList<ItemStack> in = OreDictionary.getOres(input);
        Iterator<ItemStack> it = in.iterator();
        while (it.hasNext())
        {
            outputs.add(output);
            inputs.add(it.next());
            size.add(outputSize);
        }
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

    public int getOutputSize(ItemStack output)
    {
        return size.get(outputs.indexOf(output));
    }

}
