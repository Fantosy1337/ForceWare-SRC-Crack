package me.force.ware.module.combat;

import me.force.ware.module.*;
import me.force.ware.*;
import me.force.ware.settings.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Velocity extends Module
{
    public Velocity() {
        super("Velocity", "Reduces knockback", Category.COMBAT);
        Main.instance.settingsManager.rSetting(new Setting("Horizontal", this, 90.0, 0.0, 100.0, true));
        Main.instance.settingsManager.rSetting(new Setting("Vertical", this, 100.0, 0.0, 100.0, true));
    }
    
    @SubscribeEvent
    public void onLivingUpdate(final LivingEvent.LivingUpdateEvent e) {
        if (Main.instance.destructed) {
            return;
        }
        if (Velocity.mc.field_71439_g == null) {
            return;
        }
        final float horizontal = (float)Main.instance.settingsManager.getSettingByName(this, "Horizontal").getValDouble();
        final float vertical = (float)Main.instance.settingsManager.getSettingByName(this, "Vertical").getValDouble();
        if (Velocity.mc.field_71439_g.field_70737_aN == Velocity.mc.field_71439_g.field_70738_aO && Velocity.mc.field_71439_g.field_70738_aO > 0) {
            final EntityPlayerSP field_71439_g = Velocity.mc.field_71439_g;
            field_71439_g.field_70159_w *= horizontal / 100.0f;
            final EntityPlayerSP field_71439_g2 = Velocity.mc.field_71439_g;
            field_71439_g2.field_70181_x *= vertical / 100.0f;
            final EntityPlayerSP field_71439_g3 = Velocity.mc.field_71439_g;
            field_71439_g3.field_70179_y *= horizontal / 100.0f;
        }
    }
}
