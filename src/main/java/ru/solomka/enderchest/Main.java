package ru.solomka.enderchest;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.solomka.enderchest.commands.EnderChestCommands;
import ru.solomka.enderchest.events.EnderChestEvents;

public final class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new EnderChestEvents(), this);
        Bukkit.getPluginCommand("ec").setExecutor(new EnderChestCommands());
    }

    @Override
    public void onDisable() {
    }
}
