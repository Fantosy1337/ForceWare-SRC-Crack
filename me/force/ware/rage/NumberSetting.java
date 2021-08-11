package me.force.ware.rage;

public class NumberSetting extends Setting
{
    public double increment;
    public double value;
    public double minimum;
    public double maximum;
    
    public NumberSetting(final String string, final double d, final double d2, final double d3, final double d4) {
        this.name = string;
        this.value = d;
        this.minimum = d2;
        this.maximum = d3;
        this.increment = d4;
    }
    
    public double getMaximum() {
        return this.maximum;
    }
    
    public void setMaximum(final double d) {
        this.maximum = d;
    }
    
    public double getMinimum() {
        return this.minimum;
    }
    
    public void setMinimum(final double d) {
        this.minimum = d;
    }
    
    public void increment(final boolean bl) {
        this.setValue(this.getValue() + (bl ? 1 : -1) * this.increment);
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(final double d) {
        final double d2 = 1.0 / this.increment;
        this.value = Math.round(Math.max(this.minimum, Math.min(this.maximum, d)) * d2) / d2;
    }
    
    public double getIncrement() {
        return this.increment;
    }
    
    public void setIncrement(final double d) {
        this.increment = d;
    }
}
