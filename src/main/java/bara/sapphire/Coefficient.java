package bara.sapphire;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Coefficient implements CommandExecutor {

    public Coefficient(JavaPlugin core) {
        // загружаем коэффициент
        this.core = core;
        loadCoeff();
    }

    private double coeff;
    private final JavaPlugin core;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("sapphire.admin") && (args.length == 1)) {
            // если игрок админ и ввёл аргумент
            double var;
            try {
                var = Double.parseDouble(args[0]);
                updateCoeff(var);
                sender.sendMessage(ChatColor.GREEN + "Вы успешно изменили коэффициент заработка.");
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Коэффициент должен иметь числовое значение.");
            }
        }
        // вывод
        sender.sendMessage(ChatColor.GREEN +
                "Коэффициент заработка: " +
                ChatColor.WHITE + coeff +
                ChatColor.GREEN + ".");
        return true;
    }

    private void updateCoeff(double var) {
        // запись нового коэффициента
        core.getConfig().set("boostCoef", var);
        core.saveConfig();

        coeff = var;
    }

    private void loadCoeff() {
        // проверка на наличие конфига
        File file = new File(core.getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) core.saveDefaultConfig();

        // загрузка
        coeff = core.getConfig().getDouble("boostCoef");
    }

    public double getCoeff() {
        // геттер для коеффа
        return coeff;
    }
}
