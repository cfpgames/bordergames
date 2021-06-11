package de.cfp.bordergames;

import de.cfp.bordergames.util.BorderManager;
import de.cfp.bordergames.util.GameState;
import de.cfp.bordergames.util.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Countdown {

    public boolean lobbystarted = false;
    public boolean restart = false;

    int lobbycd;
    int gamecd;
    int restartcd;

    public static int lobby = BorderGames.main.lobby;
    int ingame = BorderGames.main.ingame;

    public void startLobbyCD() {
        if(lobbystarted == false) {
            lobbycd = Bukkit.getScheduler().scheduleSyncRepeatingTask(BorderGames.main, new Runnable() {

                @Override
                public void run() {
                    lobbystarted = true;
                    if(lobby >= 1) {
                        if(lobby == 60 || lobby == 30 || lobby == 15 || (lobby <= 5 && lobby >= 1)) {
                            Bukkit.broadcastMessage(BorderGames.main.PREFIX + "Das Spiel startet in " + lobby + " Sekunden!");
                        }
                    } else if(lobby == 0) {
                        Bukkit.getWorld("world").setTime(0L);
                        BorderManager.setSize(500, 15);

                        Bukkit.getScheduler().cancelTask(lobbycd);

                        Bukkit.getScheduler().scheduleSyncDelayedTask(BorderGames.main, new Runnable() {

                            @Override
                            public void run() {
                                BorderManager.setSize(1, 60*20);
                                Bukkit.broadcastMessage(BorderGames.main.PREFIX + "Die WorldBorder wird jetzt kleiner. Beeile dich um dein Ziel zu erreichen:");
                                if(BorderGames.main.q == Quest.GETSUGAR) Bukkit.broadcastMessage(BorderGames.main.PREFIX + "GET SUGAR");
                                if(BorderGames.main.q == Quest.GETLAVABUCKET) Bukkit.broadcastMessage(BorderGames.main.PREFIX + "GET LAVA BUCKET");
                                if(BorderGames.main.q == Quest.GETIRON) Bukkit.broadcastMessage(BorderGames.main.PREFIX + "GET IRON");

                            }
                        }, 20*15L);

                        startGameCD();
                    }
                    lobby--;
                }
            }, 0, 20L);
        }
    }

    public void startRestartCD() {
        BorderGames.main.state = GameState.RESTARTING;
        if(restart == false) {
            restartcd = Bukkit.getScheduler().scheduleSyncRepeatingTask(BorderGames.main, new Runnable() {

                @Override
                public void run() {
                    restart = true;
                    if(BorderGames.main.restart >= 1) {
                        if(BorderGames.main.restart == 60 || BorderGames.main.restart == 30 || BorderGames.main.restart == 15 || (BorderGames.main.restart <= 5 && BorderGames.main.restart >= 1)) {
                            Bukkit.broadcastMessage(BorderGames.main.PREFIX + "Das Spiel restartet in " + BorderGames.main.restart + " Sekunden!");
                        }
                    } else if(BorderGames.main.restart == 0) {
                        Bukkit.getScheduler().cancelTask(restartcd);

                        for(Player p : Bukkit.getOnlinePlayers()) {
                            ByteArrayOutputStream b = new ByteArrayOutputStream();
                            DataOutputStream out = new DataOutputStream(b);

                            try {
                                out.writeUTF("Connect");
                                out.writeUTF("lobby");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            p.sendPluginMessage(BorderGames.main, "BungeeCord", b.toByteArray());
                        }

                        Bukkit.getScheduler().scheduleSyncDelayedTask(BorderGames.main, new Runnable() {

                            @Override
                            public void run() {
                                Bukkit.shutdown();
                            }
                        }, 20*3L);
                    }
                    BorderGames.main.restart--;
                }
            }, 0, 20L);
        }
    }

    public void startGameCD() {
        BorderGames.main.state = GameState.INGAME;
        gamecd = Bukkit.getScheduler().scheduleSyncRepeatingTask(BorderGames.main, new Runnable() {

            @Override
            public void run() {

                if(ingame == 0) {
                    startRestartCD();
                }

                ingame--;

            }
        }, 0, 20L);

    }

}
