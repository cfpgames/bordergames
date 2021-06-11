package de.cfp.bordergames;

import de.cfp.bordergames.util.BorderManager;
import de.cfp.bordergames.util.FileManager;
import de.cfp.bordergames.util.GameState;
import de.cfp.bordergames.util.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class BorderGames extends JavaPlugin {

    GameState state;
    public static BorderGames main;
    public ArrayList<Player> alive;
    public ArrayList<Player> done;
    public final String PREFIX = "§7Spiel §8| §7BorderGames§8: §r";
    public FileManager fm;
    public Countdown cd;
    public Quest q;

    public int min = 2;
    public int max = 8;

    public int lobby = 60;
    public int ingame = 60*20;
    public int restart = 15;

    public void check() {
        if(Bukkit.getOnlinePlayers().size() <= done.size()) {
            cd.startRestartCD();
        }
    }

    @Override
    public void onEnable() {
        main = this;
        state = GameState.LOBBY;
        alive = new ArrayList<Player>();
        done = new ArrayList<Player>();
        fm = new FileManager();
        fm.saveCfg();
        fm.register();
        cd = new Countdown();
        q = Quest.randomQuest();

        BorderManager.init("world");
        BorderManager.setCenter(0, 0);
        BorderManager.setSize(5, 0);
        Bukkit.getWorld("world").setTime(0L);

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new QuestEvents(), this);
        getCommand("start").setExecutor(new StartCMD());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
