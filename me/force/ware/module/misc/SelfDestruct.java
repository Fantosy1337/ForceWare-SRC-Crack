package me.force.ware.module.misc;

import me.force.ware.module.*;
import me.force.ware.*;

public class SelfDestruct extends Module
{
    public SelfDestruct() {
        super("SelfDestruct", "Destructs", Category.MISC);
    }
    
    @Override
    public void onEnable() {
        Main.instance.onDestruct();
    }
}
