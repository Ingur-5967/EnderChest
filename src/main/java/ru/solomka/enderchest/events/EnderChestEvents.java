package ru.solomka.enderchest.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import ru.solomka.enderchest.core.InventoryHandler;

public class EnderChestEvents implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();

        if (!inventory.getTitle().equals("Инвентарь " + event.getPlayer().getName())) return;

        for (int i = 0; i < inventory.getSize(); i++)
            new InventoryHandler((Player) event.getPlayer()).save(inventory, i);

    }
}
