package de.cfp.bordergames.util;

import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;

public class BorderManager {

    private static WorldBorder border;

    public static void init(String world) {

        border = Bukkit.getWorld(world).getWorldBorder();
        border.setDamageAmount(90);

    }

    public static void setCenter(double x, double z) {
        border.setCenter(x, z);
    }

    public static void setCenter() {
        border.setCenter(0, 0);
    }

    public static void setSize(double size, long time) {
        border.setSize(size, time);
    }

}
