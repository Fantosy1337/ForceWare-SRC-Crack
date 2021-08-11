package me.force.ware;

import net.minecraftforge.fml.common.*;
import me.force.ware.settings.*;
import me.force.ware.clickgui.*;
import me.force.ware.autosave.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import org.lwjgl.input.*;
import me.force.ware.module.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = "forceware", version = "b2")
public class Main
{
    public static Main instance;
    public ModuleManager moduleManager;
    public SettingsManager settingsManager;
    public ClickGui clickGui;
    public SaveLoad saveLoad;
    public boolean destructed;
    
    public Main() {
        this.destructed = false;
    }
    
    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.settingsManager = new SettingsManager();
        this.moduleManager = new ModuleManager();
        this.clickGui = new ClickGui();
        this.saveLoad = new SaveLoad();
    }
    
    @SubscribeEvent
    public void key(final InputEvent.KeyInputEvent e) {
        if (Minecraft.func_71410_x().field_71441_e == null || Minecraft.func_71410_x().field_71439_g == null) {
            return;
        }
        try {
            if (Keyboard.isCreated() && Keyboard.getEventKeyState()) {
                final int keyCode = Keyboard.getEventKey();
                if (keyCode <= 0) {
                    return;
                }
                for (final Module m : this.moduleManager.modules) {
                    if (m.getKey() == keyCode && keyCode > 0) {
                        m.toggle();
                    }
                }
            }
        }
        catch (Exception q) {
            q.printStackTrace();
        }
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        (Main.instance = new Main()).init();
    }
    
    public void onDestruct() {
        if (Minecraft.func_71410_x().field_71462_r != null && Minecraft.func_71410_x().field_71439_g != null) {
            Minecraft.func_71410_x().field_71439_g.func_71053_j();
        }
        this.destructed = true;
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        for (int k = 0; k < this.moduleManager.modules.size(); ++k) {
            final Module m = this.moduleManager.modules.get(k);
            MinecraftForge.EVENT_BUS.unregister((Object)m);
            this.moduleManager.getModuleList().remove(m);
        }
        this.moduleManager = null;
        this.clickGui = null;
    }
}
