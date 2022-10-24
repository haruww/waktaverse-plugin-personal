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
        e.setMaxPlayers(99999999);
        Bukkit.getConsoleSender().sendMessage(e.getAddress().getHostAddress());
    }
}
