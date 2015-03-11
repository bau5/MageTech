package com.techmage.magetech.tileentity;

import com.techmage.magetech.crafting.RecipesSolderingStation;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntitySolderingStation extends TileEntityMageTech implements ISidedInventory
{
    public static final int INVENTORY_SIZE = 12;

    private static final int[] slotsTop = new int[] {1, 2};
    private static final int[] slotsBottom = new int[] {0};
    private static final int[] slotsSides = new int[] {3, 4, 5, 6, 7, 8, 9, 10, 11};

    private ItemStack[] inventory;

    public int MaxPower = 1000;
    public int CurrentPower = 1000;

    public TileEntitySolderingStation()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= decrementAmount)
            {
                setInventorySlotContents(slotIndex, null);
            }
            else
            {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.SOLDERING_STATION;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return this.hasCustomName();
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    @Override
    public void openInventory()
    {
        // NOOP
    }

    @Override
    public void closeInventory()
    {
        // NOOP
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("CurrentPower", (short)this.CurrentPower);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            if (inventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag(Names.NBT.ITEMS, tagList);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.ITEMS, 10);
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
            this.CurrentPower = nbtTagCompound.getShort("CurrentPower");
        }
    }

    @SideOnly(Side.CLIENT)
    public int getPowerScale(int var1) { return this.CurrentPower * var1 / 1000; }

    @Override
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {

        }
    }

    public boolean canSolder()
    {
        if (!hasValidRecipe()) return false;
        if (!hasEnoughSolder()) return false;
        if (inventory[0] != null && inventory[0].getItem() != getCraftingResult().getItem()) return false;
        if (inventory[0].stackSize == 64) return false;
        if (CurrentPower < 50) return false;

        return true;
    }

    public ItemStack getCraftingResult()
    {
        ItemStack[] Components = new ItemStack[9];

        for (int i = 0; i < Components.length; i++)
        {
            Components[i] = inventory[i + 3];
        }

        return RecipesSolderingStation.soldering().getCraftingResult(Components);
    }

    public boolean hasValidRecipe()
    {
        return getCraftingResult() != null ? true : false;
    }

    public boolean hasEnoughSolder()
    {
        return RecipesSolderingStation.soldering().getNeededSolder(getCraftingResult()) <= inventory[2].stackSize ? true : false;
    }

    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return slotIndex == 1 ? false : true;
    }

    public int[] getAccessibleSlotsFromSide(int side)
    {
        return side == 0 ? slotsBottom : (side == 1 ? slotsTop : slotsSides);
    }

    public boolean canInsertItem(int slot, ItemStack itemStack, int side)
    {
        return this.isItemValidForSlot(side, itemStack);
    }
    public boolean canExtractItem(int slot, ItemStack itemStack, int side)
    {
        return side != 0 && side != 1 ? slot == 0 ? false : true : true;
    }
}

