package me.force.ware.module;

import net.minecraft.client.*;
import me.force.ware.*;
import net.minecraftforge.common.*;

public class Module
{
    protected static Minecraft mc;
    private String name;
    private String description;
    private int key;
    private Category category;
    private boolean toggled;
    public boolean visible;
    
    public Module(final String name, final String description, final Category category) {
        this.visible = true;
        this.name = name;
        this.description = description;
        this.key = 0;
        this.category = category;
        this.toggled = false;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public void setKey(final int key) {
        this.key = key;
        if (Main.instance.saveLoad != null) {
            Main.instance.saveLoad.save();
        }
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    public void setToggled(final boolean toggled) {
        this.toggled = toggled;
        if (this.toggled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
        if (Main.instance.saveLoad != null) {
            Main.instance.saveLoad.save();
        }
    }
    
    public void toggle() {
        this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
        if (Main.instance.saveLoad != null) {
            Main.instance.saveLoad.save();
        }
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    static {
        Module.mc = Minecraft.func_71410_x();
    }
}
