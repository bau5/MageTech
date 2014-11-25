package com.techmage.magetech.proxy;

import com.techmage.magetech.init.ModBlocks;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.renderer.ItemRendererMPWire;
import com.techmage.magetech.renderer.TileEntityMPWireRenderer;
import com.techmage.magetech.tileentity.TileEntityCrusher;
import com.techmage.magetech.tileentity.TileEntityInfuser;
import com.techmage.magetech.tileentity.TileEntityMPWire;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{

    @Override
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityInfuser.class, Names.Blocks.INFUSER);
        GameRegistry.registerTileEntity(TileEntityCrusher.class, Names.Blocks.CRUSHER);

        ClientRegistry.registerTileEntity(TileEntityMPWire.class, Names.Blocks.MPWIRE, new TileEntityMPWireRenderer());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.mpWire), new ItemRendererMPWire());
    }
}
