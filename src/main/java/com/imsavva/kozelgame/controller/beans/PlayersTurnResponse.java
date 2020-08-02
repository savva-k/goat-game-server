package com.imsavva.kozelgame.controller.beans;

import com.imsavva.kozelgame.model.Card;
import com.imsavva.kozelgame.model.beans.Player;
import lombok.Data;

@Data
public class PlayersTurnResponse {
    private Player player;
    private Card card;
    private int x;
    private int y;
}
