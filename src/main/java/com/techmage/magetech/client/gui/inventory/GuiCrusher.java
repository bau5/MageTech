package com.techmage.magetech.client.gui.inventory;

import com.techmage.magetech.inventory.ContainerCrusher;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import com.techmage.magetech.tileentity.TileEntityCrusher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiCrusher extends GuiContainer
{
    private TileEntityCrusher tileEntityCrusher;

    public GuiCrusher(InventoryPlayer inventoryPlayer, TileEntityCrusher tileEntityCrusher)
    {
        super(new ContainerCrusher(inventoryPlayer, tileEntityCrusher));
        ySize = 239;
        this.tileEntityCrusher = tileEntityCrusher;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = StatCollector.translateToLocal(tileEntityCrusher.getInventoryName());
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui.CRUSHER);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        int i1 = this.tileEntityCrusher.getPowerScale(46);
        this.drawTexturedModalRect (xStart + 12, yStart + 56 + 46 - i1, 177, 46 - i1, 9, i1 + 1);

        if (this.tileEntityCrusher.isCrushing())
        {
            i1 = this.tileEntityCrusher.getCrushingProgressScale(46);
            this.drawTexturedModalRect (xStart + 155, yStart + 56 + 46 - i1, 187, 46 - i1, 9, i1 + 1);
        }
    }
}