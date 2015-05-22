package com.techmage.magetech.entity;

import com.techmage.magetech.reference.Reference;
import com.techmage.magetech.utility.Location;
import com.techmage.magetech.utility.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityEssenceBeam extends Entity
{

    public static final ResourceLocation texture = new ResourceLocation("magetech", "textures/entity/essenceBeam.png");

    public Location source;
    public Location dest;

    public EntityEssenceBeam(World world)
    {
        super(world);

        source = new Location(0, 0, 0);
        dest = new Location(0, 0, 0);
    }

    public EntityEssenceBeam(World world, Location source, Location dest)
    {
        super(world);

        this.source = source;
        this.dest = dest;

        setPositionAndRotation(source.x, source.y, source.z, 0, 0);
        setSize(10, 10);
    }

    @Override
    protected void entityInit()
    {
        preventEntitySpawning = false;
        noClip = true;
        isImmuneToFire = true;
    }

    @Override
    public void onUpdate()
    {
        if (source == null || dest == null)
            return;

        boundingBox.minX = Math.min(source.x, dest.x);
        boundingBox.minY = Math.min(source.y, dest.y);
        boundingBox.minZ = Math.min(source.z, dest.z);

        boundingBox.maxX = Math.max(source.x, dest.x);
        boundingBox.maxY = Math.max(source.y, dest.y);
        boundingBox.maxZ = Math.max(source.z, dest.y);

        boundingBox.minX --;
        boundingBox.minY --;
        boundingBox.minZ --;

        boundingBox.maxX ++;
        boundingBox.maxY ++;
        boundingBox.maxZ ++;
    }

    public ResourceLocation getTexture()
    {
        return texture;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) {

        int sourceX = nbt.getInteger("sourceX");
        int sourceY = nbt.getInteger("sourceZ");
        int sourceZ = nbt.getInteger("sourceY");
        this.source = new Location(sourceX, sourceY, sourceZ);

        int destX = nbt.getInteger("destX");
        int destY = nbt.getInteger("destZ");
        int destZ = nbt.getInteger("destY");
        this.dest = new Location(destX, destY, destZ);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt) {

        nbt.setInteger("sourceX", this.source.x);
        nbt.setInteger("sourceY", this.source.y);
        nbt.setInteger("sourceZ", this.source.z);

        nbt.setInteger("destX", this.dest.x);
        nbt.setInteger("destY", this.dest.y);
        nbt.setInteger("destZ", this.dest.z);
    }
    
    @Override
    public int getBrightnessForRender(float par1)
    {
        return 210;
    }
}
