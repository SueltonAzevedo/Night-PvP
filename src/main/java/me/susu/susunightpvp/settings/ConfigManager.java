package me.susu.susunightpvp.settings;

import me.susu.susunightpvp.SusuNightPvP;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

public class ConfigManager {

    static FileConfiguration config = SusuNightPvP.getPlugin().getConfig();

    public static int horaDia;
    public static int horaNoite;
    public static String pvpNaoPode;
    public static String titleDia;
    public static String subtitleDia;
    public static String titleNoite;
    public static String subtitleNoite;
    public static String dificuldadeDia;
    public static String dificuldadeNoite;
    public static String somDia;
    public static String somNoite;
    public static int volumeSomDia;
    public static int volumeSomNoite;

    public static void loadConfig() {
        horaDia = config.getInt("dia.hora") + 20;
        horaNoite = config.getInt("noite.hora") + 20;
        pvpNaoPode = Objects.requireNonNull(config.getString("action-bar")).replace('&', '§');
        titleDia = Objects.requireNonNull(config.getString("dia.title")).replace('&', '§');
        subtitleDia = Objects.requireNonNull(config.getString("dia.subtitle")).replace('&', '§');
        titleNoite = Objects.requireNonNull(config.getString("noite.title")).replace('&', '§');
        subtitleNoite = Objects.requireNonNull(config.getString("noite.subtitle")).replace('&', '§');
        dificuldadeDia = config.getString("dia.dificuldade");
        dificuldadeNoite = config.getString("noite.dificuldade");
        somDia = config.getString("dia.som");
        somNoite = config.getString("noite.som");
        volumeSomDia = config.getInt("dia.volume");
        volumeSomNoite = config.getInt("noite.volume");
    }
}