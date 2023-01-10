package blockparty.blockparty;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlotLoad {
    public static Map<Integer, List<Byte>> plots = new HashMap<>();
    //Загрузка плотов
    public static void load() {
        getFloor(new Location(Bukkit.getWorld("bp"), -49.5, 10, 10.5), 15, 0);
        getFloor(new Location(Bukkit.getWorld("bp"), -49.5, 8, 10.5), 15, 1);
        getFloor(new Location(Bukkit.getWorld("bp"), -49.5, 6, 10.5), 15, 2);
    }
    //Копирует блоки с плота
    private static void getFloor(Location center, int radius, int numb) {
        List<Byte> plot = new ArrayList<>();
        for (int xMod = -radius; xMod <= radius; xMod++) {
            for (int zMod = -radius; zMod <= radius; zMod++) {
                Block theBlock = center.getBlock().getRelative(xMod, 0, zMod);
                plot.add(theBlock.getData());
            }
        }
        plots.put(numb, plot);
    }
}
