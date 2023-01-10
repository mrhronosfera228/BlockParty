package blockparty.blockparty.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static blockparty.blockparty.gameLogic.Game.GameStart;
import static blockparty.blockparty.gameLogic.Game.InLobby;

public class onPlayerJoin implements Listener {
    private Location location= new Location(Bukkit.getWorld("bp"), 9, 13, 8, 270, 0);
    public onPlayerJoin(){

    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.teleport(location);
        if (InLobby) {
            player.setGameMode(GameMode.SURVIVAL);
            if (Bukkit.getOnlinePlayers().size() > 1) {
                GameStart();
            }
        } else {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }
}
