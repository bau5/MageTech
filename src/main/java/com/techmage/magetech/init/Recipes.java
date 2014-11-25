package com.techmage.magetech.init;

import com.techmage.magetech.crafting.RecipesInfuser;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class Recipes
{

    public static void init()
    {
        initShapedRecipes();
        initSmeltingRecipes();
        initInfusingRecipes();
    }

    public static void initShapedRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.infuser), "lsl", "lcl", "SSS", 's', Blocks.stone_slab, 'S', Blocks.stone, 'c', ModItems.crystal, 'l', ModItems.ingot);
        GameRegistry.addRecipe(new ItemStack(ModItems.glassFibre, 6), "   ", "ggg", "   ", 'g', Blocks.glass);
        GameRegistry.addRecipe(new ItemStack(ModItems.glassFibreMat, 1), "   ", "gg ", "gg ", 'g', ModItems.glassFibre);
        GameRegistry.addRecipe(new ItemStack(ModItems.wireIron, 16), " i ", "isi", " i ", 'i', Items.iron_ingot, 's', Items.stick);
        GameRegistry.addRecipe(new ItemStack(ModItems.ic), "wpw", "srs", "wpw", 'w', ModItems.wireIron, 'p', ModItems.plastic, 's', ModItems.silicon, 'r', Items.redstone);
        GameRegistry.addRecipe(new ItemStack(ModItems.capacitor), " p ", "prp", "wsw", 'p', ModItems.plastic, 'r', Items.redstone, 'w', ModItems.wireIron, 's', ModItems.silicon);
        GameRegistry.addRecipe(new ItemStack(ModItems.transistor), "psp", "prp", "www", 'p', ModItems.plastic, 's', ModItems.silicon, 'r', Items.redstone, 'w', ModItems.wireIron);
    }

    public static void initSmeltingRecipes()
    {
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModBlocks.ore, 1, 0), new ItemStack(ModItems.ingot, 1, 0), 1.0F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModBlocks.ore, 1, 1), new ItemStack(ModItems.ingot, 1, 1), 1.0F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModBlocks.ore, 1, 2), new ItemStack(ModItems.ingot, 1, 2), 0.7F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModItems.siliconOre, 1, 0), new ItemStack(ModItems.silicon, 1, 0), 0.5F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModItems.resin, 1, 0), new ItemStack(ModItems.plastic, 1, 0), 0.5F);
    }

    public static void initInfusingRecipes()
    {
        RecipesInfuser.infusing().addRecipe(new ItemStack(ModItems.crystal, 1, 0), new ItemStack(Items.blaze_powder), new ItemStack(Items.magma_cream), new ItemStack(ModItems.crystal, 1, 1), 100);
        RecipesInfuser.infusing().addRecipe(new ItemStack(ModItems.crystal, 1, 0), new ItemStack(Items.potionitem), new ItemStack(Blocks.ice), new ItemStack(ModItems.crystal, 1, 2), 100);
        RecipesInfuser.infusing().addRecipe(new ItemStack(ModItems.crystal, 1, 0), new ItemStack(Blocks.stone), new ItemStack(Blocks.dirt), new ItemStack(ModItems.crystal, 1, 3), 100);
        RecipesInfuser.infusing().addRecipe(new ItemStack(ModItems.crystal, 1, 0), new ItemStack(Items.feather), new ItemStack(Items.ghast_tear), new ItemStack(ModItems.crystal, 1, 4), 100);
        RecipesInfuser.infusing().addOreRecipe("ingotLead", new ItemStack(Blocks.stone), new ItemStack(Blocks.stone), new ItemStack(ModBlocks.stoneHardened), 100);
    }

}
