package com.techmage.magetech.inventory;

import com.techmage.magetech.crafting.RecipesCentrifuge;
import com.techmage.magetech.tileentity.TileEntityCentrifuge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCentrifuge extends ContainerMageTech
{
    private TileEntityCentrifuge tileEntityCentrifuge;
    private int lastCentrifugeTime;
    private int lastPowerAmount;

    public ContainerCentrifuge(InventoryPlayer inventoryPlayer, TileEntityCentrifuge tileEntityCentrifuge)
    {
        this.tileEntityCentrifuge = tileEntityCentrifuge;

        this.addSlotToContainer(new Slot(tileEntityCentrifuge, 0, 56, 35) { });
        this.addSlotToContainer(new Slot(tileEntityCentrifuge, 1, 109, 35)
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
        iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityCentrifuge.CentrifugeTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityCentrifuge.CurrentPower);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCentrifugeTime != this.tileEntityCentrifuge.CentrifugeTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntityCentrifuge.CentrifugeTime);
            }

            if (this.lastPowerAmount != this.tileEntityCentrifuge.CurrentPower)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntityCentrifuge.CurrentPower);
            }
        }

        this.lastCentrifugeTime = this.tileEntityCentrifuge.CentrifugeTime;
        this.lastPowerAmount = this.tileEntityCentrifuge.CurrentPower;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        if (valueType == 0)
        {
            this.tileEntityCentrifuge.CentrifugeTime = updatedValue;
        }

        if (valueType == 1)
        {
            this.tileEntityCentrifuge.CurrentPower = updatedValue;
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

            if (p_82846_2_ == 1)
            {
                if (!this.mergeItemStack(itemstack1, 2, 38, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ != 0)
            {
                if (RecipesCentrifuge.centrifuge().getCraftingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 2 && p_82846_2_ < 29)
                {
                    if (!this.mergeItemStack(itemstack1, 29, 38, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 29 && p_82846_2_ < 38 && !this.mergeItemStack(itemstack1, 2, 29, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 2, 38, false))
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