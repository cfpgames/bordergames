package de.cfp.bordergames;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        if(arg0.hasPermission("bordergames.start")) {
            Countdown.lobby = 5;
            arg0.sendMessage("Woosh!");
        } else {
            arg0.sendMessage("Nothing to see here.");
        }
        return false;
    }

}
