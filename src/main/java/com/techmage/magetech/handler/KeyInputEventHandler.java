package com.techmage.magetech.handler;

import com.techmage.magetech.client.settings.KeyBindings;
import com.techmage.magetech.init.ModItems;
import com.techmage.magetech.reference.Key;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class KeyInputEventHandler
{

    private static Key getPressedKeyBinding()
    {
        if (KeyBindings.talismanSwitch.isPressed())
        {
            return Key.TALISMANSWITCH;
        }

        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {

        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        switch (getPressedKeyBinding())
        {
            case TALISMANSWITCH:
                if (player.inventory.getCurrentItem() != null)
                {
                    if (player.inventory.getCurrentItem().getItem() == ModItems.talismanAir)
                    {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModItems.talismanFire));
                        break;
                    }
                    if (player.inventory.getCurrentItem().getItem() == ModItems.talismanFire)
                    {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModItems.talismanAir));
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }

}
