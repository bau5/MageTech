package com.techmage.magetech.utility;

public class Location
{

    public int x;
    public int y;
    public int z;

    public Location (int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean compareLocation(Location loc)
    {
        if (x == loc.x && y == loc.y && z == loc.z)
            return true;
        return false;
    }

}
