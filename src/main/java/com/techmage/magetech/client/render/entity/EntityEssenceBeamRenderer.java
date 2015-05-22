package com.techmage.magetech.client.render.entity;

import com.techmage.magetech.entity.EntityEssenceBeam;
import com.techmage.magetech.model.ModelEssenceBeam;
import com.techmage.magetech.utility.Location;
import com.techmage.magetech.utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class EntityEssenceBeamRenderer extends Render
{

    private final ModelEssenceBeam model;

    public EntityEssenceBeamRenderer()
    {
        this.model = new ModelEssenceBeam();
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float f, float f1)
    {
        doRender((EntityEssenceBeam) entity, x, y, z, f, f1);
    }

    private void doRender(EntityEssenceBeam essenceBeam, double x, double y, double z, float f, float f1)
    {

        int dist = getBeamDistance(essenceBeam.source, essenceBeam.dest);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glPushMatrix();

        GL11.glTranslated(x, y, z);

        switch (getBeamDirection(essenceBeam.source, essenceBeam.dest))
        {
            case NORTH:
                GL11.glScaled(0.5D, 0.5D, dist);
                GL11.glRotated(180, 0, 1, 0);
                GL11.glTranslated(-1, 0, - 0.5D / dist);
                break;
            case SOUTH:
                GL11.glScaled(0.5D, 0.5D, dist);
                GL11.glTranslated(1, 0, 0.5D / dist);
                break;
            case EAST:
                GL11.glScaled(dist, 0.5D, 0.5D);
                GL11.glRotated(90, 0, 1, 0);
                GL11.glTranslated(-1, 0, 0.5D / dist);
                break;
            case WEST:
                GL11.glScaled(dist, 0.5D, 0.5D);
                GL11.glRotated(270, 0, 1, 0);
                GL11.glTranslated(1, 0, - 0.5D / dist);
                break;
            case UP:
                GL11.glScaled(0.5D, dist, 0.5D);
                GL11.glRotated(270, 1, 0, 0);
                GL11.glTranslated(1, -2, 0.5D / dist);
                break;
            case DOWN:
                GL11.glScaled(0.5D, dist, 0.5D);
                GL11.glRotated(90, 1, 0, 0);
                GL11.glTranslated(1, 0, - 0.5D / dist);
                break;
        }

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.4f);
        Minecraft.getMinecraft().renderEngine.bindTexture(getEntityTexture(essenceBeam));

        GL11.glPushMatrix();

        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
        GL11.glPopMatrix();

        GL11.glDisable(GL11.GL_BLEND);
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

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return ((EntityEssenceBeam) entity).getTexture();
    }

}
