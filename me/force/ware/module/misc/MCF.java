package me.force.ware.module.misc;

import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import me.force.ware.friends.*;
import me.force.ware.Util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.force.ware.module.*;

public class MCF extends Module
{
    TimerUtil timerUtil;
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (Minecraft.func_71410_x().field_71476_x != null && Minecraft.func_71410_x().field_71476_x.field_72308_g instanceof EntityPlayer && MCF.mc.field_71474_y.field_74322_I.func_151470_d() && this.timerUtil.hasReached(100.0)) {
            final String string = Minecraft.func_71410_x().field_71476_x.field_72308_g.func_70005_c_();
            if (!FriendManager.isFriend(string)) {
                FriendManager.addFriend(string, string);
                ChatUtil.sendChatMessage("Added " + string + " as a friend.");
                this.timerUtil.reset();
                return;
            }
            if (this.timerUtil.hasReached(100.0)) {
                FriendManager.removeFriend(string);
                ChatUtil.sendChatMessage("Removed " + string + ".");
                this.timerUtil.reset();
            }
        }
    }
    
    public MCF() {
        super("MCF", "middle click friends", Category.PLAYER);
        this.timerUtil = new TimerUtil();
    }
}
