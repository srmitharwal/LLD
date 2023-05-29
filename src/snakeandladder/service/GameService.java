package snakeandladder.service;

import snakeandladder.models.Player;
import snakeandladder.models.response.CreateGameRespose;
import snakeandladder.models.response.MoveResponse;

import java.util.List;

public interface GameService {

    public CreateGameRespose startGame(List<Player> players, int width, int height);

    public MoveResponse move(String gameId, Player player, int move);

    public List<Player> winnerList(String gameId);
}
