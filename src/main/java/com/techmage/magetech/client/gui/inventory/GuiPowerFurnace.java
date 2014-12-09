package com.techmage.magetech.client.gui.inventory;

import com.techmage.magetech.inventory.ContainerPowerFurnace;
import com.techmage.magetech.network.PacketPowerFurnaceSetMode;
import com.techmage.magetech.handler.PacketHandler;
import com.techmage.magetech.reference.Names;
import com.techmage.magetech.reference.Textures;
import com.techmage.magetech.tileentity.TileEntityPowerFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiPowerFurnace extends GuiContainer
{
    private TileEntityPowerFurnace tileEntityPowerFurnace;

    public GuiPowerFurnace(InventoryPlayer inventoryPlayer, TileEntityPowerFurnace tileEntityPowerFurnace)
    {
        super(new ContainerPowerFurnace(inventoryPlayer, tileEntityPowerFurnace));

        this.xSize = 176;
        this.ySize = 171;

        this.tileEntityPowerFurnace = tileEntityPowerFurnace;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        this.buttonList.add(new GuiButton(1, (width - xSize) / 2 + 139, (height - ySize) / 2 + 14, 20, 20, ""));
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if(button.id == 1)
        {
            if (tileEntityPowerFurnace.inSeries == false)
            {
                PacketHandler.packetReq.sendToServer(new PacketPowerFurnaceSetMode(tileEntityPowerFurnace.xCoord, tileEntityPowerFurnace.yCoord, tileEntityPowerFurnace.zCoord, true));
                tileEntityPowerFurnace.setMode(true);
            }
            else
            {
                PacketHandler.packetReq.sendToServer(new PacketPowerFurnaceSetMode(tileEntityPowerFurnace.xCoord, tileEntityPowerFurnace.yCoord, tileEntityPowerFurnace.zCoord, false));
                tileEntityPowerFurnace.setMode(false);
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = StatCollector.translateToLocal(tileEntityPowerFurnace.getInventoryName());
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.Containers.VANILLA_INVENTORY), 8, ySize - 98, 4210752);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui.POWERFURNACE);

        if (tileEntityPowerFurnace.inSeries == false)
        {
            this.drawTexturedModalRect(141, 16, 192, 32, 20, 20);
        }
        else
        {
            this.drawTexturedModalRect(141, 16, 192, 49, 20, 20);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui.POWERFURNACE);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int i1 = this.tileEntityPowerFurnace.getPowerScale(32);
        this.drawTexturedModalRect (xStart + 17, yStart + 25 + 32 - i1, 177, 63 - i1, 14, i1 + 1);

        if (this.tileEntityPowerFurnace.isCooking1())
        {
            i1 = this.tileEntityPowerFurnace.getCookingProgressScaled1(24);
            this.drawTexturedModalRect(xStart + 79, yStart + 16, 176, 14, i1 + 1, 16);
        }

        if (this.tileEntityPowerFurnace.isCooking2())
        {
            i1 = this.tileEntityPowerFurnace.getCookingProgressScaled2(24);
            this.drawTexturedModalRect(xStart + 79, yStart + 52, 176, 14, i1 + 1, 16);
        }
    }
}