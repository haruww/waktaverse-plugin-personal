package com.haru.waktaverse;

import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Waktaverse extends JavaPlugin
{
    private static Waktaverse instance;
    Logger log = Bukkit.getLogger();

    public static Waktaverse getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable()
    {
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerEventListener(), this);
        getServer().getPluginManager().registerEvents(new ServerEventListener(), this);
        getServer().getPluginManager().registerEvents(new EntityEventListener(), this);

        if (getServer().getOnlineMode())
            log.info("Server is Online Mode");
        else log.info("Server is Offline Mode");

        log.info("Current version: " + getServer().getVersion());
        log.info("IP: " + getServer().getIp() + ":" + getServer().getPort());
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }
}