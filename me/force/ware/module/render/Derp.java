package me.force.ware.module.render;

import java.util.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.force.ware.module.*;

public class Derp extends Module
{
    private final Random e;
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final float f = this.e.nextFloat() * 360.0f;
        final float cfr_ignored_0 = this.e.nextFloat() * 180.0f - 90.0f;
        Derp.mc.field_71439_g.field_70759_as = f;
        Derp.mc.field_71439_g.field_70761_aq = f;
    }
    
    public Derp() {
        super("Derp", "rotating like bitch", Category.RENDER);
        this.e = new Random();
    }
}
