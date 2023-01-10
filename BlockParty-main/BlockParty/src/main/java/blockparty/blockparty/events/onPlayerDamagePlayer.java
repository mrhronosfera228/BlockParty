package blockparty.blockparty.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class onPlayerDamagePlayer implements Listener {
    public onPlayerDamagePlayer() {

    }
    @EventHandler
    public void onPlayerDamagePlayer(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }
}
