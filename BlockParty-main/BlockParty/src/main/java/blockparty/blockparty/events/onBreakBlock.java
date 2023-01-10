package blockparty.blockparty.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class onBreakBlock implements Listener {
    public onBreakBlock() {

    }
    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        event.setCancelled(true);
    }
}
