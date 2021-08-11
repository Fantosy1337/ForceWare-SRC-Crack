package me.force.ware.module.render;

import me.force.ware.module.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class WallHack extends Module
{
    public WallHack() {
        super("WallHack", "WallHack", Category.RENDER);
    }
    
    @SubscribeEvent
    public void onRenderEntityPre(final RenderPlayerEvent.Pre pre) {
        GL11.glEnable(32823);
        GL11.glPolygonOffset(1.0f, -1100000.0f);
        GL11.glDisable(2896);
    }
    
    @SubscribeEvent
    public void onRenderEntityPost(final RenderPlayerEvent.Post post) {
        GL11.glDisable(32823);
        GL11.glPolygonOffset(1.0f, 1100000.0f);
        GL11.glEnable(2896);
    }
}
