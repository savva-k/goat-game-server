package com.imsavva.kozelgame.model.state;

import com.imsavva.kozelgame.model.Card;
import com.imsavva.kozelgame.model.GameConstants;
import com.imsavva.kozelgame.model.KozelGame;
import com.imsavva.kozelgame.model.Scene;
import com.imsavva.kozelgame.model.beans.Player;
import com.imsavva.kozelgame.model.exceptions.InvalidActionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.BiFunction;
import java.util.function.Function;

public class WaitingForPlayersGameState extends AbstractGameState {

    @Autowired
    private Function<KozelGame, GameState> newRoundGameStateFactory;

    @Autowired
    private BiFunction<Integer, Integer, Integer> randomIntGenerator;

    public WaitingForPlayersGameState(KozelGame game) {
        super(game);
        this.game.setCurrentScene(Scene.WAITING_FOR_PLAYERS);
    }

    @Override
    public void addPlayer(Player player) {
        var players = this.game.getPlayers();
        player.setRole(game.getAvailableRoles().poll());
        players.add(player);

        if (players.size() == GameConstants.NUMBER_OF_PLAYERS) {
            var dealer = players.get(randomIntGenerator.apply(0, players.size()));
            dealer.setCurrent(true);
            dealer.setDealer(true);
            this.game.setState(newRoundGameStateFactory.apply(this.game));
        }
    }

    @Override
    public void handlePlayersTurn(Player player, Card card) {
        throw new InvalidActionException();
    }
}
