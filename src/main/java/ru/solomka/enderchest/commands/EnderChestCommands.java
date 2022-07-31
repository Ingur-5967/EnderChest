package ru.solomka.enderchest.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.solomka.enderchest.core.InventoryHandler;
import ru.solomka.enderchest.core.ItemBuilder;

import java.util.Arrays;

public class EnderChestCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

        if(!(sender instanceof Player)) return true;

        ((Player)sender).getInventory().setItem(0, new ItemBuilder(new ItemStack(Material.CHEST)).setName("&a123").getReplacedItem());

        new InventoryHandler((Player) sender).openCustomInventory();
        return true;
    }
}
