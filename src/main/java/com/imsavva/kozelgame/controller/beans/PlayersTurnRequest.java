package com.imsavva.kozelgame.controller.beans;

import com.imsavva.kozelgame.model.Rank;
import com.imsavva.kozelgame.model.Suite;
import lombok.Data;

@Data
public class PlayersTurnRequest {
    private Suite suite;
    private Rank rank;
    private int x;
    private int y;
}
