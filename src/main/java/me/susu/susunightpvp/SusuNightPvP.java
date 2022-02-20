package me.susu.susunightpvp;

import com.google.common.base.Stopwatch;
import me.susu.susunightpvp.listeners.EntityDamageByEntityListener;
import me.susu.susunightpvp.settings.ConfigManager;
import me.susu.susunightpvp.task.TimeChangeTask;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SusuNightPvP extends JavaPlugin {

    public static SusuNightPvP getPlugin() {
        return SusuNightPvP.getPlugin(SusuNightPvP.class);
    }

    public void onEnable() {
        Stopwatch loadTime = Stopwatch.createStarted();

        saveDefaultConfig();

        for (String mundo : getConfig().getStringList("pvp-worlds")) {
            if (Bukkit.getWorld(mundo) == null) {
                sendMessage("&e[SusuNightPvP] O mundo &f" + mundo + "&e não existe. Corrija na config.yml.");
                sendMessage("&e[SusuNightPvP] Desabilitando plugin.");
                getServer().getPluginManager().disablePlugin(this);
                return;
            }
        }

        ConfigManager.loadConfig();
        registerEvents();
        TimeChangeTask.timeChangeTask();
        checkTime();

        sendMessage("&f[SusuNightPvP] Este plugin foi feito por");
        sendMessage("&b[SusuNightPvP]   ________ __  ________ __ ");
        sendMessage("&b[SusuNightPvP]  /  ___/  |  \\/  ___/  |  \\");
        sendMessage("&b[SusuNightPvP]  \\___ \\|  |  /\\___ \\|  |  /");
        sendMessage("&b[SusuNightPvP] /____  >____//____  >____/ ");
        sendMessage("&b[SusuNightPvP]      \\/           \\/       ");
        sendMessage("&e[SusuNightPvP] Se você precisar de alguma ajuda discord na config.yml");
        loadTime.stop();
        sendMessage("&a[SusuNightPvP] Plugin inicializado com sucesso. (" + loadTime + ")");
    }

    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(message.replace('&', '§'));
    }

    public static boolean isCurrentlyDay(String worldName) {
        long time = Objects.requireNonNull(SusuNightPvP.getPlugin().getServer().getWorld(worldName)).getTime();

        return time > ConfigManager.horaDia && time < ConfigManager.horaNoite;
    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new EntityDamageByEntityListener(), this);
    }

    public void checkTime() {
        for (String worldName : getConfig().getStringList("pvp-worlds")) {
            if (isCurrentlyDay(worldName)) {
                Objects.requireNonNull(Bukkit.getWorld(worldName)).setDifficulty(Difficulty.valueOf(getConfig().getString("dia.dificuldade")));
            } else {
                Objects.requireNonNull(Bukkit.getWorld(worldName)).setDifficulty(Difficulty.valueOf(getConfig().getString("noite.dificuldade")));
            }
        }
    }
}