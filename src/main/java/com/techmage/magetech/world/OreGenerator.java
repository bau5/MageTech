package com.techmage.magetech.world;

import com.techmage.magetech.init.ModBlocks;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.utility.LogHelper;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class OreGenerator implements IWorldGenerator
{
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.dimensionId)
        {
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateEnd(World world, Random random, int x, int z) { }

    private void generateSurface(World world, Random random, int x, int z)
    {
        for (int i = 0; i < 15; i++)
        {
            int randPosX = x + random.nextInt(16);
            int randPosY = random.nextInt(48) + 48;
            int randPosZ = z + random.nextInt(16);

            (new WorldGenMinable(ModBlocks.ore, 0, 4, Blocks.stone)).generate(world, random, randPosX, randPosY, randPosZ);
        }

        for (int i = 0; i < 4; i++)
        {
            int randPosX = x + random.nextInt(16);
            int randPosY = random.nextInt(32) + 32;
            int randPosZ = z + random.nextInt(16);

            (new WorldGenMinable(ModBlocks.ore, 1, 6, Blocks.stone)).generate(world, random, randPosX, randPosY, randPosZ);
        }

        for (int i = 0; i < 20; i++)
        {
            int randPosX = x + random.nextInt(16);
            int randPosY = random.nextInt(64) + 64;
            int randPosZ = z + random.nextInt(16);

            (new WorldGenMinable(ModBlocks.ore, 2, 8, Blocks.stone)).generate(world, random, randPosX, randPosY, randPosZ);
        }

        for (int i = 0; i < 10; i++)
        {
            int randPosX = x + random.nextInt(16);
            int randPosY = random.nextInt(64) + 64;
            int randPosZ = z + random.nextInt(16);

            (new WorldGenMinable(ModBlocks.oreCrystal, 4, Blocks.stone)).generate(world, random, randPosX, randPosY, randPosZ);
        }

        for (int i = 0; i < 25; i++)
        {
            int randPosX = x + random.nextInt(16);
            int randPosY = random.nextInt(64) + 64;
            int randPosZ = z + random.nextInt(16);

            (new WorldGenMinable(ModBlocks.oreSilicon, 6, Blocks.stone)).generate(world, random, randPosX, randPosY, randPosZ);
        }
    }

    private void generateNether(World world, Random random, int x, int z) { }
}

