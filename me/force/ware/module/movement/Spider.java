package me.force.ware.module.movement;

import me.force.ware.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Spider extends Module
{
    public Spider() {
        super("Spider", "Climb like a spider", Category.MOVEMENT);
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (Spider.mc.field_71439_g.field_70123_F) {
            Spider.mc.field_71439_g.field_70181_x = 0.2;
        }
    }
}
