package com.imsavva.kozelgame.service;

import com.imsavva.kozelgame.controller.beans.AddPlayerRequest;
import com.imsavva.kozelgame.controller.beans.NeighbourPlayersResponse;
import com.imsavva.kozelgame.model.beans.Player;
import com.imsavva.kozelgame.model.exceptions.PlayerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();

    public Player createPlayerFromRequest(String id, AddPlayerRequest request) {
        var player = new Player(id, request.getPlayerName());
        players.add(player);
        return player;
    }

    public Optional<Player> getPlayerById(String id) {
        return players.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public NeighbourPlayersResponse getNeighbourPlayers(List<Player> players, String currentPlayerId) {
        var player = getPlayerById(currentPlayerId)
                .orElseThrow(() -> new PlayerNotFoundException("Id not found: " + currentPlayerId));

        return getNeighbourPlayers(players, player);
    }

    public NeighbourPlayersResponse getNeighbourPlayers(List<Player> playersInGame, Player currentPlayer) {
        var playerIds = playersInGame.stream().map(Player::getId).collect(Collectors.toList());
        var index = playerIds.indexOf(currentPlayer.getId());
        var response = new NeighbourPlayersResponse();
        var leftIndex = 0;
        var rightIndex = 0;

        switch (index) {
            case 0:
                leftIndex = 1;
                rightIndex = 2;
                break;
            case 1:
                leftIndex = 2;
                break;
            case 2:
                rightIndex = 1;
                break;
            default:
                throw new IllegalStateException("User not found!");
        }

        var leftNeighbour = getByIndexOrNull(leftIndex, playersInGame);
        var rightNeighbour = getByIndexOrNull(rightIndex, playersInGame);
        response.setLeftNeighbour(getCopyWithoutCards(leftNeighbour));
        response.setRightNeighbour(getCopyWithoutCards(rightNeighbour));
        return response;
    }

    public Player getCopyWithoutCards(Player player) {
        if (player == null) return null;
        var copy = new Player(player.getId(), player.getName());
        copy.setCurrent(player.isCurrent());
        copy.setRole(player.getRole());
        copy.setDealer(player.isDealer());
        copy.setCurrent(player.isCurrent());
        return copy;
    }

    private Player getByIndexOrNull(int index, List<Player> playersInGame) {
        return (index < playersInGame.size()) ? playersInGame.get(index) : null;
    }
}
