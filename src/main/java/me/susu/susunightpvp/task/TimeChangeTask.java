package me.susu.susunightpvp.task;

import me.susu.susunightpvp.SusuNightPvP;
import me.susu.susunightpvp.settings.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeChangeTask {

    public static void timeChangeTask() {
        new BukkitRunnable() {
            public void run() {
                for (String worldName : SusuNightPvP.getPlugin().getConfig().getStringList("pvp-worlds")) {
                    World w = Bukkit.getWorld(worldName);
                    assert w != null;
                    if (w.getTime() == ConfigManager.horaDia) {
                        w.setDifficulty(Difficulty.valueOf(SusuNightPvP.getPlugin().getConfig().getString("dia.dificuldade")));
                    } else if (w.getTime() == ConfigManager.horaNoite) {
                        w.setDifficulty(Difficulty.valueOf(SusuNightPvP.getPlugin().getConfig().getString("noite.dificuldade")));
                    }
                    for (Player player : SusuNightPvP.getPlugin().getServer().getOnlinePlayers()) {
                        if (w.getTime() == ConfigManager.horaDia) {
                            if (player.getWorld().getName().equals(worldName)) {
                                player.playSound(player.getLocation(), Sound.valueOf(ConfigManager.somDia.toUpperCase()), ConfigManager.volumeSomDia, 1);
                                player.sendTitle(ConfigManager.titleDia, ConfigManager.subtitleDia, 20, 60, 20);
                            }
                        } else if (w.getTime() == ConfigManager.horaNoite) {
                            if (player.getWorld().getName().equals(worldName)) {
                                player.playSound(player.getLocation(), Sound.valueOf(ConfigManager.somNoite.toUpperCase()), ConfigManager.volumeSomNoite, 1);
                                player.sendTitle(ConfigManager.titleNoite, ConfigManager.subtitleNoite, 20, 60, 20);
                            }
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(SusuNightPvP.getPlugin(), 0, 0);
    }
}