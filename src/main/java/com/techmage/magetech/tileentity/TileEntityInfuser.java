package com.techmage.magetech.tileentity;

import com.techmage.magetech.init.ModItems;
import com.techmage.magetech.item.crafting.RecipesInfuser;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityInfuser extends TileEntityMageTech implements IInventory
{
    public static final int INVENTORY_SIZE = 4;

    /**
     * The ItemStacks that hold the items currently being used in the Glass Bell
     */
    private ItemStack[] inventory;

    int CurrentEssence = 1000;

    public TileEntityInfuser()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    public int InfusionTime;

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
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.INFUSER;
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
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

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
        }
    }

    @SideOnly(Side.CLIENT)
    public int getEssenceScale(int var1) { return this.CurrentEssence * var1 / 1000; }

    @SideOnly(Side.CLIENT)
    public int getInfusionProgressScale(int var1) { return this.InfusionTime * var1 / 500; }

    public boolean isInfusing()
    {
        if (this.InfusionTime > 0)
        {
            return true;
        }

        return  false;
    }

    @Override
    public void updateEntity()
    {

        if (canInfuse())
        {
            if (this.InfusionTime < 500)
            {
                this.InfusionTime ++;
            }
            else
            {
                infuseItem();
                this.InfusionTime = 0;
            }
        }
        else
        {
            this.InfusionTime = 0;
        }
    }

    public boolean canInfuse()
    {

        if (inventory[0] != null && inventory[1] != null && inventory[2] != null)
        {

            ItemStack result = RecipesInfuser.infusing().getCraftingResult(inventory[0], inventory[1], inventory[2]);

            if (result != null)
            {
                if (RecipesInfuser.infusing().getEssenceCost(inventory[0], inventory[1], inventory[2]) <= this.CurrentEssence)
                {
                    if (inventory[3] == null)
                    {
                        return true;
                    }
                    else if (inventory[3] == result)
                    {
                        if (inventory[3].stackSize < 64)
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return  false;

    }

    public void infuseItem()
    {

        ItemStack result = RecipesInfuser.infusing().getCraftingResult(inventory[0], inventory[1], inventory[2]);

        this.CurrentEssence = this.CurrentEssence - RecipesInfuser.infusing().getEssenceCost(inventory[0], inventory[1], inventory[2]);

        if (inventory[3] == null)
        {
            inventory[3] = result;
        }
        else
        {
            inventory[3].stackSize ++;
        }

        if (inventory[0].stackSize > 1)
        {
            inventory[0].stackSize --;
        }
        else
        {
            inventory[0] = null;
        }

        if (inventory[1].stackSize > 1)
        {
            inventory[1].stackSize --;
        }
        else
        {
            inventory[1] = null;
        }

        if (inventory[2].stackSize > 1)
        {
            inventory[2].stackSize --;
        }
        else
        {
            inventory[2] = null;
        }
    }
}

