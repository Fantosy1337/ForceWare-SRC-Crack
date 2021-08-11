package me.force.ware.module.render;

import net.minecraft.entity.passive.*;
import net.minecraft.client.entity.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import me.force.ware.module.*;

public class FakePlayer extends Module
{
    private EntityDonkey RidingEntity;
    private EntityOtherPlayerMP Original;
    
    @Override
    public void onEnable() {
        if (FakePlayer.mc.field_71441_e == null) {
            this.toggle();
            return;
        }
        (this.Original = new EntityOtherPlayerMP((World)FakePlayer.mc.field_71441_e, FakePlayer.mc.field_71439_g.func_146103_bH())).func_82149_j((Entity)FakePlayer.mc.field_71439_g);
        this.Original.field_70177_z = FakePlayer.mc.field_71439_g.field_70177_z;
        this.Original.field_70759_as = FakePlayer.mc.field_71439_g.field_70759_as;
        this.Original.field_71071_by.func_70455_b(FakePlayer.mc.field_71439_g.field_71071_by);
        FakePlayer.mc.field_71441_e.func_73027_a(-1048575, (Entity)this.Original);
        if (FakePlayer.mc.field_71439_g.func_184218_aH() && FakePlayer.mc.field_71439_g.func_184187_bx() instanceof EntityDonkey) {
            final EntityDonkey entityDonkey = (EntityDonkey)FakePlayer.mc.field_71439_g.func_184187_bx();
            (this.RidingEntity = new EntityDonkey((World)FakePlayer.mc.field_71441_e)).func_82149_j((Entity)entityDonkey);
            this.RidingEntity.func_110207_m(entityDonkey.func_190695_dh());
            FakePlayer.mc.field_71441_e.func_73027_a(-1048574, (Entity)this.RidingEntity);
            this.Original.func_184205_a((Entity)this.RidingEntity, true);
        }
    }
    
    public FakePlayer() {
        super("FakePlayer", "second player", Category.MISC);
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        if (this.Original != null) {
            if (this.Original.func_184218_aH()) {
                this.Original.func_184210_p();
            }
            FakePlayer.mc.field_71441_e.func_72900_e((Entity)this.Original);
        }
        if (this.RidingEntity != null) {
            FakePlayer.mc.field_71441_e.func_72900_e((Entity)this.RidingEntity);
        }
    }
}
