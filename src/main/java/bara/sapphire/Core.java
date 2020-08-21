package bara.sapphire;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Core extends JavaPlugin {

    Plugin core;
    @Override
    public void onEnable() {
        Events events = new Events(initEco());
        Bukkit.getPluginManager().registerEvents(events, this);

        File file = new File(core.getDataFolder() + File.separator + "config.yaml");
        if (!file.exists()) core.saveDefaultConfig();

        getCommand("boost").setExecutor(new Cmd());
    }

    private Economy initEco() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        return rsp.getProvider();
    }
}
