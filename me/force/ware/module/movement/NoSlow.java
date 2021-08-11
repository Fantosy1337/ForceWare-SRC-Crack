package me.force.ware.module.movement;

import me.force.ware.module.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoSlow extends Module
{
    public NoSlow() {
        super("NoSlow", "i like NoSlow", Category.MOVEMENT);
    }
    
    @SubscribeEvent
    public void onInput(final InputUpdateEvent event) {
        if (NoSlow.mc.field_71439_g.func_184587_cr() && !NoSlow.mc.field_71439_g.func_184218_aH()) {
            final MovementInput movementInput = event.getMovementInput();
            movementInput.field_78902_a *= 5.0f;
            final MovementInput movementInput2 = event.getMovementInput();
            movementInput2.field_192832_b *= 5.0f;
        }
    }
}
