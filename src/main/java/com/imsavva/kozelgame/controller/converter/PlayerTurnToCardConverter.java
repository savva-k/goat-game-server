package com.imsavva.kozelgame.controller.converter;

import com.imsavva.kozelgame.controller.beans.PlayersTurnRequest;
import com.imsavva.kozelgame.model.Card;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PlayerTurnToCardConverter implements Converter<PlayersTurnRequest, Card> {

    @Override
    public Card convert(PlayersTurnRequest request) {
        return new Card(request.getSuite(), request.getRank());
    }

}
