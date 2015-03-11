package com.techmage.magetech.tileentity;

import com.techmage.magetech.crafting.RecipesCrusher;
import com.techmage.magetech.crafting.RecipesInfuser;
import com.techmage.magetech.reference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCrusher extends TileEntityMageTech implements ISidedInventory
{
    public static final int INVENTORY_SIZE = 2;

    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {1};
    private static final int[] slotsSides = new int[] {0, 1};

    private ItemStack[] inventory;

    public int MaxPower = 1000;
    public int CurrentPower = 1000;

    public TileEntityCrusher()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    public int CrushingTime;

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
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.CRUSHER;
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
        nbtTagCompound.setShort("CrushingTime", (short)this.CrushingTime);
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
            this.CrushingTime = nbtTagCompound.getShort("CrushingTime");
            this.CurrentPower = nbtTagCompound.getShort("CurrentPower");
        }
    }

    @SideOnly(Side.CLIENT)
    public int getPowerScale(int var1) { return this.CurrentPower * var1 / 1000; }

    @SideOnly(Side.CLIENT)
    public int getCrushingProgressScale(int var1) { return this.CrushingTime * var1 / 300; }

    public boolean isCrushing()
    {
        return this.CrushingTime > 0;
    }

    @Override
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {
            if (canCrush())
            {
                if (this.CrushingTime < 300)
                {
                    this.CrushingTime++;
                }
                else
                {
                    crushItem();
                    this.CrushingTime = 0;
                    this.markDirty();
                }
            }
            else
            {
                this.CrushingTime = 0;
                this.markDirty();
            }
        }
    }

    public boolean canCrush()
    {

        if (inventory[0] != null)
        {

            ItemStack result = RecipesCrusher.crushing().getCraftingResult(inventory[0]);

            if (result != null)
            {
                if (this.CurrentPower >= 50)
                {
                    if (inventory[1] == null)
                    {
                        return true;
                    }
                    else if (inventory[1].isItemEqual(result))
                    {
                        if (inventory[1].stackSize + RecipesCrusher.crushing().getOutputSize(result) <= 64)
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return  false;

    }

    public void crushItem()
    {

        ItemStack result = RecipesCrusher.crushing().getCraftingResult(inventory[0]);

        this.CurrentPower = this.CurrentPower - 50;

        if (inventory[1] == null)
        {
            inventory[1] = new ItemStack(result.getItem(), RecipesCrusher.crushing().getOutputSize(result), result.getItemDamage());
        }
        else
        {
            inventory[1].stackSize += RecipesCrusher.crushing().getOutputSize(result);
        }

        if (inventory[0].stackSize > 1)
        {
            inventory[0].stackSize --;
        }
        else
        {
            inventory[0] = null;
        }
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

