package me.force.ware.module.movement;

import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.force.ware.module.*;

public class AutoWalk extends Module
{
    @SubscribeEvent
    public void onUpdateInput(final InputUpdateEvent event) {
        event.getMovementInput().field_192832_b = 1.0f;
    }
    
    public AutoWalk() {
        super("AutoWalk", "auto walk ok", Category.MOVEMENT);
    }
}
