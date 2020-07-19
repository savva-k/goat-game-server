package com.imsavva.kozelgame.model.state;

import com.imsavva.kozelgame.model.Card;
import com.imsavva.kozelgame.model.KozelGame;
import com.imsavva.kozelgame.model.Scene;
import com.imsavva.kozelgame.model.beans.Player;
import com.imsavva.kozelgame.model.exceptions.InvalidActionException;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static com.imsavva.kozelgame.model.GameConstants.CARDS_PER_PLAYER;

public class NewRoundState extends AbstractGameState {

    private Supplier<List<Card>> deckFactory;

    public NewRoundState(KozelGame game, Supplier<List<Card>> deckFactory) {
        super(game);
        this.deckFactory = deckFactory;
        this.game.setCurrentScene(Scene.NEW_ROUND_START);
        giveCards();
    }

    @Override
    public void addPlayer(Player player) {
        throw new InvalidActionException("Cannot add a player: the game is already in progress!");
    }

    private void giveCards() {
        var players = this.game.getPlayers();
        var deck = deckFactory.get();

        for (var i = 0; i < players.size(); i++) {
            var startPosition = i * CARDS_PER_PLAYER;
            var hand = deck.subList(startPosition, startPosition + CARDS_PER_PLAYER);
            players.get(i).setHand(Set.of(hand.toArray(new Card[]{})));
        }
    }
}
