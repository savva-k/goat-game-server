package com.imsavva.kozelgame.model.state;

import com.imsavva.kozelgame.model.GameConstants;
import com.imsavva.kozelgame.model.KozelGame;
import com.imsavva.kozelgame.model.Scene;
import com.imsavva.kozelgame.model.beans.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

public class WaitingForPlayersGameState extends AbstractGameState {

    @Autowired
    private Function<KozelGame, GameState> newRoundGameStateFactory;

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
            this.game.setState(newRoundGameStateFactory.apply(this.game));
        }
    }
}
