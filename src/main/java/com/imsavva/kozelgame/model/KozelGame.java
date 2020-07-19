package com.imsavva.kozelgame.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imsavva.kozelgame.model.beans.Player;
import com.imsavva.kozelgame.model.state.GameState;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
@JsonIgnoreProperties({ "state", "availableRoles" })
public class KozelGame {

    private GameState state;
    private Queue<String> availableRoles = new LinkedList<>(Arrays.asList("player1", "player2", "player3"));
    private List<Player> players = new ArrayList<>();
    private Player currentPlayer;

    // teams / teams generation after all talon actions done (in NewRoundState),
    // then change to a different state depending on the game mode

    private String tableId;
    private Scene currentScene;
    private boolean started;
    private boolean finished;

    public void addPlayer(Player player) {
        this.state.addPlayer(player);
    }

    public void takeTalon(Player player) {

    }

    public void skipTalon(Player player) {

    }

    public void layDownTalon(Player player, List<Card> cards) {

    }

    public void playCard(Player player, Card card) {

    }

}
