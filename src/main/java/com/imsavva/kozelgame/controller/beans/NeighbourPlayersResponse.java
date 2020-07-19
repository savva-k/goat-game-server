package com.imsavva.kozelgame.controller.beans;

import com.imsavva.kozelgame.model.beans.Player;
import lombok.Data;

@Data
public class NeighbourPlayersResponse {
    private Player leftNeighbour;
    private Player rightNeighbour;
}
