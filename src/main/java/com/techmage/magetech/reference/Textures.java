package com.techmage.magetech.reference;

import com.techmage.magetech.utility.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Textures
{
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

    public static final class Gui
    {
        private static final String GUI_SHEET_LOCATION = "textures/gui/";
        public static final ResourceLocation INFUSER = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "infuser.png");
        public static final ResourceLocation CRUSHER = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "crusher.png");
    }

}
