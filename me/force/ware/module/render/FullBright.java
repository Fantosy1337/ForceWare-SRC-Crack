package me.force.ware.module.render;

import me.force.ware.module.*;

public class FullBright extends Module
{
    public float oldGamma;
    
    public FullBright() {
        super("FullBright", "all bright", Category.RENDER);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.oldGamma = FullBright.mc.field_71474_y.field_74333_Y;
        FullBright.mc.field_71474_y.field_74333_Y = 10.0f;
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        FullBright.mc.field_71474_y.field_74333_Y = this.oldGamma;
    }
}
