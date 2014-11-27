package com.techmage.magetech.crafting;

import net.minecraft.item.crafting.IRecipe;

import java.util.Comparator;

public class ElectronicsWorkbenchRecipeSorter implements Comparator
{
    final ElectronicsWorkbenchCraftingManager electronicsWorkbench;

    public ElectronicsWorkbenchRecipeSorter(ElectronicsWorkbenchCraftingManager electronicsWorkbenchCraftingManager) {
        this.electronicsWorkbench = electronicsWorkbenchCraftingManager;
    }

    public int compareRecipes(IRecipe irecipe1, IRecipe irecipe2) {
        return irecipe1 instanceof ElectronicsWorkbenchShapelessRecipes && irecipe2 instanceof ElectronicsWorkbenchShapedRecipes ? 1:
                (irecipe2 instanceof ElectronicsWorkbenchShapelessRecipes && irecipe1 instanceof ElectronicsWorkbenchShapedRecipes ? -1 :
                (irecipe2.getRecipeSize() < irecipe1.getRecipeSize() ? -1 : (irecipe2.getRecipeSize() > irecipe1.getRecipeSize() ? 1 : 0)));
    }

    @Override
    public int compare(Object o1, Object o2) {
        return this.compareRecipes((IRecipe)o1, (IRecipe)o2);
    }

}
