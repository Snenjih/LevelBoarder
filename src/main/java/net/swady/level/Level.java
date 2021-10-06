package net.swady.level;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class Level extends JavaPlugin implements Listener {

    private static Level instance;


    private BukkitTask bukkitTask;
    private int size;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        World world = event.getPlayer().getWorld();
        WorldBorder worldBorder = event.getPlayer().getWorld().getWorldBorder();
        worldBorder.setSize(size);

    }


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Bukkit.getScheduler().runTaskLater(this.getInstance(), () -> {
            if (Bukkit.getServer().getOnlinePlayers().size() == 0) {
                bukkitTask.cancel();
            }
        }, 40L);
    }


    @EventHandler
    public void changeLevelEvent(PlayerLevelChangeEvent event) {
        Player player = event.getPlayer();
        WorldBorder worldBorder = event.getPlayer().getWorld().getWorldBorder();
        size++;
        worldBorder.setSize(size);

    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this,this);


        size = 1;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Level getInstance() {
        return instance;
    }
}
