package com.imsavva.kozelgame.container;

import com.imsavva.kozelgame.model.GameConstants;
import com.imsavva.kozelgame.model.KozelGame;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class GamesContainer {

    private Map<String, KozelGame> games = new HashMap<>();

    public void addGame(KozelGame game) {
        var tableId = generateTableId();
        game.setTableId(tableId);
        this.games.put(tableId, game);
    }

    public Optional<KozelGame> findGameByTableId(String tableId) {
        return games.values().stream()
                .filter(game -> tableId.equals(game.getTableId()))
                .findFirst();
    }

    public Collection<KozelGame> getGames() {
        return games.values();
    }

    private String generateTableId() {
        var tableId = RandomStringUtils.randomAlphabetic(GameConstants.TABLE_ID_LENGTH).toUpperCase();

        while (games.containsKey(tableId)) {
            tableId = RandomStringUtils.randomAlphabetic(GameConstants.TABLE_ID_LENGTH).toUpperCase();
        }

        return tableId;
    }
}
