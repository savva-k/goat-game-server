package com.imsavva.kozelgame.model.state;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imsavva.kozelgame.model.KozelGame;
import lombok.Data;

@Data
@JsonIgnoreProperties("game")
public abstract class AbstractGameState implements GameState {

    protected KozelGame game;

    public AbstractGameState(KozelGame game) {
        this.game = game;
    }

}
