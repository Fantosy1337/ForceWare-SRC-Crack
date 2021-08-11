package me.force.ware.module.combat;

import me.force.ware.module.*;
import me.force.ware.*;
import me.force.ware.settings.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import net.minecraft.client.settings.*;
import net.minecraftforge.fml.common.eventhandler.*;
import io.netty.util.internal.*;

public class AutoClicker extends Module
{
    private long lastClick;
    private long hold;
    private double speed;
    private double holdLength;
    private double min;
    private double max;
    
    public AutoClicker() {
        super("AutoClicker", "Automatically clicks when you hold down left click", Category.COMBAT);
        Main.instance.settingsManager.rSetting(new Setting("MinCPS", this, 8.0, 1.0, 20.0, false));
        Main.instance.settingsManager.rSetting(new Setting("MaxCPS", this, 12.0, 1.0, 20.0, false));
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.RenderTickEvent e) {
        if (Main.instance.destructed) {
            return;
        }
        if (Mouse.isButtonDown(0)) {
            if (System.currentTimeMillis() - this.lastClick > this.speed * 1000.0) {
                this.lastClick = System.currentTimeMillis();
                if (this.hold < this.lastClick) {
                    this.hold = this.lastClick;
                }
                final int key = AutoClicker.mc.field_71474_y.field_74312_F.func_151463_i();
                KeyBinding.func_74510_a(key, true);
                KeyBinding.func_74507_a(key);
                this.updateVals();
            }
            else if (System.currentTimeMillis() - this.hold > this.holdLength * 1000.0) {
                KeyBinding.func_74510_a(AutoClicker.mc.field_71474_y.field_74312_F.func_151463_i(), false);
                this.updateVals();
            }
        }
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.updateVals();
    }
    
    private void updateVals() {
        this.min = Main.instance.settingsManager.getSettingByName(this, "MinCPS").getValDouble();
        this.max = Main.instance.settingsManager.getSettingByName(this, "MaxCPS").getValDouble();
        if (this.min >= this.max) {
            this.max = this.min + 1.0;
        }
        this.speed = 1.0 / ThreadLocalRandom.current().nextDouble(this.min - 0.2, this.max);
        this.holdLength = this.speed / ThreadLocalRandom.current().nextDouble(this.min, this.max);
    }
}
