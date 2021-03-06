package com.techmage.magetech;

import com.techmage.magetech.handler.ConfigurationHandler;
import com.techmage.magetech.handler.GuiHandler;
import com.techmage.magetech.handler.KeyInputEventHandler;
import com.techmage.magetech.init.*;
import com.techmage.magetech.handler.PacketHandler;
import com.techmage.magetech.proxy.IProxy;
import com.techmage.magetech.reference.Reference;
import com.techmage.magetech.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME,  version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class MageTech
{
    @Mod.Instance(Reference.MOD_ID)
    public static MageTech instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        ModItems.init();

        ModBlocks.init();

        Entity.init();

        PacketHandler.init();

        LogHelper.info("Pre Initialization Complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());

        proxy.registerTileEntities();
        proxy.bindTileEntityRenderer();
        proxy.bindEntityRenderer();
        proxy.registerKeyBindings();

        Recipes.init();

        WorldGeneration.init();

        LogHelper.info("Initialization Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
