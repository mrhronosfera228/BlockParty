package blockparty.blockparty;

import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {
    private static MainClass instance;
    @Override
    public void onEnable() {
        instance = this;
        Game.copyWorld("oldworld1",  "bp");
        EventsLoader.load();
        PlotLoad.load();
    }
    @Override
    public void onDisable() {

    }

    public static MainClass getInstance() {
        return instance;
    }
}
