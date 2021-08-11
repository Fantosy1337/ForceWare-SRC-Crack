package me.force.ware.module.combat;

import me.force.ware.rage.*;
import net.minecraft.client.*;
import net.minecraft.potion.*;
import me.force.ware.*;
import java.util.function.*;
import java.util.stream.*;
import net.minecraft.entity.*;
import me.force.ware.Util.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.force.ware.module.*;
import me.force.ware.settings.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.item.*;
import me.force.ware.friends.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import java.util.*;

public class TargetStrafe extends Module
{
    public TimerUtil timerUtil;
    public BooleanSetting playerSet;
    public BooleanSetting animalSett;
    public BooleanSetting mobSet;
    public BooleanSetting invisibleTarget;
    public boolean sideDirection;
    public static int direction;
    public double increment;
    public BooleanSetting renderCircle;
    
    public double getMovementSpeed() {
        double d = 0.2873;
        if (Minecraft.func_71410_x().field_71439_g.func_70644_a((Potion)Objects.requireNonNull(Potion.func_188412_a(1)))) {
            final int n = Objects.requireNonNull(Minecraft.func_71410_x().field_71439_g.func_70660_b((Potion)Objects.requireNonNull(Potion.func_188412_a(1)))).func_76458_c();
            d *= 1.0 + 0.2 * (n + 1);
        }
        return d;
    }
    
    public Entity getTargetEz() {
        if (TargetStrafe.mc.field_71439_g == null || TargetStrafe.mc.field_71439_g.field_70128_L) {
            return null;
        }
        final List list = (List)TargetStrafe.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity != TargetStrafe.mc.field_71439_g).filter(entity -> TargetStrafe.mc.field_71439_g.func_70032_d(entity) <= Main.instance.settingsManager.getSettingByName(this, "Range").getValDouble()).filter(entity -> !entity.field_70128_L).filter(this::lambda$getTargetEz$3).sorted(Comparator.comparing(entity -> TargetStrafe.mc.field_71439_g.func_70032_d(entity))).collect(Collectors.toList());
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    public final boolean doStrafeAtSpeed(final double d) {
        final boolean bl = true;
        final Entity entity = this.getTargetEz();
        if (entity != null) {
            if (TargetStrafe.mc.field_71439_g.field_70122_E) {
                TargetStrafe.mc.field_71439_g.func_70664_aZ();
            }
            final float[] arrf = RotationUtils.getNeededRotations((EntityLivingBase)entity);
            if (Minecraft.func_71410_x().field_71439_g.func_70032_d(entity) <= Main.instance.settingsManager.getSettingByName(this, "Range").getValDouble()) {
                TargetStrafe.mc.field_71439_g.field_70761_aq = arrf[0];
                TargetStrafe.mc.field_71439_g.field_70759_as = arrf[0];
                setSpeed(d - (0.1 - Main.instance.settingsManager.getSettingByName(this, "Speed").getValDouble() / 100.0), arrf[0], TargetStrafe.direction, 0.0);
            }
            else {
                setSpeed(d - (0.1 - Main.instance.settingsManager.getSettingByName(this, "Speed").getValDouble() / 100.0), arrf[0], TargetStrafe.direction, 1.0);
                TargetStrafe.mc.field_71439_g.field_70761_aq = arrf[0];
                TargetStrafe.mc.field_71439_g.field_70759_as = arrf[0];
            }
        }
        return bl;
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (TargetStrafe.mc.field_71439_g.field_70123_F && this.timerUtil.hasReached(80.0)) {
            this.timerUtil.reset();
            this.invertStrafe();
        }
        TargetStrafe.mc.field_71439_g.field_71158_b.field_192832_b = 0.0f;
        final double d = this.getMovementSpeed();
        this.doStrafeAtSpeed(d);
    }
    
    public TargetStrafe() {
        super("TargetStrafe", "targets closest player. note: use with kill_aura", Category.COMBAT);
        this.sideDirection = true;
        this.increment = 0.05;
        Main.instance.settingsManager.rSetting(new Setting("Speed", this, -7.0, -7.0, 5.0, true));
        Main.instance.settingsManager.rSetting(new Setting("Range", this, 3.0, 0.0, 5.0, true));
        this.invisibleTarget = new BooleanSetting("Invisible", false);
        this.playerSet = new BooleanSetting("Players", true);
        this.mobSet = new BooleanSetting("Mobs", false);
        this.animalSett = new BooleanSetting("Animals", false);
        this.timerUtil = new TimerUtil();
    }
    
    public static void setSpeed(final double d, final float f, final double d2, final double d3) {
        double d4 = d3;
        double d5 = d2;
        float f2 = f;
        if (d4 == 0.0 && d5 == 0.0) {
            TargetStrafe.mc.field_71439_g.field_70179_y = 0.0;
            TargetStrafe.mc.field_71439_g.field_70159_w = 0.0;
        }
        else {
            if (d4 != 0.0) {
                if (d5 > 0.0) {
                    f2 += ((d4 > 0.0) ? -45 : 45);
                }
                else if (d5 < 0.0) {
                    f2 += ((d4 > 0.0) ? 45 : -45);
                }
                d5 = 0.0;
                if (d4 > 0.0) {
                    d4 = 1.0;
                }
                else if (d4 < 0.0) {
                    d4 = -1.0;
                }
            }
            final double d6 = Math.cos(Math.toRadians(f2 + 90.0f));
            final double d7 = Math.sin(Math.toRadians(f2 + 90.0f));
            TargetStrafe.mc.field_71439_g.field_70159_w = d4 * d * d6 + d5 * d * d7;
            TargetStrafe.mc.field_71439_g.field_70179_y = d4 * d * d7 - d5 * d * d6;
        }
    }
    
    private void drawRadius(final Entity entity, final double d, final double d2, final double d3, final double d4) {
        GlStateManager.func_179126_j();
        final double d5 = 0.0;
        Double.compare(d5, 0.01);
    }
    
    private boolean lambda$getTargetEz$3(final Entity entity) {
        return this.attackCheck(entity);
    }
    
    public void onRenderWorldLast(final float f) {
        if (TargetStrafe.mc.field_71439_g == null || TargetStrafe.mc.field_71439_g.field_70128_L) {
            return;
        }
        final List list = (List)TargetStrafe.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity != TargetStrafe.mc.field_71439_g).filter(entity -> TargetStrafe.mc.field_71439_g.func_70032_d(entity) <= 15.0f).filter(entity -> !entity.field_70128_L).filter(this::attackCheckin).filter(entity -> !(entity instanceof EntityArmorStand)).sorted(Comparator.comparing(entity -> TargetStrafe.mc.field_71439_g.func_70032_d(entity))).collect(Collectors.toList());
        if (list.size() > 0) {
            final double d = Main.instance.settingsManager.getSettingByName(this, "Range").getValDouble();
            final Entity entity2 = list.get(0);
            final double d2 = entity2.field_70142_S + (entity2.field_70165_t - entity2.field_70142_S) * f - TargetStrafe.mc.func_175598_ae().field_78730_l;
            double d3 = entity2.field_70137_T + (entity2.field_70163_u - entity2.field_70137_T) * f - TargetStrafe.mc.func_175598_ae().field_78731_m;
            final double d4 = entity2.field_70136_U + (entity2.field_70161_v - entity2.field_70136_U) * f - TargetStrafe.mc.func_175598_ae().field_78728_n;
            if (this.increment < 2.03 && this.sideDirection) {
                if (this.increment >= 2.0) {
                    this.sideDirection = false;
                    this.increment = 2.0;
                    d3 -= this.increment;
                }
                this.increment += 0.02;
                this.drawRadius(list.get(0), d2, d3 += this.increment, d4, d);
            }
            if (this.increment > 0.01 && !this.sideDirection) {
                if (this.increment <= 0.02) {
                    this.sideDirection = true;
                    this.increment = 0.01;
                }
                this.increment -= 0.02;
                this.drawRadius(list.get(0), d2, d3 += this.increment, d4, d);
            }
        }
    }
    
    public boolean attackCheck(final Entity entity) {
        for (final Object friendw : FriendManager.friends) {
            final Friend friend = (Friend)friendw;
            if (entity.func_70005_c_() != friend.name) {
                continue;
            }
            return false;
        }
        return (this.invisibleTarget.isEnabled() && entity.func_82150_aj() && ((this.playerSet.isEnabled() && entity instanceof EntityPlayer) || (this.mobSet.isEnabled() && entity instanceof EntityMob) || (this.animalSett.isEnabled() && entity instanceof EntityAnimal))) || (this.playerSet.isEnabled() && entity instanceof EntityPlayer && !entity.func_82150_aj()) || (this.animalSett.isEnabled() && entity instanceof EntityAnimal && !entity.func_82150_aj()) || (this.mobSet.isEnabled() && entity instanceof EntityMob && !entity.func_82150_aj());
    }
    
    private void invertStrafe() {
        TargetStrafe.direction = -TargetStrafe.direction;
    }
    
    public boolean attackCheckin(final Entity entity) {
        return entity instanceof EntityPlayer && ((EntityPlayer)entity).func_110143_aJ() > 0.0f && Math.abs(TargetStrafe.mc.field_71439_g.field_70177_z - RotationUtils.getNeededRotations((EntityLivingBase)entity)[0]) % 180.0f < 190.0f && !entity.func_82150_aj() && !entity.func_110124_au().equals(TargetStrafe.mc.field_71439_g.func_110124_au());
    }
    
    static {
        TargetStrafe.direction = -1;
    }
}
