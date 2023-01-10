package blockparty.blockparty.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static blockparty.blockparty.gameLogic.Game.*;

public class onPlayerLeave implements Listener {
    public onPlayerLeave() {

    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.SPECTATOR && GameStart) {
            AlivePlayers.remove(player.getName());
            if (AlivePlayers.size() <= 1) {
                GameEnd();
            }
        }
    }
}
