package me.force.ware.module.combat;

import net.minecraft.entity.player.*;
import me.force.ware.module.*;
import java.util.*;

public class AntiBot extends Module
{
    public static List<EntityPlayer> bots;
    
    public AntiBot() {
        super("AntiBot", "Prevents you from targetting bots", Category.COMBAT);
    }
    
    static {
        AntiBot.bots = new ArrayList<EntityPlayer>();
    }
}
