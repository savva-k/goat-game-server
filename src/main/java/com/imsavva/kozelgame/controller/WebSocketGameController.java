package com.imsavva.kozelgame.controller;

import com.imsavva.kozelgame.controller.beans.AddPlayerRequest;
import com.imsavva.kozelgame.controller.beans.KozelGameStateMessage;
import com.imsavva.kozelgame.controller.beans.PlayersTurnRequest;
import com.imsavva.kozelgame.controller.beans.PlayersTurnResponse;
import com.imsavva.kozelgame.controller.converter.PlayerTurnToCardConverter;
import com.imsavva.kozelgame.model.exceptions.PlayerNotFoundException;
import com.imsavva.kozelgame.service.GameStateAdjustmentService;
import com.imsavva.kozelgame.service.GamesContainerService;
import com.imsavva.kozelgame.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class WebSocketGameController {

    @Autowired
    private GamesContainerService gamesContainerService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameStateAdjustmentService adjustmentService;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private PlayerTurnToCardConverter playerTurnToCardConverter;

    @MessageMapping("/game/create")
    @SendToUser("/topic/game/create/success")
    public KozelGameStateMessage createGame(@RequestBody AddPlayerRequest request, Principal principal) {
        var player = this.playerService.createPlayerFromRequest(principal.getName(), request);
        var game = this.gamesContainerService.createGame(player);
        return adjustmentService.convertToMessage(game, player);
    }

    @MessageMapping("/game/{tableId}/players/add")
    public void addPlayer(@DestinationVariable String tableId, @RequestBody AddPlayerRequest request, Principal principal) {
        var currentPlayer = this.playerService.createPlayerFromRequest(principal.getName(), request);
        var game = this.gamesContainerService.addPlayer(tableId, currentPlayer);

        game.getPlayers().forEach(player -> {
            var message = adjustmentService.convertToMessage(game, player);
            template.convertAndSendToUser(player.getId(), "/topic/games/" + tableId + "/player_added", message);
        });
    }

    @MessageMapping("/game/{tableId}/turn")
    public void handlePlayersTurn(
            @DestinationVariable String tableId,
            @RequestBody PlayersTurnRequest request,
            Principal principal) {
        var currentPlayer = this.playerService.getPlayerById(principal.getName())
                .orElseThrow(PlayerNotFoundException::new);
        var game = this.gamesContainerService.getGame(tableId); // TODO orElseThrow
        var card = this.playerTurnToCardConverter.convert(request);
        // todo validate the player
        game.handlePlayersTurn(currentPlayer, card);

        var response = new PlayersTurnResponse();
        response.setPlayer(playerService.getCopyWithoutCards(currentPlayer));
        response.setCard(card);
        response.setX(request.getX());
        response.setY(request.getY());

        game.getPlayers().forEach(player -> {
            var destination = "/topic/games/" + tableId + "/player_made_turn";
            template.convertAndSendToUser(
                    player.getId(),
                    destination,
                    response
            );
        });
    }
}
