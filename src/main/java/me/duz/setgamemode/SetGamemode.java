package me.duz.setgamemode;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class GamemodeOnJoin extends JavaPlugin implements Listener {

    GameMode gamemode;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        String gm = getConfig().getString("gamemode");
        switch (gm) {
            case "survival":
                gamemode = GameMode.SURVIVAL;
                break;
            case "creative":
                gamemode = GameMode.CREATIVE;
                break;
            case "spectator":
            case "spec":
                gamemode = GameMode.SPECTATOR;
                break;
            case "adventure":
                gamemode = GameMode.ADVENTURE;
                break;
            default:
                System.out.println("[ERROR] No gamemode defined/unrecognised gamemode in config file. Please define one or check your spelling if you have.");
                break;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (gamemode == null) {
            return;
        }
        e.getPlayer().setGameMode(gamemode);
    }
}
