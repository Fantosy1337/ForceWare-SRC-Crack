package me.force.ware.module.combat;

import me.force.ware.module.*;
import net.minecraftforge.client.event.*;
import java.util.function.*;
import net.minecraft.entity.item.*;
import java.util.*;
import java.util.stream.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.settings.*;
import me.force.ware.Util.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.util.*;

public class KillAura extends Module
{
    private int hitDelayTimer;
    private long curTimeHit;
    private Random random;
    private double posX;
    private long curTimeRotate;
    private int rotateTimer;
    
    public KillAura() {
        super("KillAura", "automatically pvp", Category.COMBAT);
        this.hitDelayTimer = 625;
        this.random = new Random();
        this.rotateTimer = 2000;
        this.curTimeHit = System.currentTimeMillis();
        this.curTimeRotate = System.currentTimeMillis();
    }
    
    public static float[] getRotations(final Entity entity) {
        final double d = entity.field_70165_t + (entity.field_70165_t - entity.field_70142_S) - KillAura.mc.field_71439_g.field_70165_t;
        final double d2 = entity.field_70163_u + entity.func_70047_e() - KillAura.mc.field_71439_g.field_70163_u + KillAura.mc.field_71439_g.func_70047_e() - 3.5;
        final double d3 = entity.field_70161_v + (entity.field_70161_v - entity.field_70136_U) - KillAura.mc.field_71439_g.field_70161_v;
        final double d4 = Math.sqrt(Math.pow(d, 2.0) + Math.pow(d3, 2.0));
        float f = (float)Math.toDegrees(-Math.atan(d / d3));
        final float f2 = (float)(-Math.toDegrees(Math.atan(d2 / d4)));
        if (d < 0.0 && d3 < 0.0) {
            f = (float)(90.0 + Math.toDegrees(Math.atan(d3 / d)));
        }
        else if (d > 0.0 && d3 < 0.0) {
            f = (float)(-90.0 + Math.toDegrees(Math.atan(d3 / d)));
        }
        return new float[] { f, f2 };
    }
    
    @SideOnly(Side.SERVER)
    @SubscribeEvent
    public void onCameraSetup(final EntityViewRenderEvent.CameraSetup cameraSetup) {
        if (KillAura.mc.field_71439_g == null || KillAura.mc.field_71439_g.field_70128_L) {
            return;
        }
        final List list = (List)KillAura.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity != KillAura.mc.field_71439_g).filter(entity -> KillAura.mc.field_71439_g.func_70032_d(entity) <= 3.5).filter(entity -> !entity.field_70128_L).filter(this::attackCheck).filter(entity -> !(entity instanceof EntityArmorStand)).sorted(Comparator.comparing(entity -> KillAura.mc.field_71439_g.func_70032_d(entity))).collect(Collectors.toList());
        if (list.size() > 0) {
            final float[] arrf = getRotations((Entity)list.get(0));
            arrf[0] += this.random.nextInt(30) * 0.1f;
            arrf[1] += this.random.nextInt(60) * 0.1f;
            final float f = arrf[0] - 180.0f;
            final float f2 = arrf[1];
            KillAura.mc.field_71439_g.field_70761_aq = f - 180.0f;
            KillAura.mc.field_71439_g.field_70759_as = f - 180.0f;
            if (f >= 0.0f) {
                if (cameraSetup.getYaw() < f) {
                    while (cameraSetup.getYaw() < f) {
                        cameraSetup.setYaw(cameraSetup.getYaw() + this.random.nextInt(99) * 0.001f);
                    }
                }
                else {
                    while (cameraSetup.getYaw() > f) {
                        cameraSetup.setYaw(cameraSetup.getYaw() - this.random.nextInt(99) * 0.001f);
                    }
                }
            }
            else if (cameraSetup.getYaw() < f) {
                while (cameraSetup.getYaw() < f) {
                    cameraSetup.setYaw(cameraSetup.getYaw() + this.random.nextInt(99) * 0.001f);
                }
            }
            else {
                while (cameraSetup.getYaw() > f) {
                    cameraSetup.setYaw(cameraSetup.getYaw() - this.random.nextInt(99) * 0.001f);
                }
            }
            if (f2 >= 0.0f) {
                if (cameraSetup.getPitch() < f2) {
                    while (cameraSetup.getPitch() < f2) {
                        cameraSetup.setPitch(cameraSetup.getPitch() + this.random.nextInt(99) * 0.001f);
                    }
                }
                else {
                    while (cameraSetup.getPitch() > f2) {
                        cameraSetup.setPitch(cameraSetup.getPitch() - this.random.nextInt(99) * 0.001f);
                    }
                }
            }
            else if (cameraSetup.getPitch() < f2) {
                while (cameraSetup.getPitch() < f2) {
                    cameraSetup.setPitch(cameraSetup.getPitch() + this.random.nextInt(99) * 0.001f);
                }
            }
            else {
                while (cameraSetup.getPitch() > f2) {
                    cameraSetup.setPitch(cameraSetup.getPitch() - this.random.nextInt(99) * 0.001f);
                }
            }
        }
    }
    
    private void setRotation(final float f, final float f2, final EntityPlayer entityPlayer) {
        KillAura.mc.field_71439_g.field_70761_aq = f;
        KillAura.mc.field_71439_g.field_70759_as = f;
        if (f >= 0.0f) {
            if (KillAura.mc.field_71439_g.field_70177_z < f) {
                while (KillAura.mc.field_71439_g.field_70177_z < f) {
                    KillAura.mc.field_71439_g.field_70177_z += (float)(this.random.nextInt(99) * 1.0E-4);
                }
            }
            else {
                while (KillAura.mc.field_71439_g.field_70177_z > f) {
                    KillAura.mc.field_71439_g.field_70177_z -= (float)(this.random.nextInt(99) * 1.0E-4);
                }
            }
        }
        else if (KillAura.mc.field_71439_g.field_70177_z < f) {
            while (KillAura.mc.field_71439_g.field_70177_z < f) {
                KillAura.mc.field_71439_g.field_70177_z += (float)(this.random.nextInt(99) * 1.0E-4);
            }
        }
        else {
            while (KillAura.mc.field_71439_g.field_70177_z > f) {
                KillAura.mc.field_71439_g.field_70177_z -= (float)(this.random.nextInt(99) * 1.0E-4);
            }
        }
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        KeyBinding.func_74510_a(KillAura.mc.field_71474_y.field_74351_w.func_151463_i(), false);
    }
    
    public boolean attackCheck(final Entity entity) {
        return entity instanceof EntityPlayer && ((EntityPlayer)entity).func_110143_aJ() > 0.0f && Math.abs(KillAura.mc.field_71439_g.field_70177_z - RotationUtils.getNeededRotations((EntityLivingBase)entity)[0]) % 180.0f < 190.0f && !entity.func_82150_aj() && !entity.func_110124_au().equals(KillAura.mc.field_71439_g.func_110124_au());
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (KillAura.mc.field_71439_g == null || KillAura.mc.field_71439_g.field_70128_L) {
            return;
        }
        final List list;
        if ((list = (List)KillAura.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity != KillAura.mc.field_71439_g).filter(entity -> KillAura.mc.field_71439_g.func_70032_d(entity) <= 50.0f).filter(entity -> !entity.field_70128_L).filter(this::attackCheck).filter(entity -> !(entity instanceof EntityArmorStand)).sorted(Comparator.comparing(entity -> KillAura.mc.field_71439_g.func_70032_d(entity))).collect(Collectors.toList())).size() > 0) {
            final float[] arrf = getRotations(list.get(0));
            if (KillAura.mc.field_71439_g.func_70032_d((Entity)list.get(0)) > 7.0f && System.currentTimeMillis() - this.curTimeRotate >= this.rotateTimer) {
                this.setRotation(arrf[0], arrf[1], list.get(0));
                this.curTimeRotate = System.currentTimeMillis();
            }
            if (KillAura.mc.field_71439_g.func_70032_d((Entity)list.get(0)) <= 3.5 && System.currentTimeMillis() - this.curTimeHit >= this.hitDelayTimer) {
                this.setRotation(arrf[0], arrf[1], list.get(0));
                KillAura.mc.field_71442_b.func_78764_a((EntityPlayer)KillAura.mc.field_71439_g, (Entity)list.get(0));
                KillAura.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
                this.curTimeHit = System.currentTimeMillis();
            }
        }
    }
}
