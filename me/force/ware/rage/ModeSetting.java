package me.force.ware.rage;

import java.util.*;

public class ModeSetting extends Setting
{
    public List modes;
    public int index;
    public String activeMode;
    
    public ModeSetting(final String string, final String string2, final String... arrstring) {
        this.name = string;
        this.modes = Arrays.asList(arrstring);
        this.activeMode = string2;
        this.index = this.modes.indexOf(string2);
    }
    
    public void cycle() {
        this.index = ((this.index < this.modes.size() - 1) ? (++this.index) : 0);
        this.activeMode = this.modes.get(this.index);
    }
    
    public String getMode() {
        return this.modes.get(this.index);
    }
    
    public void setMode(final String string) {
        this.index = this.modes.indexOf(string);
    }
    
    public boolean is(final String string) {
        return this.index == this.modes.indexOf(string);
    }
}
