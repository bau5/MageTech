package com.techmage.magetech.client.gui.inventory;

import com.techmage.magetech.inventory.ContainerElectronicsWorkbench;
import com.techmage.magetech.inventory.ContainerInfuser;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import com.techmage.magetech.tileentity.TileEntityInfuser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiElectronicsWorkbench extends GuiContainer
{

    public GuiElectronicsWorkbench(InventoryPlayer invPlayer, World world, int x, int y, int z)
    {
        super(new ContainerElectronicsWorkbench(invPlayer, world, x, y, z));

        this.xSize = 176;
        this.ySize = 171;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.ELECTRONICS_WORKBENCH), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal(Names.Containers.ELECTRONICS_WORKBENCH)) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 98, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui.ELECTRONICS_WORKBENCH);

        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}