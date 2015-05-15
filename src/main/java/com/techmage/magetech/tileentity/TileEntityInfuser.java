package com.techmage.magetech.tileentity;

import com.techmage.magetech.crafting.RecipesInfuser;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityInfuser extends TileEntityEssenceHandler implements ISidedInventory
{
    public static final int INVENTORY_SIZE = 4;

    private static final int[] slotsTop = new int[] {0, 1, 2};
    private static final int[] slotsBottom = new int[] {3};
    private static final int[] slotsSides = new int[] {0, 1, 2, 3};

    private ItemStack[] inventory;

    public int storedEssence = 0;
    public int maxEssence = 1000;

    public TileEntityInfuser()
    {
        this.setReceiverSites(ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST);

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
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("InfusionTime", (short)this.InfusionTime);
        nbtTagCompound.setShort("storedEssence", (short)this.storedEssence);

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
        this.InfusionTime = nbtTagCompound.getShort("InfusionTime");
        this.storedEssence = nbtTagCompound.getShort("storedEssence");
    }

    @Override
    public int getStoredEssence()
    {
        return this.storedEssence;
    }

    public int getMaxEssence()
    {
        return this.maxEssence;
    }

    @Override
    public void ReceiveEssence(int amount, int[] source)
    {
        this.storedEssence += amount;
    }

    @SideOnly(Side.CLIENT)
    public int getEssenceScale(int var1) { return this.storedEssence * var1 / 1000; }

    @SideOnly(Side.CLIENT)
    public int getInfusionProgressScale(int var1) { return this.InfusionTime * var1 / 500; }

    public boolean isInfusing()
    {
        return this.InfusionTime > 0;
    }

    @Override
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {
            if (canInfuse())
            {
                if (this.InfusionTime < 500)
                {
                    this.InfusionTime++;
                }
                else
                {
                    infuseItem();
                    this.InfusionTime = 0;
                    this.markDirty();
                }
            }
            else
            {
                this.InfusionTime = 0;
                this.markDirty();
            }
        }
    }

    public boolean canInfuse()
    {

        if (inventory[0] != null && inventory[1] != null && inventory[2] != null)
        {

            ItemStack result = RecipesInfuser.infusing().getCraftingResult(inventory[0], inventory[1], inventory[2]);

            if (result != null)
            {
                if (RecipesInfuser.infusing().getEssenceCost(inventory[0], inventory[1], inventory[2]) <= this.storedEssence)
                {
                    if (inventory[3] == null)
                    {
                        return true;
                    }
                    else if (inventory[3].isItemEqual(result))
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

        this.storedEssence = this.storedEssence - RecipesInfuser.infusing().getEssenceCost(inventory[0], inventory[1], inventory[2]);

        if (inventory[3] == null)
        {
            inventory[3] = new ItemStack(result.getItem(), 1, result.getItemDamage());
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

    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return slotIndex == 3 ? false : true;
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
        return side != 0 && side != 1 ? slot == 0 || slot == 1 || slot == 2 ? false : true : true;
    }

}

