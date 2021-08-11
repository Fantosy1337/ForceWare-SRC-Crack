package me.force.ware.module.render;

import me.force.ware.module.*;
import net.minecraftforge.client.event.*;
import me.force.ware.*;
import net.minecraft.client.*;
import java.awt.*;
import java.util.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Arraylist extends Module
{
    public Arraylist() {
        super("HUD", "Draws the module list on your screen", Category.RENDER);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text e) {
        Main.instance.moduleManager.modules.sort(Comparator.comparingInt(m -> -Arraylist.mc.field_71466_p.func_78256_a(m.getName())));
        final ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
        int y = 2;
        for (final Module mod : Main.instance.moduleManager.getModuleList()) {
            if (!mod.getName().equalsIgnoreCase("HUD") && mod.isToggled() && mod.visible) {
                final FontRenderer fr = Minecraft.func_71410_x().field_71466_p;
                fr.func_78276_b(mod.getName(), sr.func_78326_a() - fr.func_78256_a(mod.getName()) - 1, y, new Color(187, 30, 68).getRGB());
                y += fr.field_78288_b;
            }
        }
    }
}
