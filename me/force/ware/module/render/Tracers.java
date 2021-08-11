package me.force.ware.module.render;

import me.force.ware.module.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Tracers extends Module
{
    public Tracers() {
        super("Tracers", "draw a line to an entity", Category.RENDER);
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        GL11.glPushMatrix();
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        for (final Entity entity : Tracers.mc.field_71441_e.field_72996_f) {
            if (entity != Tracers.mc.field_71439_g && entity != null) {
                if (!(entity instanceof EntityPlayer)) {
                    continue;
                }
                Tracers.mc.func_175606_aa().func_70032_d(entity);
                final double d = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) - Tracers.mc.func_175598_ae().field_78730_l;
                final double d2 = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) - Tracers.mc.func_175598_ae().field_78731_m;
                final double d3 = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) - Tracers.mc.func_175598_ae().field_78728_n;
                final float f2 = System.currentTimeMillis() % 2000L / 1000.0f;
                final float cfr_ignored_0 = 0.5f + 0.5f * MathHelper.func_76126_a(f2 * 3.1415927f);
                final float cfr_ignored_2 = 0.5f + 0.5f * MathHelper.func_76126_a((f2 + 1.3333334f) * 3.1415927f);
                final float cfr_ignored_3 = 0.5f + 0.5f * MathHelper.func_76126_a((f2 + 2.6666667f) * 3.1415927f);
                final float cfr_ignored_4 = Tracers.mc.field_71439_g.func_70032_d(entity) / 20.0f;
                GL11.glColor4f(0.4f, 0.6f, 1.0f, 1.71f);
                Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0);
                vec3d = vec3d.func_178789_a(-(float)Math.toRadians(Tracers.mc.field_71439_g.field_70125_A));
                final Vec3d vec3d2 = vec3d.func_178785_b(-(float)Math.toRadians(Tracers.mc.field_71439_g.field_70177_z));
                GL11.glBegin(2);
                GL11.glVertex3d(vec3d2.field_72450_a, Tracers.mc.field_71439_g.func_70047_e() + vec3d2.field_72448_b, vec3d2.field_72449_c);
                GL11.glVertex3d(d, d2 + 1.15, d3);
                GL11.glEnd();
            }
        }
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
}
