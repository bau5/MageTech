package com.techmage.magetech.tileentity;

import com.techmage.magetech.network.DoBlockUpdate;
import com.techmage.magetech.network.PacketHandler;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityPowerFurnace extends TileEntityMageTech implements ISidedInventory
{
    public static final int INVENTORY_SIZE = 4;

    private static final int[] slotsTop = new int[] {0, 2};
    private static final int[] slotsBottom = new int[] {1, 3};
    private static final int[] slotsSides = new int[] {0, 1, 2, 3};

    /**
     * The ItemStacks that hold the items currently being used in the Glass Bell
     */
    private ItemStack[] inventory;

    public int CurrentPower = 1000;

    public TileEntityPowerFurnace()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    public int CookingTime1;
    public int CookingTime2;

    public boolean doUpdate = false;
    public boolean inSeries = false;

    public int transferTime;

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
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.POWERFURNACE;
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
        nbtTagCompound.setShort("CookingTime1", (short)this.CookingTime1);
        nbtTagCompound.setShort("CookingTime2", (short)this.CookingTime2);
        nbtTagCompound.setShort("CurrentPower", (short)this.CurrentPower);

        nbtTagCompound.setBoolean("inSeries", this.inSeries);

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

        this.CookingTime1 = nbtTagCompound.getShort("CookingTime1");
        this.CookingTime2 = nbtTagCompound.getShort("CookingTime2");
        this.CurrentPower = nbtTagCompound.getShort("CurrentPower");
        this.inSeries = nbtTagCompound.getBoolean("inSeries");
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tagCompound = new NBTTagCompound();
        writeToNBT(tagCompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
    }


    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.func_148857_g();
        readFromNBT(tag);
    }

    @SideOnly(Side.CLIENT)
    public int getPowerScale(int var1) { return this.CurrentPower * var1 / 1000; }

    @SideOnly(Side.CLIENT)
    public int getCookingProgressScaled1(int var1) { return this.CookingTime1 * var1 / 300; }

    @SideOnly(Side.CLIENT)
    public int getCookingProgressScaled2(int var1) { return this.CookingTime2 * var1 / 300; }

    public boolean isCooking1()
    {
        return this.CookingTime1 > 0;
    }
    public boolean isCooking2()
    {
        return this.CookingTime2 > 0;
    }

    @Override
    public void updateEntity()
    {

        if (worldObj.isRemote) return;

        if (canCook1())
        {
            if (this.CookingTime1 < 300)
            {
                this.CookingTime1++;
            }
            else
            {
                cookItem1();
                this.CookingTime1 = 0;
                doUpdate = true;
            }
        }
        else
        {
            this.CookingTime1 = 0;
        }

        if (canCook2())
        {
            if (this.CookingTime2 < 300)
            {
                this.CookingTime2++;
            }
            else
            {
                cookItem2();
                this.CookingTime2 = 0;
                doUpdate = true;
            }
        }
        else
        {
            this.CookingTime2 = 0;
        }

        if (this.inSeries)
        {
            if (canTransferItem())
            {
                if (this.transferTime < 100) {
                    this.transferTime++;
                }
                else
                {
                    this.transferItem();
                    this.transferTime = 0;
                }
            }
        }

        if (doUpdate)
        {
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            this.markDirty();
            doUpdate = false;
        }
    }

    public boolean canCook1()
    {

        if (inventory[0] != null)
        {

            ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]);

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
                        if (inventory[1].stackSize < 64)
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return  false;

    }

    public boolean canCook2()
    {

        if (inventory[2] != null)
        {

            ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inventory[2]);

            if (result != null)
            {
                if (this.CurrentPower >= 50)
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

    public void cookItem1()
    {

        ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]);

        this.CurrentPower = this.CurrentPower - 50;

        if (inventory[1] == null)
        {
            inventory[1] = new ItemStack(result.getItem(), 1, result.getItemDamage());
        }
        else
        {
            inventory[1].stackSize ++;
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

    public void cookItem2()
    {

        ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inventory[2]);

        this.CurrentPower = this.CurrentPower - 50;

        if (inventory[3] == null)
        {
            inventory[3] = new ItemStack(result.getItem(), 1, result.getItemDamage());
        }
        else
        {
            inventory[3].stackSize ++;
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

    public void setMode(boolean mode)
    {
        this.inSeries = mode;
    }

    public boolean canTransferItem()
    {
        if (inventory[1] != null)
        {
            if (inventory[2] == null)
            {
                return true;
            }

            if (inventory[2].isItemEqual(inventory[1]))
            {
                if (inventory[2].stackSize < 64)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void transferItem()
    {
        if (inventory[1] != null)
        {
            if (inventory[1].stackSize > 1)
            {
                inventory[1].stackSize --;
            }
            else
            {
                inventory[1] = null;
            }
        }

        if (inventory[2] == null)
        {
            inventory[2] = new ItemStack(inventory[1].getItem(), 1, inventory[1].getItemDamage());
        }
        else
        {
            inventory[2].stackSize++;
        }
    }

    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return slotIndex == 1 ? false : slotIndex == 3 ? false : true;
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
        return side != 0 && side != 1 ? slot == 0 || slot == 2 ? false : true : true;
    }
}

