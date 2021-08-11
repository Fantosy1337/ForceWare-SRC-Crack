package me.force.ware.module.render;

import me.force.ware.module.*;
import me.force.ware.*;
import net.minecraft.client.gui.*;

public class ClickGUI extends Module
{
    public ClickGUI() {
        super("ClickGUI", "Allows you to enable and disable modules", Category.RENDER);
        this.setKey(54);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        ClickGUI.mc.func_147108_a((GuiScreen)Main.instance.clickGui);
        this.setToggled(false);
    }
}
