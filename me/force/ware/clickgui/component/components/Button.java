package me.force.ware.clickgui.component.components;

import me.force.ware.module.*;
import me.force.ware.clickgui.component.*;
import me.force.ware.*;
import me.force.ware.settings.*;
import me.force.ware.clickgui.component.components.sub.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import me.force.ware.clickgui.*;

public class Button extends Component
{
    public Module mod;
    public Frame parent;
    public int offset;
    private boolean isHovered;
    private ArrayList<Component> subcomponents;
    public boolean open;
    private int height;
    
    public Button(final Module mod, final Frame parent, final int offset) {
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
        this.subcomponents = new ArrayList<Component>();
        this.open = false;
        this.height = 12;
        int opY = offset + 12;
        if (Main.instance.settingsManager.getSettingsByMod(mod) != null) {
            for (final Setting s : Main.instance.settingsManager.getSettingsByMod(mod)) {
                if (s.isCombo()) {
                    this.subcomponents.add(new ModeButton(s, this, mod, opY));
                    opY += 12;
                }
                if (s.isSlider()) {
                    this.subcomponents.add(new Slider(s, this, opY));
                    opY += 12;
                }
                if (s.isCheck()) {
                    this.subcomponents.add(new Checkbox(s, this, opY));
                    opY += 12;
                }
            }
        }
        this.subcomponents.add(new Keybind(this, opY));
        this.subcomponents.add(new VisibleButton(this, mod, opY));
    }
    
    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
        int opY = this.offset + 12;
        for (final Component comp : this.subcomponents) {
            comp.setOff(opY);
            opY += 12;
        }
    }
    
    @Override
    public void renderComponent() {
        Gui.func_73734_a(this.parent.getX(), this.parent.getY() + this.offset, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + 12 + this.offset, this.isHovered ? (this.mod.isToggled() ? new Color(-14540254).darker().getRGB() : -14540254) : (this.mod.isToggled() ? new Color(14, 14, 14).getRGB() : -15658735));
        Minecraft.func_71410_x().field_71466_p.func_175063_a(this.mod.getName(), (float)(this.parent.getX() + 2), (float)(this.parent.getY() + this.offset + 2), this.mod.isToggled() ? 10066329 : -1);
        if (this.subcomponents.size() > 2) {
            Minecraft.func_71410_x().field_71466_p.func_175063_a(this.open ? "-" : "+", (float)(this.parent.getX() + this.parent.getWidth() - 10), (float)(this.parent.getY() + this.offset + 2), -1);
        }
        if (this.open && !this.subcomponents.isEmpty()) {
            for (final Component comp : this.subcomponents) {
                comp.renderComponent();
            }
            Gui.func_73734_a(this.parent.getX() + 2, this.parent.getY() + this.offset + 12, this.parent.getX() + 3, this.parent.getY() + this.offset + (this.subcomponents.size() + 1), ClickGui.color);
        }
    }
    
    @Override
    public int getHeight() {
        if (this.open) {
            return 12 * (this.subcomponents.size() + 1);
        }
        return 12;
    }
    
    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
        this.isHovered = this.isMouseOnButton(mouseX, mouseY);
        if (!this.subcomponents.isEmpty()) {
            for (final Component comp : this.subcomponents) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            this.mod.toggle();
        }
        if (this.isMouseOnButton(mouseX, mouseY) && button == 1) {
            this.open = !this.open;
            this.parent.refresh();
        }
        for (final Component comp : this.subcomponents) {
            comp.mouseClicked(mouseX, mouseY, button);
        }
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        for (final Component comp : this.subcomponents) {
            comp.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }
    
    @Override
    public void keyTyped(final char typedChar, final int key) {
        for (final Component comp : this.subcomponents) {
            comp.keyTyped(typedChar, key);
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.parent.getX() && x < this.parent.getX() + this.parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset;
    }
}
