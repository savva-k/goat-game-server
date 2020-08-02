package com.imsavva.kozelgame.model.state;

import com.imsavva.kozelgame.model.Card;
import com.imsavva.kozelgame.model.beans.Player;

public interface GameState {

    void addPlayer(Player player);

    void handlePlayersTurn(Player player, Card card);
}
