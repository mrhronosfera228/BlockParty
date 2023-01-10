package blockparty.blockparty.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static blockparty.blockparty.gameLogic.Game.AlivePlayers;
import static blockparty.blockparty.gameLogic.Game.GameEnd;

public class onGetFallDamage implements Listener {
    public onGetFallDamage() {

    }
    @EventHandler
    public void onGetFallDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
            Player player = (Player) event.getEntity();
            player.setGameMode(GameMode.SPECTATOR);
            AlivePlayers.remove(player.getName());
            if (AlivePlayers.size() <= 1) {
                GameEnd();
            }
        }
    }
}
