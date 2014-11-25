package com.techmage.magetech.reference;

public class Names
{
    public static final class Items
    {
        public static final String CRYSTAL = "crystal";
        public static final String[] CRYSTAL_SUBTYPES = {"Empty", "Fire", "Water", "Earth", "Air", "Pure"};
        public static final String INGOT = "ingot";
        public static final String[] INGOT_SUBTYPES = {"Lead", "Silver", "Copper"};
        public static final String SILICONORE = "siliconOre";
        public static final String SILICON = "silicon";
        public static final String RESIN = "resin";
        public static final String PLASTIC = "plastic";
        public static final String GLASSFIBRE = "glassFibre";
        public static final String GLASSFIBREMAT = "glassFibreMat";
        public static final String WIREIRON = "wireIron";
        public static final String IC = "ic";
        public static final String CAPACITOR = "capacitor";
        public static final String TRANSISTOR = "transistor";
    }

    public static final class Blocks
    {
        public static final String INFUSER = "infuser";
        public static final String STONE_HARDENED = "stoneHardened";
        public static final String MPWIRE = "mpWire";
        public static final String ORE = "ore";
        public static final String[] ORE_SUBTYPES = {"Lead", "Silver", "Copper"};
        public static final String ORECRYSTAL = "oreCrystal";
        public static final String CRUSHER = "crusher";
        public static final String ORESILICON = "oreSilicon";
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
        public static final String CRUSHER = "container.magetech:" + Blocks.CRUSHER;
    }
}
