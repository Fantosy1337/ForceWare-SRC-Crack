package me.force.ware.clickgui.component;

import me.force.ware.*;
import me.force.ware.module.*;
import me.force.ware.clickgui.component.components.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.gui.*;

public class Frame
{
    public ArrayList<Component> components;
    public Category category;
    private boolean open;
    private int width;
    private int y;
    private int x;
    private int barHeight;
    private boolean isDragging;
    public int dragX;
    public int dragY;
    
    public Frame(final Category cat) {
        this.components = new ArrayList<Component>();
        this.category = cat;
        this.width = 76;
        this.x = 5;
        this.y = 5;
        this.barHeight = 13;
        this.dragX = 0;
        this.open = true;
        this.isDragging = false;
        int tY = this.barHeight;
        for (final Module mod : Main.instance.moduleManager.getModulesInCategory(this.category)) {
            final Button modButton = new Button(mod, this, tY);
            this.components.add(modButton);
            tY += 12;
        }
    }
    
    public ArrayList<Component> getComponents() {
        return this.components;
    }
    
    public void setX(final int newX) {
        this.x = newX;
    }
    
    public void setY(final int newY) {
        this.y = newY;
    }
    
    public void setDrag(final boolean drag) {
        this.isDragging = drag;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public void setOpen(final boolean open) {
        this.open = open;
    }
    
    public void renderFrame(final FontRenderer fontRenderer) {
        Gui.func_73734_a(this.x, this.y, this.x + this.width, this.y + this.barHeight, new Color(191, 16, 210, 150).getRGB());
        fontRenderer.func_175063_a(this.category.name(), (float)(this.x + 2), this.y + 2.5f, -1);
        fontRenderer.func_175063_a(this.open ? "-" : "+", (float)(this.x + this.width - 10), this.y + 2.5f, -1);
        if (this.open && !this.components.isEmpty()) {
            Gui.func_73734_a(this.x, this.y + this.barHeight, this.x + 1, this.y + this.barHeight + 12 * this.components.size(), new Color(191, 16, 210, 150).getRGB());
            Gui.func_73734_a(this.x, this.y + this.barHeight + 12 * this.components.size(), this.x + this.width, this.y + this.barHeight + 12 * this.components.size() + 1, new Color(191, 16, 210, 150).getRGB());
            Gui.func_73734_a(this.x + this.width, this.y + this.barHeight, this.x + this.width - 1, this.y + this.barHeight + 12 * this.components.size(), new Color(191, 16, 210, 150).getRGB());
            for (final Component component : this.components) {
                component.renderComponent();
            }
        }
    }
    
    public void refresh() {
        int off = this.barHeight;
        for (final Component comp : this.components) {
            comp.setOff(off);
            off += comp.getHeight();
        }
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void updatePosition(final int mouseX, final int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - this.dragX);
            this.setY(mouseY - this.dragY);
        }
    }
    
    public boolean isWithinHeader(final int x, final int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
    }
}
