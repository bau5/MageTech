package com.techmage.magetech.client.gui.inventory;

import com.techmage.magetech.inventory.ContainerInfuser;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import com.techmage.magetech.tileentity.TileEntityInfuser;
import com.techmage.magetech.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiInfuser extends GuiContainer
{
    private TileEntityInfuser tileEntityInfuser;

    public GuiInfuser(InventoryPlayer inventoryPlayer, TileEntityInfuser tileEntityInfuser)
    {
        super(new ContainerInfuser(inventoryPlayer, tileEntityInfuser));
        ySize = 239;
        this.tileEntityInfuser = tileEntityInfuser;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = StatCollector.translateToLocal(tileEntityInfuser.getInventoryName());
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui.INFUSER);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        int i1 = this.tileEntityInfuser.getEssenceScale(46);
        this.drawTexturedModalRect (xStart + 12, yStart + 56 + 46 - i1, 177, 46 - i1, 9, i1 + 1);

        if (this.tileEntityInfuser.isInfusing())
        {
            i1 = this.tileEntityInfuser.getInfusionProgressScale(46);
            this.drawTexturedModalRect (xStart + 155, yStart + 56 + 46 - i1, 187, 46 - i1, 9, i1 + 1);
        }
    }
}