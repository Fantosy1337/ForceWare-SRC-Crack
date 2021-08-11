package me.force.ware.module.movement;

import me.force.ware.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Strafe extends Module
{
    public Strafe() {
        super("Starfe", "", Category.MOVEMENT);
    }
    
    public static void strafe(final float f) {
        if (!isMoving()) {
            return;
        }
        final double d = getDirection();
        Strafe.mc.field_71439_g.field_70159_w = -Math.sin(d) * f;
        Strafe.mc.field_71439_g.field_70179_y = Math.cos(d) * f;
    }
    
    public static float getSpeed() {
        return (float)Math.sqrt(Strafe.mc.field_71439_g.field_70159_w * Strafe.mc.field_71439_g.field_70159_w + Strafe.mc.field_71439_g.field_70179_y * Strafe.mc.field_71439_g.field_70179_y);
    }
    
    public static double getDirection() {
        float f = Strafe.mc.field_71439_g.field_70177_z;
        if (Strafe.mc.field_71439_g.field_191988_bg < 0.0f) {
            f += 180.0f;
        }
        float f2 = 1.0f;
        if (Strafe.mc.field_71439_g.field_191988_bg < 0.0f) {
            f2 = -0.5f;
        }
        else if (Strafe.mc.field_71439_g.field_191988_bg > 0.0f) {
            f2 = 0.5f;
        }
        if (Strafe.mc.field_71439_g.field_70702_br > 0.0f) {
            f -= 90.0f * f2;
        }
        if (Strafe.mc.field_71439_g.field_70702_br < 0.0f) {
            f += 90.0f * f2;
        }
        return Math.toRadians(f);
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        strafe();
    }
    
    private static void strafe() {
        strafe(getSpeed());
    }
    
    public static boolean isMoving() {
        return Strafe.mc.field_71439_g != null && (Strafe.mc.field_71439_g.field_71158_b.field_192832_b != 0.0f || Strafe.mc.field_71439_g.field_71158_b.field_78902_a != 0.0f);
    }
}
