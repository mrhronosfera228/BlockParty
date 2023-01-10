package blockparty.blockparty;

import blockparty.blockparty.events.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class EventsLoader {
    //Загрузка событий
    private static PluginManager pm;
    public static void load() {
        pm = Bukkit.getServer().getPluginManager();
        register(new onBreakBlock());
        register(new onGetFallDamage());
        register(new onHunger());
        register(new onPlaceBlock());
        register(new onPlayerDamagePlayer());
        register(new onPlayerJoin());
        register(new onPlayerLeave());
    }
    private static void register(Listener l) {
        pm.registerEvents(l, MainClass.getInstance());
    }
}
