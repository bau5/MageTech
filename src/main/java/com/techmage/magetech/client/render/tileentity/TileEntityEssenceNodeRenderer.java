package com.techmage.magetech.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class TileEntityEssenceNodeRenderer extends TileEntitySpecialRenderer
{

    private IModelCustom model;
    private static final ResourceLocation ESSENCE_NODE = new ResourceLocation("magetech", "textures/models/essenceNode.obj");
    private static final ResourceLocation texture = new ResourceLocation("magetech", "textures/models/essenceNode.png");

    public TileEntityEssenceNodeRenderer()
    {
        this.model = AdvancedModelLoader.loadModel(ESSENCE_NODE);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();

        GL11.glTranslated(x, y, z);

        bindTexture(texture);
        model.renderAll();

        GL11.glPopMatrix();
    }

    public void renderHand(double x, double y, double z)
    {
        GL11.glPushMatrix();

        GL11.glTranslatef((float) x, (float) y + 0.5F, (float) z);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        GL11.glPushMatrix();
        model.renderAll();

        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void renderInventory(double x, double y, double z)
    {
        GL11.glPushMatrix();

        GL11.glTranslatef((float) x, (float) y, (float) z);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        GL11.glPushMatrix();
        model.renderAll();

        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}
