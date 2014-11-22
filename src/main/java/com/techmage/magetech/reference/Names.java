package com.techmage.magetech.reference;

public class Names
{
    public static final class Items
    {
        public static final String CRYSTAL = "crystal";
        public static final String[] CRYSTAL_SUBTYPES = {"Empty", "Fire", "Water", "Earth", "Air", "Pure"};
        public static final String INGOT = "ingot";
        public static final String[] INGOT_SUBTYPES = {"Lead", "Silver"};
    }

    public static final class Blocks
    {
        public static final String INFUSER = "infuser";
        public static final String STONE_HARDENED = "stoneHardened";
        public static final String MPWIRE = "mpWire";
    }

    public static final class NBT
    {
        public static final String ITEMS = "Items";
        public static final String STATE = "teState";
        public static final String CUSTOM_NAME = "CustomName";
        public static final String DIRECTION = "teDirection";
    }

    public static final class Containers
    {
        public static final String VANILLA_INVENTORY = "container.inventory";
        public static final String INFUSER = "container.magetech:" + Blocks.INFUSER;
    }
}
