package de.cfp.bordergames.util;

import de.cfp.bordergames.BorderGames;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public File file = new File("plugins/" + BorderGames.main.getName(), "config.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    // min, max

    public void register() {
        cfg.options().copyDefaults(true);
        cfg.addDefault("MinPlayers", BorderGames.main.min);
        cfg.addDefault("MaxPlayers", BorderGames.main.max);
        saveCfg();

        BorderGames.main.min = cfg.getInt("MinPlayers");
        BorderGames.main.max = cfg.getInt("MaxPlayers");
    }

    public void saveCfg() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
