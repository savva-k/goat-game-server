package com.imsavva.kozelgame.service;

import com.imsavva.kozelgame.controller.beans.KozelGameStateMessage;
import com.imsavva.kozelgame.model.KozelGame;
import com.imsavva.kozelgame.model.beans.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameStateAdjustmentService {

    @Autowired
    private PlayerService playerService;

    public KozelGameStateMessage convertToMessage(KozelGame game, Player player) {
        var message = new KozelGameStateMessage();
        var neighbours = playerService.getNeighbourPlayers(game.getPlayers(), player);

        message.setTableId(game.getTableId());
        message.setCurrentUser(player);
        message.setCurrentScene(game.getCurrentScene());
        message.setFinished(game.isFinished());
        message.setStarted(game.isStarted());
        message.setLeftNeighbour(neighbours.getLeftNeighbour());
        message.setRightNeighbour(neighbours.getRightNeighbour());

        return message;
    }
}
