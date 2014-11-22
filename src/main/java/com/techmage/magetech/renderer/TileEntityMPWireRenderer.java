package com.techmage.magetech.renderer;

import com.techmage.magetech.reference.Textures;
import com.techmage.magetech.renderer.model.ModelMPWire;
import com.techmage.magetech.tileentity.TileEntityMPWire;
import com.techmage.magetech.utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityMPWireRenderer extends TileEntitySpecialRenderer
{
    private static final ResourceLocation WIRE_LOCATION = new ResourceLocation(Textures.RESOURCE_PREFIX + "textures/models/mpwire.png");

    private final ModelMPWire model;

    public TileEntityMPWireRenderer()
    {
        model = new ModelMPWire();
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
    {
        if (te != null && te instanceof TileEntityMPWire)
        {
            renderPower((TileEntityMPWire) te, x, y, z);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        Minecraft.getMinecraft().renderEngine.bindTexture(WIRE_LOCATION);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        model.render(te);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    private void renderPower(TileEntityMPWire te, double x, double y, double z)
    {
        GL11.glPushMatrix();
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        long time = te.getWorldObj().getTotalWorldTime();
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        OpenGlHelper.glBlendFunc(770, 1, 1, 0);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.5F);
        GL11.glPopMatrix();
    }

    public void render(double x, double y, double z)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        Minecraft.getMinecraft().renderEngine.bindTexture(WIRE_LOCATION);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}