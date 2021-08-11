package me.force.ware.autosave;

import net.minecraft.client.*;
import me.force.ware.*;
import me.force.ware.module.*;
import me.force.ware.settings.*;
import java.util.*;
import java.io.*;

public class SaveLoad
{
    private File dir;
    private File dataFile;
    
    public SaveLoad() {
        this.dir = new File(Minecraft.func_71410_x().field_71412_D, "ForceWare");
        if (!this.dir.exists()) {
            this.dir.mkdir();
        }
        this.dataFile = new File(this.dir, "cfg.txt");
        if (!this.dataFile.exists()) {
            try {
                this.dataFile.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.load();
    }
    
    public void save() {
        if (Main.instance.destructed) {
            return;
        }
        final ArrayList<String> toSave = new ArrayList<String>();
        for (final Module mod : Main.instance.moduleManager.modules) {
            toSave.add("MOD:" + mod.getName() + ":" + mod.isToggled() + ":" + mod.getKey());
        }
        for (final Setting set : Main.instance.settingsManager.getSettings()) {
            if (set.isCheck()) {
                toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValDouble());
            }
            if (set.isCombo()) {
                toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValString());
            }
            if (set.isSlider()) {
                toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValDouble());
            }
        }
        try {
            final PrintWriter pw = new PrintWriter(this.dataFile);
            for (final String str : toSave) {
                pw.println(str);
            }
            pw.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void load() {
        if (Main.instance.destructed) {
            return;
        }
        final ArrayList<String> lines = new ArrayList<String>();
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(this.dataFile));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                lines.add(line);
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (final String s : lines) {
            final String[] args = s.split(":");
            if (s.toLowerCase().startsWith("mod:")) {
                final Module m = Main.instance.moduleManager.getModule(args[1]);
                if (m == null) {
                    continue;
                }
                m.setToggled(Boolean.parseBoolean(args[2]));
                m.setKey(Integer.parseInt(args[3]));
            }
            else {
                if (!s.toLowerCase().startsWith("set:")) {
                    continue;
                }
                final Module m = Main.instance.moduleManager.getModule(args[2]);
                if (m == null) {
                    continue;
                }
                final Setting set = Main.instance.settingsManager.getSettingByName(m, args[1]);
                if (set == null) {
                    continue;
                }
                if (set.isCheck()) {
                    set.setValBoolean(Boolean.parseBoolean(args[3]));
                }
                if (set.isCombo()) {
                    set.setValString(args[3]);
                }
                if (!set.isSlider()) {
                    continue;
                }
                set.setValDouble(Double.parseDouble(args[3]));
            }
        }
    }
}
