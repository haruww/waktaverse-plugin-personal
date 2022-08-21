package com.haru.waktaverse;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerEventListener implements Listener
{
    @EventHandler
    public void onServerList(ServerListPingEvent e)
    {
        e.setMaxPlayers(20);
        e.setMotd("test MOTD");
        Bukkit.getConsoleSender().sendMessage(e.getAddress().getHostAddress());
    }
}
