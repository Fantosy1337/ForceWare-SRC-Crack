package me.force.ware.module.render;

import me.force.ware.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoFall extends Module
{
    public NoFall() {
        super("NoFall", "disable fall damage", Category.PLAYER);
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (NoFall.mc.field_71439_g.field_70143_R > 3.0f) {
            NoFall.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer(true));
            NoFall.mc.field_71439_g.field_70122_E = true;
            NoFall.mc.field_71439_g.field_70181_x = -0.48876887;
            NoFall.mc.field_71439_g.field_70143_R = 0.0f;
        }
    }
}
