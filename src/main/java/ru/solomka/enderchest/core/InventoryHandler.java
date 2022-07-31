package ru.solomka.enderchest.core;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.solomka.enderchest.config.Yaml;
import ru.solomka.enderchest.config.enums.DirectoryType;
import ru.solomka.enderchest.config.files.FileUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryHandler {

    private final Player p;
    private final Yaml file;

    public InventoryHandler(@NotNull Player p) {
        this.p = p;
        file = FileUtils.getDirectoryFile(DirectoryType.PLAYER.getType(), p.getUniqueId().toString());
    }

    public void openCustomInventory() {
        String[] defParams = {"Title", "Items"};
        Object[] defArgs = {"Инвентарь " + p.getName(), ""};

        Inventory inventory = Bukkit.createInventory(null, 9, "Инвентарь " + p.getName());

        if (!hasInventory())
            for (int i = 0; i < defParams.length; i++)
                file.set("Inventory." + defParams[i], defArgs[i]);

        p.openInventory(setItems(inventory));
    }

    public void save(@NotNull Inventory inventory, int slot) {
        ItemStack item = inventory.getItem(slot);
        if (item == null) {
            if (file.getString("Inventory.Items." + slot) != null)
                file.set("Inventory.Items." + slot, (Object) null);
            return;
        }

        String[] defParams = {"Material", "Name", "Lore"};

        Object[] defArgs = {
                String.join(";", Arrays.asList(item.getType().name(),
                        String.valueOf(item.getAmount()), String.valueOf(item.getDurability()))),
                (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) ? item.getItemMeta().getDisplayName() : "",
                (item.hasItemMeta() && item.getItemMeta().hasLore()) ? item.getItemMeta().getLore() : ""
        };

        for(int i = 0; i < defParams.length; i++)
            file.set("Inventory.Items." + slot + "." + defParams[i], defArgs[i]);
    }

    private Inventory setItems(@NotNull Inventory inventory) {
        for (Map.Entry<Integer, ItemStack> item : getItems(inventory.getSize()).get(p.getUniqueId()).entrySet())
            inventory.setItem(item.getKey(), item.getValue());

        return inventory;
    }

    private @NotNull Map<UUID, Map<Integer, ItemStack>> getItems(int sizeInventory) {
        Map<UUID, Map<Integer, ItemStack>> itemStacks = new HashMap<>();

        Map<Integer, ItemStack> items = new HashMap<>();

        for (int i = 0; i < sizeInventory; i++) {
            if (file.getString("Inventory.Items." + i) == null || file.getString("Inventory.Items." + i).equals(""))
                continue;

            String[] material = file.getString("Inventory.Items." + i + ".Material").split(";");

            ItemStack item = new ItemStack(Material.getMaterial(material[0]), Integer.parseInt(material[1]), Byte.parseByte(material[2]));

            items.put(i, new ItemBuilder(item).setName(file.getString("Inventory.Items." + i + ".Name"))
                    .setLore(file.getStringList("Inventory.Items." + i + ".Lore")).getReplacedItem());
        }
        itemStacks.put(p.getUniqueId(), items);
        return itemStacks;
    }

    private boolean hasInventory() {
        return file.getString("Inventory.Items") != null;
    }
}