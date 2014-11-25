package com.techmage.magetech.inventory;

import com.techmage.magetech.item.ItemCrystal;
import com.techmage.magetech.tileentity.TileEntityCrusher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCrusher extends ContainerMageTech
{
    private TileEntityCrusher tileEntityCrusher;
    private int lastCrushingTime;
    private int lastPowerAmount;

    public ContainerCrusher(InventoryPlayer inventoryPlayer, TileEntityCrusher tileEntityCrusher)
    {
        this.tileEntityCrusher = tileEntityCrusher;

        this.addSlotToContainer(new Slot(tileEntityCrusher, 0, 80, 22) { });
        this.addSlotToContainer(new Slot(tileEntityCrusher, 1, 80, 70)
        {
            @Override
            public boolean isItemValid(ItemStack itemStack)
            {
                return false;
            }

        });

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 158 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 216));
        }
    }

    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityCrusher.CrushingTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityCrusher.CurrentPower);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCrushingTime != this.tileEntityCrusher.CrushingTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntityCrusher.CrushingTime);
            }

            if (this.lastPowerAmount != this.tileEntityCrusher.CurrentPower)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntityCrusher.CurrentPower);
            }
        }

        this.lastCrushingTime = this.tileEntityCrusher.CrushingTime;
        this.lastPowerAmount = this.tileEntityCrusher.CurrentPower;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        if (valueType == 0)
        {
            this.tileEntityCrusher.CrushingTime = updatedValue;
        }

        if (valueType == 1)
        {
            this.tileEntityCrusher.CurrentPower = updatedValue;
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {
        ItemStack itemStack = null;

        return itemStack;
    }
}