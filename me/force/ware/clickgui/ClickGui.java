package me.force.ware.clickgui;

import net.minecraft.client.gui.*;
import me.force.ware.module.*;
import me.force.ware.clickgui.component.*;
import java.util.*;
import java.io.*;
import java.awt.*;

public class ClickGui extends GuiScreen
{
    public static ArrayList<Frame> frames;
    public static int color;
    
    public ClickGui() {
        ClickGui.frames = new ArrayList<Frame>();
        int frameX = 100;
        for (final Category category : Category.values()) {
            final Frame frame = new Frame(category);
            frame.setX(frameX);
            ClickGui.frames.add(frame);
            frameX += frame.getWidth() + 1;
        }
    }
    
    public void func_73866_w_() {
    }
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        for (final Frame frame : ClickGui.frames) {
            frame.renderFrame(this.field_146289_q);
            frame.updatePosition(mouseX, mouseY);
            for (final Component comp : frame.getComponents()) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }
    
    protected void func_73864_a(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        for (final Frame frame : ClickGui.frames) {
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 0) {
                frame.setDrag(true);
                frame.dragX = mouseX - frame.getX();
                frame.dragY = mouseY - frame.getY();
            }
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 1) {
                frame.setOpen(!frame.isOpen());
            }
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
    }
    
    protected void func_73869_a(final char typedChar, final int keyCode) {
        for (final Frame frame : ClickGui.frames) {
            if (frame.isOpen() && keyCode != 1 && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.keyTyped(typedChar, keyCode);
                }
            }
        }
        if (keyCode == 1) {
            this.field_146297_k.func_147108_a((GuiScreen)null);
        }
    }
    
    protected void func_146286_b(final int mouseX, final int mouseY, final int state) {
        for (final Frame frame : ClickGui.frames) {
            frame.setDrag(false);
        }
        for (final Frame frame : ClickGui.frames) {
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseReleased(mouseX, mouseY, state);
                }
            }
        }
    }
    
    public boolean func_73868_f() {
        return true;
    }
    
    static {
        ClickGui.color = new Color(191, 16, 210, 150).getRGB();
    }
}
