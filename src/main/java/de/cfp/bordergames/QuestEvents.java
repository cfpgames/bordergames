package de.cfp.bordergames;

import de.cfp.bordergames.util.Quest;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class QuestEvents implements Listener {

    public void done(Player p) {
        p.teleport(new Location(Bukkit.getWorld("world"), 0, 85, 0));
        p.setGameMode(GameMode.SPECTATOR);
        BorderGames.main.done.add(p);
        BorderGames.main.check();

//		ByteArrayOutputStream stream = new ByteArrayOutputStream();
//		DataOutputStream in = new DataOutputStream(stream);
//
//		int rnd = getRandomNumber(1, 15);
//		try {
//			in.writeUTF(rnd + "");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		p.sendPluginMessage(BorderGames.main, "GadgetsMenu", stream.toByteArray());
//		p.sendMessage("§3+"+rnd+" Mystery Dust!");
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @EventHandler
    public void changeWorld(PlayerPickupItemEvent e) {
        System.out.println("p" + e.getItem().getItemStack().getType() + BorderGames.main.q);
        if((e.getItem().getItemStack().getType().equals(Material.LEGACY_SUGAR) || e.getItem().getItemStack().getType().equals(Material.SUGAR)) && BorderGames.main.q.equals(Quest.GETSUGAR)) {
            done(e.getPlayer());
        }
        if((e.getItem().getItemStack().getType().equals(Material.LEGACY_LAVA_BUCKET) || e.getItem().getItemStack().getType().equals(Material.LAVA_BUCKET)) && BorderGames.main.q.equals(Quest.GETLAVABUCKET)) {
            done(e.getPlayer());
        }
        if((e.getItem().getItemStack().getType().equals(Material.LEGACY_IRON_INGOT) || e.getItem().getItemStack().getType().equals(Material.IRON_INGOT)) && BorderGames.main.q.equals(Quest.GETIRON)) {
            done(e.getPlayer());
        }
    }

}
