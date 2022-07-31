package ru.solomka.enderchest.config.enums;

import lombok.Getter;

public enum DirectoryType {

    PLAYER("playerdata"),
    MENU("menu"),
    DATA("data"),
    LANG("lang"),
    NONE("");

    @Getter
    private final String type;

    DirectoryType(String type) {
        this.type = type;
    }

}

