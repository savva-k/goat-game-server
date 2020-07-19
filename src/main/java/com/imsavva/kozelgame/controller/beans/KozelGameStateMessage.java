package com.imsavva.kozelgame.controller.beans;

import com.imsavva.kozelgame.model.Scene;
import com.imsavva.kozelgame.model.beans.Player;
import com.imsavva.kozelgame.model.state.GameState;
import lombok.Data;

@Data
public class KozelGameStateMessage {
    private GameState state;
    private Player leftNeighbour;
    private Player rightNeighbour;
    private Player currentUser;

    // teams / teams generation after all talon actions done (in NewRoundState),
    // then change to a different state depending on the game mode

    private String tableId;
    private Scene currentScene;
    private boolean started;
    private boolean finished;
}
