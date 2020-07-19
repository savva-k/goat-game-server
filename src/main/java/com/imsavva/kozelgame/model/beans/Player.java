package com.imsavva.kozelgame.model.beans;

import com.imsavva.kozelgame.model.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Player implements Cloneable {
    @NonNull
    private String id;
    @NonNull
    private String name;
    private String role;
    private Set<Card> hand;
    private boolean isCurrent;
    private boolean isDealer;
}
