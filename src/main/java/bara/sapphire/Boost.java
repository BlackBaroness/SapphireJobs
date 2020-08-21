package bara.sapphire;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Boost extends JavaPlugin{

    private File file;
    private Plugin core;

    private double coeff;

    public double getCoeff() {
        return this.coeff;
    }

    public void upDate(double boostCoeff) {
        file = new File(core.getDataFolder() + File.separator + "config.yaml");
        if (!file.exists()) core.saveDefaultConfig();

        core.getConfig().set("boostCoef", boostCoeff);
        core.saveConfig();
    }

    public void getConfigData() {
        file = new File(core.getDataFolder() + File.separator + "config.yaml");
        if (!file.exists()) core.saveDefaultConfig();

        coeff = core.getConfig().getDouble("boostCoef");
    }
}
