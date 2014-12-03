package com.techmage.magetech.inventory;

import com.techmage.magetech.tileentity.TileEntityPowerFurnace;
import com.techmage.magetech.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerPowerFurnace extends ContainerMageTech
{
    private TileEntityPowerFurnace tileEntityPowerFurnace;
    private int lastCookingTime1;
    private int lastCookingTime2;
    private int lastPowerAmount;

    public ContainerPowerFurnace(InventoryPlayer inventoryPlayer, TileEntityPowerFurnace tileEntityPowerFurnace)
    {
        this.tileEntityPowerFurnace = tileEntityPowerFurnace;

        this.addSlotToContainer(new Slot(tileEntityPowerFurnace, 0, 56, 17) { });
        this.addSlotToContainer(new Slot(tileEntityPowerFurnace, 2, 56, 53) { });
        this.addSlotToContainer(new Slot(tileEntityPowerFurnace, 1, 109, 17)
        {
            @Override
            public boolean isItemValid(ItemStack itemStack)
            {
                return false;
            }

        });
        this.addSlotToContainer(new Slot(tileEntityPowerFurnace, 3, 109, 53)
        {
            @Override
            public boolean isItemValid(ItemStack itemStack)
            {
                return false;
            }

        });

        for (int l = 0; l < 3; l ++)
        {
            for (int i1 = 0; i1 < 9; i1 ++)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (int l = 0; l < 9; l ++)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, l, 8 + l * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityPowerFurnace.CookingTime1);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityPowerFurnace.CookingTime2);
        iCrafting.sendProgressBarUpdate(this, 2, this.tileEntityPowerFurnace.CurrentPower);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookingTime1 != this.tileEntityPowerFurnace.CookingTime1)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntityPowerFurnace.CookingTime1);
            }

            if (this.lastCookingTime2 != this.tileEntityPowerFurnace.CookingTime2)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntityPowerFurnace.CookingTime2);
            }

            if (this.lastPowerAmount != this.tileEntityPowerFurnace.CurrentPower)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntityPowerFurnace.CurrentPower);
            }
        }

        this.lastCookingTime1 = this.tileEntityPowerFurnace.CookingTime1;
        this.lastCookingTime2 = this.tileEntityPowerFurnace.CookingTime2;
        this.lastPowerAmount = this.tileEntityPowerFurnace.CurrentPower;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        if (valueType == 0)
        {
            this.tileEntityPowerFurnace.CookingTime1 = updatedValue;
        }

        if (valueType == 1)
        {
            this.tileEntityPowerFurnace.CookingTime2 = updatedValue;
        }

        if (valueType == 2)
        {
            this.tileEntityPowerFurnace.CurrentPower = updatedValue;
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

            if (p_82846_2_ == 1 || p_82846_2_ == 3)
            {
                if (!this.mergeItemStack(itemstack1, 4, 40, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ != 0 && p_82846_2_ != 2)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        if (!this.mergeItemStack(itemstack1, 1, 2, false))
                        {
                            return null;
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