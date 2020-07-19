package com.imsavva.kozelgame.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Card {

    @NonNull
    private Suite suite;

    @NonNull
    private Rank rank;

}
