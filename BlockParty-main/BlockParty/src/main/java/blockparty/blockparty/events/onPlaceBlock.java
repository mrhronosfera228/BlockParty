package blockparty.blockparty.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class onPlaceBlock implements Listener {
    public  onPlaceBlock() {

    }
    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {
        event.setCancelled(true);
    }
}
