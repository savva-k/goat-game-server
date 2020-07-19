package com.imsavva.kozelgame.service;

import com.imsavva.kozelgame.container.GamesContainer;
import com.imsavva.kozelgame.model.KozelGame;
import com.imsavva.kozelgame.model.beans.Player;
import com.imsavva.kozelgame.model.exceptions.TableNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class GamesContainerService {

    @Autowired
    private Supplier<KozelGame> kozelGameFactory;

    @Autowired
    private GamesContainer gamesContainer;

    public KozelGame addPlayer(String tableId, Player player) {
        var game = getGame(tableId);
        addPlayer(game, player);
        return game;
    }

    public KozelGame getGame(String tableId) {
        return this.gamesContainer.findGameByTableId(tableId).orElseThrow(TableNotFoundException::new);
    }

    public void addPlayer(KozelGame game, Player player) {
        game.addPlayer(player);
    }

    public KozelGame createGame(Player host) {
        var game = kozelGameFactory.get();
        this.addPlayer(game, host);
        this.gamesContainer.addGame(game);
        return game;
    }
}
