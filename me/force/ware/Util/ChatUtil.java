package me.force.ware.Util;

import net.minecraft.client.*;
import net.minecraft.util.text.*;

public class ChatUtil
{
    public static void sendChatMessage(final String string) {
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("§9ForceWare: §7" + string));
    }
    
    public static void error(final String string) {
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("§8§l[§3§lRage R8:§8§l] §c§lERROR: §c" + string));
    }
    
    public static void info(final String string) {
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("§8§l[§3§lRage R8:§8§l] §e" + string));
    }
    
    public static void warning(String string) {
        string = "Could not find empty slot. Operation has been aborted.";
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("§8§l[§3§lRage R8:§8§l] §b§lWARNING: §b" + string));
    }
    
    public static void clear(final int n) {
        for (int i = 0; i < n; ++i) {
            Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString(""));
        }
    }
    
    public static void syntax(final String string) {
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("§8§l[§3§lRage R8:§8§l] §a§lSYNTAX: §a" + string));
    }
}
