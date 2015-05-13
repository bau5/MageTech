package com.techmage.magetech.item;

import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemTalismanAir extends ItemMageTech_Magic
{

    public ItemTalismanAir()
    {
        super();
        this.setUnlocalizedName(Names.Items.TALISMAN_AIR);
    }

    private boolean isActivated = false;
    private boolean inPassiv = false;

    public void clearBlocks(World par1World, int posX, int posY, int posZ)
    {
        for (int y = 1; y < 3; y ++)
        {
            for (int z = -2; z < 3; z ++)
            {
                for (int x = -2; x < 3; x ++)
                {
                    if (par1World.getBlock(posX + x, posY - y, posZ + z) == Blocks.glass)
                        par1World.setBlock(posX + x, posY - y, posZ + z, Blocks.air);
                }
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3Entity)
    {
        if (!par2World.isRemote)
        {
            int posX = ((int) par3Entity.posX) - 1;
            int posY = ((int) par3Entity.posY);
            int posZ = ((int) par3Entity.posZ - 1);

            if (par3Entity.isSneaking())
            {
                if (inPassiv)
                    inPassiv = false;
                else
                    inPassiv = true;
            }
            else
            {
                if (inPassiv)
                {
                    if (!isActivated)
                        isActivated = true;
                    else
                        isActivated = false;

                    clearBlocks(par2World, posX, posY, posZ);
                }
                else
                {
                    Vec3 look = par3Entity.getLookVec();
                    EntitySmallFireball fireball = new EntitySmallFireball(par2World, par3Entity, 1, 1, 1);
                    fireball.setPosition( par3Entity.posX + look.xCoord * 5, par3Entity.posY + 1 + look.yCoord * 5, par3Entity.posZ + look.zCoord * 5);
                    fireball.accelerationX = look.xCoord * 0.1;
                    fireball.accelerationY = look.yCoord * 0.1;
                    fireball.accelerationZ = look.zCoord * 0.1;
                    par2World.spawnEntityInWorld(fireball);
                }
            }
        }
        return par1ItemStack;
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (!par2World.isRemote)
        {
            int posX = ((int) par3Entity.posX) - 1;
            int posY = ((int) par3Entity.posY);
            int posZ = ((int) par3Entity.posZ - 1);

            if (isActivated)
            {
                clearBlocks(par2World, posX, posY, posZ);

                for (int y = -1; y < 2; y ++)
                {
                    for (int x = -1; x < 2; x++)
                    {
                        if (par2World.getBlock(posX + x, posY - 1, posZ + y) == Blocks.air)
                            par2World.setBlock(posX + x, posY - 1, posZ + y, Blocks.glass);
                    }
                }
            }

            if (inPassiv)
            {
                if (par3Entity instanceof EntityPlayer)
                {
                    EntityPlayer player = ((EntityPlayer) par3Entity);
                    player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 20, 2));
                    player.addPotionEffect(new PotionEffect(Potion.jump.id, 20, 2));
                    player.fallDistance = 0;
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Textures.RESOURCE_PREFIX + Names.Items.TALISMAN_AIR);
    }

}
