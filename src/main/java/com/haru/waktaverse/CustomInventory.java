package com.haru.waktaverse;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CustomInventory implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {/*
        Player p = (Player) sender;
        Inventory inv = null;
        //args 1은 이름 args 2는 체력
        if (p.isOp())
        {
            if(args[0].equals("inv") && args.length==1)
            {
                p.sendMessage("[Waktaverse] 사용법 : /inv [이름] [높이수] [x] [y] [z]");
            }
            else if(args[0].equals("inv") && args.length==6)
            {
                inv = Bukkit.createInventory(null, Integer.parseInt(args[2]), args[1]);
            }
            else if(args[0].equals("inv") && args[1].equals("open") && args.length==2)
            {
                p.openInventory(inv);
            }
        }*/
        return true;
    }
}
