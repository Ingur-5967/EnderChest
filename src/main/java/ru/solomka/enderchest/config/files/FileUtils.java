package ru.solomka.enderchest.config.files;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.solomka.enderchest.Main;
import ru.solomka.enderchest.config.Yaml;
import ru.solomka.enderchest.config.enums.DirectoryType;

import java.io.File;

public class FileUtils {

    @Contract("_ -> new")
    public static @NotNull Yaml getDefaultCfg(String file) {
        return new Yaml(new File(Main.getInstance().getDataFolder(), file + ".yml"));
    }

    @Contract("_, _ -> new")
    public static @NotNull Yaml getDirectoryFile(String directory, String file) {
        return new Yaml(new File(Main.getInstance().getDataFolder() + "/" + directory + "/" + file + ".yml"));
    }

    public static Yaml getLangFile() {
        return getDirectoryFile(DirectoryType.LANG.getType(), getDefaultCfg("config").getString("Lang"));
    }
}
