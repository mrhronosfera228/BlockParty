package blockparty.blockparty;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class EventsLoader {
    //Загрузка событий
    private static PluginManager pm;
    public static void load() {
        pm = Bukkit.getServer().getPluginManager();
        register(new Game());
    }
    private static void register(Listener l) {
        pm.registerEvents(l, MainClass.getInstance());
    }
}
