package com.techmage.magetech.init;

import com.techmage.magetech.crafting.ElectronicsWorkbenchCraftingManager;
import com.techmage.magetech.crafting.RecipesCentrifuge;
import com.techmage.magetech.crafting.RecipesCrusher;
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
        initElectronicsWorkbenchRecipes();
        initCrushingRecipes();
        initCentrifugeRecipes();
    }

    public static void initShapedRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.infuser), "lsl", "lcl", "SSS", 's', Blocks.stone_slab, 'S', Blocks.stone, 'c', ModItems.crystal, 'l', ModItems.ingot);
        GameRegistry.addRecipe(new ItemStack(ModItems.glassFibre, 6), "   ", "ggg", "   ", 'g', Blocks.glass);
        GameRegistry.addRecipe(new ItemStack(ModItems.glassFibreMat, 1), "   ", "gg ", "gg ", 'g', ModItems.glassFibre);
        GameRegistry.addRecipe(new ItemStack(ModItems.wireIron, 16), " i ", "isi", " i ", 'i', Items.iron_ingot, 's', Items.stick);
        GameRegistry.addRecipe(new ItemStack(ModItems.circuitBoard), "ggg", "rrr", "ggg", 'g', ModItems.glassFibreMat, 'r', ModItems.resin);
    }

    public static void initSmeltingRecipes()
    {
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModBlocks.ore, 1, 0), new ItemStack(ModItems.ingot, 1, 0), 1.0F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModBlocks.ore, 1, 1), new ItemStack(ModItems.ingot, 1, 1), 1.0F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModBlocks.ore, 1, 2), new ItemStack(ModItems.ingot, 1, 2), 0.7F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModItems.siliconOre, 1, 0), new ItemStack(ModItems.silicon, 1, 0), 0.5F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModItems.resin, 1, 0), new ItemStack(ModItems.rubber, 1, 0), 0.1F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModItems.rubber, 1, 0), new ItemStack(ModItems.plastic, 1, 0), 0.1F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModItems.dustOre, 1, 0), new ItemStack(Items.iron_ingot), 0.5F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModItems.dustOre, 1, 1), new ItemStack(Items.gold_ingot), 0.5F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModItems.dustOre, 1, 2), new ItemStack(ModItems.ingot, 1, 2), 0.5F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModItems.dustOre, 1, 3), new ItemStack(ModItems.ingot, 1, 0), 0.5F);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ModItems.dustOre, 1, 4), new ItemStack(ModItems.ingot, 1, 1), 0.5F);
    }

    public static void initInfusingRecipes()
    {
        RecipesInfuser.infusing().addRecipe(new ItemStack(ModItems.crystal, 1, 0), new ItemStack(Items.blaze_powder), new ItemStack(Items.magma_cream), new ItemStack(ModItems.crystal, 1, 1), 100);
        RecipesInfuser.infusing().addRecipe(new ItemStack(ModItems.crystal, 1, 0), new ItemStack(Items.potionitem), new ItemStack(Blocks.ice), new ItemStack(ModItems.crystal, 1, 2), 100);
        RecipesInfuser.infusing().addRecipe(new ItemStack(ModItems.crystal, 1, 0), new ItemStack(Blocks.stone), new ItemStack(Blocks.dirt), new ItemStack(ModItems.crystal, 1, 3), 100);
        RecipesInfuser.infusing().addRecipe(new ItemStack(ModItems.crystal, 1, 0), new ItemStack(Items.feather), new ItemStack(Items.ghast_tear), new ItemStack(ModItems.crystal, 1, 4), 100);
        RecipesInfuser.infusing().addOreRecipe("ingotLead", new ItemStack(Blocks.stone), new ItemStack(Blocks.stone), new ItemStack(ModBlocks.stoneHardened), 100);
    }

    public static void initElectronicsWorkbenchRecipes()
    {
        ElectronicsWorkbenchCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.transistor), "psp", "prp", "www",
                'p', ModItems.plastic, 's', ModItems.silicon, 'r', Items.redstone, 'w', ModItems.wireIron);
        ElectronicsWorkbenchCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.capacitor), " p ", "prp", "wsw",
                'p', ModItems.plastic, 'r', Items.redstone, 'w', ModItems.wireIron, 's', ModItems.silicon);
        ElectronicsWorkbenchCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.ic), "wpw", "srs", "wpw",
                'w', ModItems.wireIron, 'p', ModItems.plastic, 's', ModItems.silicon, 'r', Items.redstone);
    }

    public static void initCrushingRecipes()
    {
        RecipesCrusher.crushing().addRecipe(new ItemStack(ModItems.crushedOre, 1, 0), new ItemStack(Blocks.iron_ore), 2);
        RecipesCrusher.crushing().addRecipe(new ItemStack(ModItems.crushedOre, 1, 1), new ItemStack(Blocks.gold_ore), 2);
        RecipesCrusher.crushing().addRecipe(new ItemStack(ModItems.crushedOre, 1, 2), new ItemStack(Blocks.coal_ore), 3);
        RecipesCrusher.crushing().addRecipe(new ItemStack(ModItems.crushedOre, 1, 3), new ItemStack(Blocks.redstone_ore), 8);
        RecipesCrusher.crushing().addRecipe(new ItemStack(ModItems.crushedOre, 1, 4), new ItemStack(Blocks.lapis_ore), 8);
        RecipesCrusher.crushing().addRecipe(new ItemStack(ModItems.crushedOre, 1, 5), new ItemStack(Blocks.emerald_ore), 2);
        RecipesCrusher.crushing().addRecipe(new ItemStack(ModItems.crushedOre, 1, 6), new ItemStack(Blocks.diamond_ore), 2);
        RecipesCrusher.crushing().addOreRecipe(new ItemStack(ModItems.crushedOre, 1, 7), "oreCopper", 2);
        RecipesCrusher.crushing().addOreRecipe(new ItemStack(ModItems.crushedOre, 1, 8), "oreLead", 2);
        RecipesCrusher.crushing().addOreRecipe(new ItemStack(ModItems.crushedOre, 1, 9), "oreSilver", 2);
        RecipesCrusher.crushing().addRecipe(new ItemStack(ModItems.crushedOre, 1, 10), new ItemStack(ModBlocks.oreSilicon), 5);
        RecipesCrusher.crushing().addRecipe(new ItemStack(ModItems.crushedOre, 1, 11), new ItemStack(ModBlocks.oreCrystal), 5);
        RecipesCrusher.crushing().addRecipe(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.stone), 1);
        RecipesCrusher.crushing().addRecipe(new ItemStack(Blocks.gravel), new ItemStack(Blocks.cobblestone), 1);
        RecipesCrusher.crushing().addRecipe(new ItemStack(Blocks.sand), new ItemStack(Blocks.gravel), 1);
    }

    public static void initCentrifugeRecipes()
    {
        RecipesCentrifuge.centrifuge().addRecipe(new ItemStack(ModItems.dustOre, 1, 0), new ItemStack(ModItems.crushedOre, 1, 0));
        RecipesCentrifuge.centrifuge().addRecipe(new ItemStack(ModItems.dustOre, 1, 1), new ItemStack(ModItems.crushedOre, 1, 1));
        RecipesCentrifuge.centrifuge().addRecipe(new ItemStack(Items.coal), new ItemStack(ModItems.crushedOre, 1, 2));
        RecipesCentrifuge.centrifuge().addRecipe(new ItemStack(Items.redstone), new ItemStack(ModItems.crushedOre, 1, 3));
        RecipesCentrifuge.centrifuge().addRecipe(new ItemStack(Items.dye), new ItemStack(ModItems.crushedOre, 1, 4));
        RecipesCentrifuge.centrifuge().addRecipe(new ItemStack(Items.emerald), new ItemStack(ModItems.crushedOre, 1, 5));
        RecipesCentrifuge.centrifuge().addRecipe(new ItemStack(Items.diamond), new ItemStack(ModItems.crushedOre, 1, 6));
        RecipesCentrifuge.centrifuge().addRecipe(new ItemStack(ModItems.dustOre, 1, 2), new ItemStack(ModItems.crushedOre, 1, 7));
        RecipesCentrifuge.centrifuge().addRecipe(new ItemStack(ModItems.dustOre, 1, 3), new ItemStack(ModItems.crushedOre, 1, 8));
        RecipesCentrifuge.centrifuge().addRecipe(new ItemStack(ModItems.dustOre, 1, 4), new ItemStack(ModItems.crushedOre, 1, 9));
        RecipesCentrifuge.centrifuge().addRecipe(new ItemStack(ModItems.siliconOre), new ItemStack(ModItems.crushedOre, 1, 10));
        RecipesCentrifuge.centrifuge().addRecipe(new ItemStack(ModItems.crystal), new ItemStack(ModItems.crushedOre, 1, 11));
    }
}
