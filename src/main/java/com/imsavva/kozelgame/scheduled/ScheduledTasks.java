package com.imsavva.kozelgame.scheduled;

import com.imsavva.kozelgame.container.GamesContainer;
import com.imsavva.kozelgame.service.GameStateAdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private GameStateAdjustmentService adjustmentService;
    @Autowired
    private GamesContainer gamesContainer;

    @Scheduled(fixedRate = 2000)
    public void fireState() {
        gamesContainer.getGames().forEach(game -> {
            game.getPlayers().forEach(player -> {
                var message = adjustmentService.convertToMessage(game, player);
                template.convertAndSendToUser(
                        player.getId(),
                        "/topic/games/" + game.getTableId() + "/state", message);
            });
        });
    }
}
