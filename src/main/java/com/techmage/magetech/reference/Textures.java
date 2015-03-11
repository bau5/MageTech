package com.techmage.magetech.reference;

import com.techmage.magetech.utility.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Textures
{
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

    public static final class Gui
    {
        private static final String GUI_SHEET_LOCATION = "textures/gui/";

        // Tech

        public static final ResourceLocation CRUSHER = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "crusher.png");
        public static final ResourceLocation CENTRIFUGE = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "centrifuge.png");
        public static final ResourceLocation POWERFURNACE = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "powerFurnace.png");
        public static final ResourceLocation ELECTRONICS_WORKBENCH = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "electronicsWorkbench.png");
        public static final ResourceLocation SOLDERING_STATION = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "solderingStation.png");

        // Magic
        public static final ResourceLocation INFUSER = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "infuser.png");

    }

}
