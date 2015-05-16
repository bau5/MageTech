package com.techmage.magetech.client.render.tileentity;

import com.techmage.magetech.utility.Location;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class EssenceBeamRenderer extends TileEntitySpecialRenderer
{

    public Float pixel = 1F / 16F;

    @Override
    public void renderTileEntityAt(TileEntity tile, double translateX, double translateY, double translateZ, float f)
    {

    }

    public void renderBeam(Location source, Location dest)
    {
        ForgeDirection dir = getBeamDirection (source, dest);
        int dist = getBeamDistance(source, dest);

        switch (dir)
        {
            case NORTH:
                renderBeamNorth(dist);
                break;
            case SOUTH:
                renderBeamSouth(dist);
                break;
            case West:
                renderBeamWest(dist);
                break;
            case East:
                renderBeamEast(dist);
                break;
            case UP:
                renderBeamUp(dist);
                break;
            case DOWN:
                renderBeamDown(dist);
                break;
            default:
                break;
        }
    }

    public ForgeDirection getBeamDirection(Location source, Location dest)
    {
        int difX = source.x - dest.x;
        int difY = source.y - dest.y;
        int difZ = source.z - dest.z;

        if (difX == 0 && difY == 0 && difZ > 0)
            return ForgeDirection.NORTH;
        if (difX == 0 && difY == 0 && difZ < 0)
            return ForgeDirection.SOUTH;

        if (difX > 0 && difY == 0 && difZ == 0)
            return ForgeDirection.WEST;
        if (difX < 0 && difY == 0 && difZ == 0)
            return ForgeDirection.EAST;

        if (difX == 0 && difY > 0 && difZ == 0)
            return ForgeDirection.DOWN;
        if (difX == 0 && difY < 0 && difZ == 0)
            return ForgeDirection.UP;

        return  ForgeDirection.UNKNOWN;
    }

    public int getBeamDistance(Location source, Location dest)
    {
        if (source.x - dest.x > 0)
            return source.x - dest.x;
        if (source.x - dest.x < 0)
            return dest.x - source.x;

        if (source.y - dest.y > 0)
            return source.y - dest.y;
        if (source.y - dest.y < 0)
            return dest.y - source.y;

        if (source.z - dest.z > 0)
            return source.z - dest.z;
        if (source.z - dest.z < 0)
            return dest.z - source.z;

        return 0;
    }
}
