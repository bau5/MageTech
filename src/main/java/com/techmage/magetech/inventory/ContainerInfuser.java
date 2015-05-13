package com.techmage.magetech.inventory;

import com.techmage.magetech.crafting.RecipesInfuser;
import com.techmage.magetech.item.ItemCrystal;
import com.techmage.magetech.tileentity.TileEntityInfuser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerInfuser extends ContainerMageTech
{
    private TileEntityInfuser tileEntityInfuser;
    private int lastCrushingTime;
    private int lastEssenceAmount;

    public ContainerInfuser(InventoryPlayer inventoryPlayer, TileEntityInfuser tileEntityInfuser)
    {
        this.tileEntityInfuser = tileEntityInfuser;

        this.addSlotToContainer(new Slot(tileEntityInfuser, 0, 80, 22) { });
        this.addSlotToContainer(new Slot(tileEntityInfuser, 1, 37, 106) { });
        this.addSlotToContainer(new Slot(tileEntityInfuser, 2, 122, 106) { });
        this.addSlotToContainer(new Slot(tileEntityInfuser, 3, 80, 70)
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
        iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityInfuser.InfusionTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityInfuser.storedEssence);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCrushingTime != this.tileEntityInfuser.InfusionTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntityInfuser.InfusionTime);
            }

            if (this.lastEssenceAmount != this.tileEntityInfuser.storedEssence)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntityInfuser.storedEssence);
            }
        }

        this.lastCrushingTime = this.tileEntityInfuser.InfusionTime;
        this.lastEssenceAmount = this.tileEntityInfuser.storedEssence;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        if (valueType == 0)
        {
            this.tileEntityInfuser.InfusionTime = updatedValue;
        }

        if (valueType == 1)
        {
            this.tileEntityInfuser.storedEssence = updatedValue;
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 3)
            {
                if (!this.mergeItemStack(itemstack1, 4, 40, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ != 0 && p_82846_2_ != 1 && p_82846_2_ != 2)
            {
                if (RecipesInfuser.infusing().isCraftingComponent(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        if (!this.mergeItemStack(itemstack1, 1, 2, false))
                        {
                            if (!this.mergeItemStack(itemstack1, 2, 3, false))
                            {
                                return null;
                            }
                        }
                    }
                }
                else if (p_82846_2_ >= 4 && p_82846_2_ < 31)
                {
                    if (!this.mergeItemStack(itemstack1, 31, 40, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 31 && p_82846_2_ < 40 && !this.mergeItemStack(itemstack1, 4, 31, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 4, 40, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }

        return itemstack;
    }
}