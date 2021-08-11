package me.force.ware.module.render;

import me.force.ware.rage.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;
import java.awt.*;
import net.minecraftforge.client.event.*;
import java.util.stream.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import me.force.ware.module.*;

public class Esp extends Module
{
    public BooleanSetting invisibleSet;
    private transient int BOX;
    public BooleanSetting animalSet;
    private transient ArrayList ENTITIES;
    public BooleanSetting mobSet;
    public BooleanSetting playerSet;
    
    private void a(final Entity entity, final double d, final double d2, final double d3) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d2, (float)d3);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-Esp.mc.func_175598_ae().field_78735_i, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(Esp.mc.func_175598_ae().field_78732_j, 0.0f, 1.0f, 0.0f);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        final Tessellator tessellator = Tessellator.func_178181_a();
        tessellator.func_178180_c();
        if (((EntityLivingBase)entity).func_110143_aJ() <= 20.0f) {
            drawRect(-0.7f, ((EntityLivingBase)entity).func_110143_aJ() / 10.0f, -0.58f, 0.0f, new Color(65, 186, 13, 255).getRGB());
            drawRect(-0.71f, 2.01f, -0.58f, 2.0f, new Color(208, 208, 208, 255).getRGB());
            drawRect(-0.71f, 2.01f, -0.7f, 0.01f, new Color(208, 208, 208, 255).getRGB());
            drawRect(-0.59f, 0.01f, -0.58f, 2.0f, new Color(208, 208, 208, 255).getRGB());
            drawRect(-0.58f, 0.01f, -0.71f, 0.0f, new Color(208, 208, 208, 255).getRGB());
            a(-0.5, 2.0, 0.5, 0.0, 2.5f, new Color(208, 208, 208, 255).getRGB(), new Color(208, 208, 208, 255).getRGB());
        }
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        if (Esp.mc.field_71439_g == null || Esp.mc.field_71439_g.field_70128_L) {
            return;
        }
        final List list = (List)Esp.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity != Esp.mc.field_71439_g).filter(entity -> !entity.field_70128_L).sorted(Comparator.comparing(entity -> Esp.mc.field_71439_g.func_70032_d(entity))).collect(Collectors.toList());
        for (final Object entity2w : list) {
            final Entity entity2 = (Entity)entity2w;
            if (entity2 != null) {
                if (entity2 == Esp.mc.field_71439_g) {
                    continue;
                }
                if (this.invisibleSet.isEnabled() && entity2.func_82150_aj() && ((entity2 instanceof EntityPlayer && this.playerSet.isEnabled()) || (entity2 instanceof EntityMob && this.mobSet.isEnabled()) || (entity2 instanceof EntityAnimal && this.animalSet.isEnabled()))) {
                    final double d3 = entity2.field_70142_S + (entity2.field_70165_t - entity2.field_70142_S) - Esp.mc.func_175598_ae().field_78730_l;
                    final double d4 = entity2.field_70137_T + (entity2.field_70163_u - entity2.field_70137_T) - Esp.mc.func_175598_ae().field_78731_m;
                    final double d5 = entity2.field_70136_U + (entity2.field_70161_v - entity2.field_70136_U) - Esp.mc.func_175598_ae().field_78728_n;
                    this.a(entity2, d3, d4, d5);
                    this.ab(entity2, d3, d4, d5);
                }
                else if (this.mobSet.isEnabled() && entity2 instanceof EntityMob) {
                    final double d3 = entity2.field_70142_S + (entity2.field_70165_t - entity2.field_70142_S) - Esp.mc.func_175598_ae().field_78730_l;
                    final double d4 = entity2.field_70137_T + (entity2.field_70163_u - entity2.field_70137_T) - Esp.mc.func_175598_ae().field_78731_m;
                    final double d5 = entity2.field_70136_U + (entity2.field_70161_v - entity2.field_70136_U) - Esp.mc.func_175598_ae().field_78728_n;
                    this.a(entity2, d3, d4, d5);
                    this.ab(entity2, d3, d4, d5);
                }
                else if (this.animalSet.isEnabled() && entity2 instanceof EntityAnimal) {
                    final double d3 = entity2.field_70142_S + (entity2.field_70165_t - entity2.field_70142_S) - Esp.mc.func_175598_ae().field_78730_l;
                    final double d4 = entity2.field_70137_T + (entity2.field_70163_u - entity2.field_70137_T) - Esp.mc.func_175598_ae().field_78731_m;
                    final double d5 = entity2.field_70136_U + (entity2.field_70161_v - entity2.field_70136_U) - Esp.mc.func_175598_ae().field_78728_n;
                    this.a(entity2, d3, d4, d5);
                    this.ab(entity2, d3, d4, d5);
                }
                else {
                    if (!this.playerSet.isEnabled()) {
                        continue;
                    }
                    if (!(entity2 instanceof EntityPlayer)) {
                        continue;
                    }
                    final double d3 = entity2.field_70142_S + (entity2.field_70165_t - entity2.field_70142_S) - Esp.mc.func_175598_ae().field_78730_l;
                    final double d4 = entity2.field_70137_T + (entity2.field_70163_u - entity2.field_70137_T) - Esp.mc.func_175598_ae().field_78731_m;
                    final double d5 = entity2.field_70136_U + (entity2.field_70161_v - entity2.field_70136_U) - Esp.mc.func_175598_ae().field_78728_n;
                    this.a(entity2, d3, d4, d5);
                    this.ab(entity2, d3, d4, d5);
                }
            }
        }
    }
    
    private void ab(final Entity entity, final double d, final double d2, final double d3) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d2, (float)d3);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-Esp.mc.func_175598_ae().field_78732_j, 0.0f, 0.2f, 0.0f);
        GL11.glRotatef(Esp.mc.func_175598_ae().field_78735_i, 1.0f, 0.0f, 0.0f);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        if (((EntityLivingBase)entity).func_110143_aJ() <= 20.0f) {
            drawRect(1.1f, 0.5f, 1.1f, 0.5f, -14880362);
        }
        if (((EntityLivingBase)entity).func_110143_aJ() <= 10.0f) {
            drawRect(1.1f, 2.0f, 1.7f, 2.0f, -196692);
        }
        if (((EntityLivingBase)entity).func_110143_aJ() <= 5.0f) {
            drawRect(1.1f, 0.5f, 1.1f, 0.5f, -63232);
        }
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void a(double d, double d2, double d3, double d4, final float f, final int n, final int n2) {
        d4 = 0.0;
        d3 = 0.5;
        d2 = 2.0;
        d = -0.5;
        a((float)d, (float)d2, (float)d3, (float)d4, n2);
        final float f2 = (n >> 24 & 0xFF) / 255.0f;
        final float f3 = (n >> 16 & 0xFF) / 255.0f;
        final float f4 = (n >> 8 & 0xFF) / 255.0f;
        final float f5 = (n & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glColor4f(f3, f4, f5, f2);
        GL11.glLineWidth(2.0f);
        GL11.glBegin(1);
        GL11.glVertex2d(d, d2);
        GL11.glVertex2d(d, d4);
        GL11.glVertex2d(d3, d4);
        GL11.glVertex2d(d3, d2);
        GL11.glVertex2d(d, d2);
        GL11.glVertex2d(d3, d2);
        GL11.glVertex2d(d, d4);
        GL11.glVertex2d(d3, d4);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static void drawRect(float f, float f2, float f3, float f4, final int n) {
        if (f < f3) {
            final float f5 = f;
            f = f3;
            f3 = f5;
        }
        if (f2 < f4) {
            final float f5 = f2;
            f2 = f4;
            f4 = f5;
        }
        final float f5 = (n >> 24 & 0xFF) / 255.0f;
        final float f6 = (n >> 16 & 0xFF) / 255.0f;
        final float f7 = (n >> 8 & 0xFF) / 255.0f;
        final float f8 = (n & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferBuilder = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179131_c(f6, f7, f8, f5);
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        bufferBuilder.func_181662_b((double)f, (double)f4, 0.0).func_181675_d();
        bufferBuilder.func_181662_b((double)f3, (double)f4, 0.0).func_181675_d();
        bufferBuilder.func_181662_b((double)f3, (double)f2, 0.0).func_181675_d();
        bufferBuilder.func_181662_b((double)f, (double)f2, 0.0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public Esp() {
        super("Esp", "draw entity lines", Category.RENDER);
        this.BOX = 0;
        this.ENTITIES = new ArrayList();
        this.mobSet = new BooleanSetting("Mobs", false);
        this.animalSet = new BooleanSetting("Animals", false);
        this.playerSet = new BooleanSetting("Players", true);
        this.invisibleSet = new BooleanSetting("Invisible", true);
    }
    
    public static void a(float f, float f2, float f3, float f4, final int n) {
        f4 = 0.0f;
        f3 = 0.5f;
        f2 = 2.0f;
        f = -0.5f;
        final float f5 = (n >> 24 & 0xFF) / 255.0f;
        final float f6 = (n >> 16 & 0xFF) / 255.0f;
        final float f7 = (n >> 8 & 0xFF) / 255.0f;
        final float f8 = (n & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glBegin(7);
        GL11.glVertex2d((double)f3, (double)f2);
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glVertex2d((double)f, (double)f4);
        GL11.glVertex2d((double)f3, (double)f4);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
}
