package bara.sapphire;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UUIDManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("sapphire.admin")) {
            if (args.length <= 1) {
                sender.sendMessage(ChatColor.RED + "Введите ник игрока");
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "Игрок не зарегистрирован или не онлайн");
                } else {
                    sender.sendMessage(ChatColor.GREEN + target.getUniqueId().toString() + " - " + target.getName());
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "У вас недостаточно прав для выполнения данной команды");
        }
        return true;
    }
}
