package me.force.ware.module;

import me.force.ware.module.render.*;
import me.force.ware.module.movement.*;
import me.force.ware.module.combat.*;
import me.force.ware.module.misc.*;
import java.util.*;

public class ModuleManager
{
    public ArrayList<Module> modules;
    
    public ModuleManager() {
        (this.modules = new ArrayList<Module>()).clear();
        this.modules.add(new ClickGUI());
        this.modules.add(new Arraylist());
        this.modules.add(new AutoGApple());
        this.modules.add(new Derp());
        this.modules.add(new NoSlow());
        this.modules.add(new WaterMark());
        this.modules.add(new Esp());
        this.modules.add(new FakePlayer());
        this.modules.add(new FullBright());
        this.modules.add(new NoFall());
        this.modules.add(new Tracers());
        this.modules.add(new WallHack());
        this.modules.add(new Sprint());
        this.modules.add(new AutoClicker());
        this.modules.add(new Strafe());
        this.modules.add(new AirJump());
        this.modules.add(new AutoWalk());
        this.modules.add(new Spider());
        this.modules.add(new MCF());
        this.modules.add(new BowSpam());
        this.modules.add(new HitBox());
        this.modules.add(new KillAura());
        this.modules.add(new TargetStrafe());
        this.modules.add(new Velocity());
        this.modules.add(new AntiBot());
        this.modules.add(new SelfDestruct());
    }
    
    public Module getModule(final String name) {
        for (final Module m : this.modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
    
    public ArrayList<Module> getModuleList() {
        return this.modules;
    }
    
    public ArrayList<Module> getModulesInCategory(final Category c) {
        final ArrayList<Module> mods = new ArrayList<Module>();
        for (final Module m : this.modules) {
            if (m.getCategory() == c) {
                mods.add(m);
            }
        }
        return mods;
    }
}
