package com.imsavva.kozelgame.config;

import com.imsavva.kozelgame.model.Card;
import com.imsavva.kozelgame.model.KozelGame;
import com.imsavva.kozelgame.model.Rank;
import com.imsavva.kozelgame.model.Suite;
import com.imsavva.kozelgame.model.state.GameState;
import com.imsavva.kozelgame.model.state.NewRoundState;
import com.imsavva.kozelgame.model.state.WaitingForPlayersGameState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class GameConfig {

    @Bean
    public Supplier<KozelGame> kozelGameFactory() {
        return this::kozelGame;
    }

    @Bean
    Supplier<List<Card>> deckFactory() {
        return this::deck;
    }

    @Bean
    public Function<KozelGame, GameState> newRoundGameStateFactory() {
        return this::newRoundState;
    }

    @Bean
    Function<KozelGame, GameState> waitingForPlayersGameStateFactory() {
        return this::waitingForPlayersGameState;
    }

    @Bean
    @Scope(value = "prototype")
    public KozelGame kozelGame() {
        var game = new KozelGame();
        game.setState(waitingForPlayersGameState(game));
        return game;
    }

    @Bean
    @Scope(value = "prototype")
    public GameState waitingForPlayersGameState(KozelGame game) {
        return new WaitingForPlayersGameState(game);
    }

    @Bean
    @Scope(value = "prototype")
    public GameState newRoundState(KozelGame game) {
        return new NewRoundState(game, deckFactory());
    }

    @Bean
    @Scope(value = "prototype")
    public List<Card> deck() {
        var deck = new ArrayList<Card>();

        for (var suite : Suite.values()) {
            for (var rank : Rank.values()) {
                deck.add(new Card(suite, rank));
            }
        }

        Collections.shuffle(deck);
        return deck;
    }
}
