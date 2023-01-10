package blockparty.blockparty.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class onHunger implements Listener {
    public onHunger() {

    }
    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getEntity();
        player.setFoodLevel(20);
    }
}
