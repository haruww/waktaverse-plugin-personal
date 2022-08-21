package com.haru.waktaverse;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEventListener implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        //player.sendMessage(player.getName() + "님 안녕하세요");
        //e.getPlayer().setDisplayName("하루");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        e.setCancelled(false);
        Player p = e.getPlayer();
    }
}
