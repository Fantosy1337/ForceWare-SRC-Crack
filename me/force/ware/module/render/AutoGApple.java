package me.force.ware.module.render;

import me.force.ware.rage.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.settings.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.*;
import me.force.ware.module.*;

public class AutoGApple extends Module
{
    private int oldSlot;
    public NumberSetting needHealth;
    private boolean eating;
    
    boolean isFood(final ItemStack itemStack) {
        return !isNullOrEmptyStack(itemStack) && itemStack.func_77973_b() instanceof ItemAppleGold;
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (AutoGApple.mc.field_71439_g.func_110143_aJ() + AutoGApple.mc.field_71439_g.func_110139_bj() > this.needHealth.getValue() && this.eating) {
            this.eating = false;
            this.stop();
            return;
        }
        if (!this.canEat()) {
            return;
        }
        if (this.isFood(AutoGApple.mc.field_71439_g.func_184592_cb()) && AutoGApple.mc.field_71439_g.func_110143_aJ() <= this.needHealth.getValue() && this.canEat()) {
            KeyBinding.func_74510_a(AutoGApple.mc.field_71474_y.field_74313_G.func_151463_i(), true);
            this.eating = true;
        }
        if (!this.canEat()) {
            this.stop();
        }
    }
    
    public static boolean isNullOrEmptyStack(final ItemStack itemStack) {
        return itemStack == null || itemStack.func_190926_b();
    }
    
    public boolean canEat() {
        final Entity entity;
        return AutoGApple.mc.field_71476_x == null || (!((entity = AutoGApple.mc.field_71476_x.field_72308_g) instanceof EntityVillager) && !(entity instanceof EntityTameable));
    }
    
    void stop() {
        KeyBinding.func_74510_a(AutoGApple.mc.field_71474_y.field_74313_G.func_151463_i(), false);
    }
    
    public AutoGApple() {
        super("AutoApple", "eat golden apples automatically", Category.MISC);
        this.oldSlot = -1;
        this.needHealth = new NumberSetting("Health", 11.5, 1.0, 24.0, 0.5);
        this.eating = false;
    }
}
