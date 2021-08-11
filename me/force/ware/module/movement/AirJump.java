package me.force.ware.module.movement;

import me.force.ware.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AirJump extends Module
{
    public AirJump() {
        super("EblanFly", "", Category.MOVEMENT);
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (AirJump.mc.field_71474_y.field_74314_A.func_151468_f()) {
            AirJump.mc.field_71439_g.field_70181_x = 0.413213;
            final double d = AirJump.mc.field_71439_g.field_70165_t;
            final double d2 = AirJump.mc.field_71439_g.field_70163_u;
            final double d3 = AirJump.mc.field_71439_g.field_70161_v;
            AirJump.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.PositionRotation(d, d2 + 0.413213, d3, AirJump.mc.field_71439_g.field_70177_z, AirJump.mc.field_71439_g.field_70125_A, true));
            AirJump.mc.field_71439_g.func_180426_a(d, d2 + 0.413213, d3, AirJump.mc.field_71439_g.field_70177_z, AirJump.mc.field_71439_g.field_70125_A, 1, false);
        }
    }
}
