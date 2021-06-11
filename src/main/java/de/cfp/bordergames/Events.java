package de.cfp.bordergames;

import de.cfp.bordergames.util.GameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(Bukkit.getOnlinePlayers().size() == BorderGames.main.min) {
            BorderGames.main.cd.startLobbyCD();
        }
        Location loc = Bukkit.getWorld("world").getSpawnLocation();
        loc.setX(0);
        loc.setZ(0);
        e.getPlayer().teleport(loc);
        e.getPlayer().getInventory().clear();
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        e.getPlayer().setHealth(20D);
        e.getPlayer().getActivePotionEffects().clear();
        e.getPlayer().setFoodLevel(20);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if(BorderGames.main.state != GameState.LOBBY) {
            e.disallow(PlayerLoginEvent.Result.KICK_FULL, "Das Spiel läuft schon!");
            return;
        }
        if(BorderGames.main.alive.size() >= BorderGames.main.max) {
            e.disallow(PlayerLoginEvent.Result.KICK_FULL, "Das Spiel ist voll!");
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(BorderGames.main.state == GameState.INGAME) {

        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPVP(EntityDamageByEntityEvent e) {
        if(BorderGames.main.state == GameState.INGAME) {

        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDisc(PlayerQuitEvent e) {
        if(Bukkit.getOnlinePlayers().size() == 0) {
            BorderGames.main.cd.startRestartCD();
        }
    }

}
