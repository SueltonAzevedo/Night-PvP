package me.susu.susunightpvp.events;

import me.susu.susunightpvp.SusuNightPvP;
import me.susu.susunightpvp.settings.ConfigManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) {
            return;
        }

        Player p = (Player) e.getDamager();

        for (String worldName : SusuNightPvP.getPlugin().getConfig().getStringList("pvp-worlds")) {
            if (p.getWorld().getName().equals(worldName) && SusuNightPvP.isDeDia(p.getWorld().getName())) {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ConfigManager.pvpNaoPode));
                e.setCancelled(true);
            }
        }
    }
}