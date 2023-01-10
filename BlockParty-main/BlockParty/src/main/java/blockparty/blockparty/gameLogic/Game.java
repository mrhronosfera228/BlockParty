package blockparty.blockparty.gameLogic;

import blockparty.blockparty.MainClass;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static blockparty.blockparty.PlotLoad.plots;

public class Game implements Listener {
    public static boolean GameStart = false;
    private static Random random = new Random();
    public static boolean InLobby = true;
    private static int k;
    public static List<String> AlivePlayers = new ArrayList<>();
    //Реализация старта игры
    public static void GameStart() {
        InLobby = false;
        //Запускает таймер до начала игры
        counterSec("Начало игры через", 10, 200, 0);
        //Добавляет игроков в список.
        Bukkit.getScheduler().runTaskLater(MainClass.getInstance(), () -> {
            GameStart = true;
            for (Player player : Bukkit.getOnlinePlayers()) {
                AlivePlayers.add(player.getName());
            }
        }, 200);
        //Запуск основной логики игры
         k = Bukkit.getScheduler().runTaskTimer(MainClass.getInstance(), () -> {
             //Берём рандомный плот и устанавливаем его
            int numb = random.nextInt(3);
            setFloor(new Location(Bukkit.getWorld("bp"), 8, 12, 8), 15, numb);
             //5 секунд до появления цвета и получаем цвет
            counterSec("", 5, 100, 0);
            int color = random.nextInt(15);
            //Ждем 5 секунд и добавляем в инвентарь блок определённого цвета
            counterSec(returnMessage(color), 5, 200, 100);
            Bukkit.getScheduler().runTaskLater(MainClass.getInstance(), () -> {
                ItemStack item = new ItemStack(Material.STAINED_CLAY);
                item.setDurability((short) color);
                for (String player : AlivePlayers) {
                   Player p = Bukkit.getPlayer(player);
                   p.getInventory().addItem(item);
                }
            }, 100);
             //Удаляем все блоки кроме блока полученного цвета
            Bukkit.getScheduler().runTaskLater(MainClass.getInstance(), ()-> {
                setAir(new Location(Bukkit.getWorld("bp"), 8, 12, 8), 15, Material.AIR, color);
            }, 200);
            //Очищяем инвентарь
             Bukkit.getScheduler().runTaskLater(MainClass.getInstance(), ()-> {
                 for (String player : AlivePlayers) {
                     Player p = Bukkit.getPlayer(player);
                     p.getInventory().clear();
                 }
             }, 250);
        }, 210, 300).getTaskId();
    }

    //Реализация окончания игры
    public static void GameEnd() {
        Bukkit.getScheduler().cancelTask(k);
        setFloor(new Location(Bukkit.getWorld("bp"), 8, 12, 8), 15, 2);
        Player player = Bukkit.getPlayer(AlivePlayers.get(0));
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&6&lVICTORY"), "", 10, 20, 40);
        Bukkit.getScheduler().runTaskLater(MainClass.getInstance(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.kickPlayer(("Сервер перезагружается"));
            }
            World.deleteWorld("bp");
            Bukkit.getServer().reload();
        }, 200);

    }
    //Счётчик времени
    private static void counterSec(String message, int sec, int delay, int delay2) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            AtomicInteger fakeSeconds = new AtomicInteger(sec);
            BukkitTask tasker = Bukkit.getScheduler().runTaskTimer(MainClass.getInstance(), () -> {
                player.sendTitle(ChatColor.translateAlternateColorCodes('&', message), "" + fakeSeconds, 10, 10, 10);
                fakeSeconds.getAndDecrement();
            }, delay2, 20);
            Bukkit.getScheduler().runTaskLater(MainClass.getInstance(), tasker::cancel, delay);
        }
    }
    //Возвращает сообщение с цветом определённого блока
    private static String returnMessage(int color) {
        if (color == 0) return "&f&lБелый";
        if (color == 1) return "&6&lОранжевый";
        if (color == 2) return "&d&lПурпурный";
        if (color == 3) return "&b&lГолубой";
        if (color == 4) return "&e&lЖёлтый";
        if (color == 5) return "&a&lЛаймовый";
        if (color == 6) return "&c&lСветло Красный";
        if (color == 7) return "&8&lТемно Коричневый";
        if (color == 8) return "&7&lСветло Серый";
        if (color == 9) return "&3&lБирюзовый";
        if (color == 10) return "&5&lФиолетовый";
        if (color == 11) return "&1&lСиний";
        if (color == 12) return "&0&lКоричневый";
        if (color == 13) return "&2&lЗелёный";
        if (color == 14) return "&4&lКрасный";
        return null;
    }
    //Удаляет все блоки с плота, кроме определённого
    private static void setAir(Location center, int radius, Material material, int color) {
        for (int xMod = -radius; xMod <= radius; xMod++) {
            for (int zMod = -radius; zMod <= radius; zMod++) {
                Block theBlock = center.getBlock().getRelative(xMod, 0, zMod);
                if (theBlock.getData() != color) {
                    theBlock.setType(material);
                }
            }
        }
    }
    //Метод для установки блоков
    private static void setFloor(Location center, int radius, int numb) {
        int i = 0;
        List<Byte> plot = plots.get(numb);
        for (int xMod = -radius; xMod <= radius; xMod++) {
            for (int zMod = -radius; zMod <= radius; zMod++) {
                Block theBlock = center.getBlock().getRelative(xMod, 0, zMod);
                theBlock.setType(Material.STAINED_CLAY);
                theBlock.setData(plot.get(i++));
            }
        }
    }

}
