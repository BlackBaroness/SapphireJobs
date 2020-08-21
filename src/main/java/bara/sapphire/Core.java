package bara.sapphire;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        // загружаем сразу коефф и регистрируем его, как команду
        Coefficient coefficient = new Coefficient(this);
        getCommand("boost").setExecutor(coefficient);

        // регистрируем ивенты и передаём в них coefficient
        Events events = new Events(initEco(), coefficient);
        Bukkit.getPluginManager().registerEvents(events, this);
    }

    private Economy initEco() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        return rsp.getProvider();
    }
}
