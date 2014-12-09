package com.techmage.magetech.item;

import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import com.techmage.magetech.utility.LogHelper;
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

public class ItemTalismanFire extends ItemMageTech_Magic
{

    public ItemTalismanFire()
    {
        super();
        this.setUnlocalizedName(Names.Items.TALISMAN_FIRE);
    }

    private boolean inPassiv = false;

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3Entity)
    {
        if (!par2World.isRemote)
        {
            if (par3Entity.isSneaking())
            {
                if (inPassiv)
                    inPassiv = false;
                else
                    inPassiv = true;
            }
            else
            {
                Vec3 look = par3Entity.getLookVec();
                EntitySmallFireball fireball = new EntitySmallFireball(par2World, par3Entity, 1, 1, 1);
                fireball.setPosition(par3Entity.posX + look.xCoord * 5, par3Entity.posY + 1 + look.yCoord * 5, par3Entity.posZ + look.zCoord * 5);
                fireball.accelerationX = look.xCoord * 0.1;
                fireball.accelerationY = look.yCoord * 0.1;
                fireball.accelerationZ = look.zCoord * 0.1;
                par2World.spawnEntityInWorld(fireball);
            }
        }
        return par1ItemStack;
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par3World.isRemote)
        {
            if(par3World.getBlock(par4, par5 + 1, par6) == Blocks.air)
                par3World.setBlock(par4, par5 + 1, par6, Blocks.flowing_lava);
        }

        return true;
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (!par2World.isRemote)
        {
            if (inPassiv)
            {
                if (par3Entity instanceof EntityPlayer)
                {
                    EntityPlayer player = ((EntityPlayer) par3Entity);
                    player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 20, 2));
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Textures.RESOURCE_PREFIX + this.getUnlocalizedName());
    }

}
