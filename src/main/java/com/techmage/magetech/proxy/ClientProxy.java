package com.techmage.magetech.proxy;

import com.techmage.magetech.client.render.item.ItemCrystalClusterRenderer;
import com.techmage.magetech.client.render.item.ItemEssenceNodeRenderer;
import com.techmage.magetech.client.render.tileentity.TileEntityCrystalClusterRenderer;
import com.techmage.magetech.client.render.tileentity.TileEntityEssenceNodeRenderer;
import com.techmage.magetech.init.ModBlocks;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.tileentity.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{

    @Override
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCrusher.class, Names.Blocks.CRUSHER);
        GameRegistry.registerTileEntity(TileEntityCentrifuge.class, Names.Blocks.CENTRIFUGE);
        GameRegistry.registerTileEntity(TileEntityPowerFurnace.class, Names.Blocks.POWERFURNACE);
        GameRegistry.registerTileEntity(TileEntitySolderingStation.class, Names.Blocks.SOLDERING_STATION);

        GameRegistry.registerTileEntity(TileEntityInfuser.class, Names.Blocks.INFUSER);
        GameRegistry.registerTileEntity(TileEntityElementalExtractor.class, Names.Blocks.ELEMENTAL_EXTRACTOR);
        GameRegistry.registerTileEntity(TileEntityCrystalCluster.class, Names.Blocks.CRYSTAL_CLUSTER);
        GameRegistry.registerTileEntity(TileEntityEssenceNode.class, Names.Blocks.ESSENCE_NODE);
    }

    @Override
    public void bindTileEntityRenderer()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystalCluster.class, new TileEntityCrystalClusterRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.crystalCluster), new ItemCrystalClusterRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEssenceNode.class, new TileEntityEssenceNodeRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.essenceNode), new ItemEssenceNodeRenderer());
    }
}
