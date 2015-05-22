package com.techmage.magetech.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEssenceBeam extends ModelBase
{

    ModelRenderer BeamMiddle;
    ModelRenderer BeamTop;
    ModelRenderer BeamBottom;

    public ModelEssenceBeam()
    {
        textureWidth = 32;
        textureHeight = 32;

        BeamMiddle = new ModelRenderer(this, 0, 0);
        BeamMiddle.addBox(0F, 0F, 0F, 4, 2, 16);
        BeamMiddle.setRotationPoint(-2F, 15F, 0);
        BeamMiddle.setTextureSize(32, 32);
        BeamMiddle.mirror = true;

        setRotation(BeamMiddle, 0F, 0F, 0F);

        BeamTop = new ModelRenderer(this, 0, 0);
        BeamTop.addBox(0F, 0F, 0F, 2, 1, 16);
        BeamTop.setRotationPoint(-1F, 14F, 0);
        BeamTop.setTextureSize(32, 32);
        BeamTop.mirror = true;

        setRotation(BeamTop, 0F, 0F, 0F);

        BeamBottom = new ModelRenderer(this, 0, 0);
        BeamBottom.addBox(0F, 0F, 0F, 2, 1, 16);
        BeamBottom.setRotationPoint(-1F, 17F, 0);
        BeamBottom.setTextureSize(32, 32);
        BeamBottom.mirror = true;

        setRotation(BeamBottom, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        BeamMiddle.render(f5);
        BeamTop.render(f5);
        BeamBottom.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
