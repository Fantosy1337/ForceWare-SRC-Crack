package me.force.ware.module.combat;

import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import java.util.stream.*;
import me.force.ware.*;
import java.util.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.force.ware.module.*;
import me.force.ware.settings.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;

public class HitBox extends Module
{
    public void setEntityBoundingBoxSize(final EntityPlayer entityPlayer, final float f, final float f2) {
        final EntitySize entitySize = this.getEntitySize(entityPlayer);
        entityPlayer.field_70130_N = entitySize.width;
        entityPlayer.field_70131_O = entitySize.height;
        final double d = f / 2.0;
        entityPlayer.func_174826_a(new AxisAlignedBB(entityPlayer.field_70165_t - d, entityPlayer.field_70163_u, entityPlayer.field_70161_v - d, entityPlayer.field_70165_t + d, entityPlayer.field_70163_u + f2, entityPlayer.field_70161_v + d));
    }
    
    public void setHitBoxForEntities() {
        if (HitBox.mc.field_71439_g == null || HitBox.mc.field_71439_g.field_70128_L) {
            return;
        }
        final List list = (List)HitBox.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity != HitBox.mc.field_71439_g).filter(entity -> HitBox.mc.field_71439_g.func_70032_d(entity) <= 200.0f).filter(entity -> !entity.field_70128_L).filter(entity -> entity instanceof EntityPlayer).sorted(Comparator.comparing(entity -> HitBox.mc.field_71439_g.func_70032_d(entity))).collect(Collectors.toList());
        for (final Object entity2w : list) {
            final Entity entity2 = (Entity)entity2w;
            final float f = (float)Main.instance.settingsManager.getSettingByName(this, "X").getValDouble();
            final float f2 = (float)Main.instance.settingsManager.getSettingByName(this, "Y").getValDouble();
            this.setEntityBoundingBoxSize((EntityPlayer)entity2, f, f2);
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent clientTickEvent) {
        this.setHitBoxForEntities();
    }
    
    public HitBox() {
        super("HitBox", "bigger players range attack", Category.COMBAT);
        Main.instance.settingsManager.rSetting(new Setting("X", this, 1.0, 0.0, 3.0, true));
        Main.instance.settingsManager.rSetting(new Setting("Y", this, 2.0, 0.0, 3.0, true));
    }
    
    public EntitySize getEntitySize(final EntityPlayer entityPlayer) {
        final EntitySize entitySize = new EntitySize(0.6f, 1.8f);
        return entitySize;
    }
    
    public boolean check(final EntityLivingBase entityLivingBase) {
        return !(entityLivingBase instanceof EntityPlayerSP) && entityLivingBase != HitBox.mc.field_71439_g && !entityLivingBase.field_70128_L && entityLivingBase.func_110143_aJ() >= 0.0f && entityLivingBase instanceof EntityPlayer;
    }
}
