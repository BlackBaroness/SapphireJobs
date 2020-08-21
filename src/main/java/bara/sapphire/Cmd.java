package bara.sapphire;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Cmd implements CommandExecutor {
    Boost boost = new Boost();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("Admin")) {
            sender.sendMessage(ChatColor.GOLD + "Коэффициент денег - " + boost.getCoeff());
        }else {
            try {
                double coeff;
                coeff = Double.parseDouble(args[0]);
                boost.upDate(coeff);
                sender.sendMessage(ChatColor.GOLD + "Коэффициент денег - " + boost.getCoeff());
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }
}
