package com.imsavva.kozelgame.model;

public enum Scene {
    WAITING_FOR_PLAYERS("waitingForPlayers"),
    NEW_ROUND_START("newRoundStart");

    private String name;

    Scene(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
