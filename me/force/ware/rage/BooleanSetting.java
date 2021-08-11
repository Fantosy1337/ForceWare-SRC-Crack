package me.force.ware.rage;

public class BooleanSetting extends Setting
{
    public boolean enabled;
    
    public BooleanSetting(final String string, final boolean bl) {
        this.name = string;
        this.enabled = bl;
    }
    
    public void toggle() {
        this.enabled = !this.enabled;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean bl) {
        this.enabled = bl;
    }
}
