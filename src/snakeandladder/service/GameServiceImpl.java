package snakeandladder.service;

import snakeandladder.models.Board;
import snakeandladder.models.Cell;
import snakeandladder.models.Game;
import snakeandladder.models.Player;
import snakeandladder.models.move.Jump;
import snakeandladder.models.response.CreateGameRespose;
import snakeandladder.models.response.MoveResponse;

import java.util.*;

public class GameServiceImpl implements GameService {

    private static Map<String, Game> games = new HashMap<>();

    @Override
    public CreateGameRespose startGame(List<Player> players, int width, int height) {
        String gameId = UUID.randomUUID().toString();
        Board board = new Board(width, height);
        Game game = new Game(gameId, players, board);
        games.put(gameId, game);
        CreateGameRespose createGameRespose = new CreateGameRespose();
        createGameRespose.setGameId(gameId);
        createGameRespose.setMsg("game creation successful");
        createGameRespose.setFirstPlayer(game.getCurrentPlayer());
        return createGameRespose;
    }

    @Override
    public MoveResponse move(String gameId, Player player, int move) {
        Game game = games.get(gameId);
        MoveResponse moveResponse = new MoveResponse();
        if (player != game.getCurrentPlayer()) {
            moveResponse.setNextPlayer(setNextPlayer(game, game.getCurrentPlayer()));
            game.setCurrentPlayer(moveResponse.getNextPlayer());
            moveResponse.setMsg("Either the player is winner or this is not player turn");
            return moveResponse;
        }

        int playerPosition = game.getPlayersPositionMap().getOrDefault(player.getId(), 0);
        System.out.println("Before Move: player with player Id: " + player.getId()+ " position: "+  playerPosition);

        int newPlayerPosition = Math.min(100,playerPosition + move);
        Cell playerCell = game.getBoard().getCells().get(newPlayerPosition);
        Jump jump = playerCell.getJump();

        if (jump != null ) {
            if (jump.getEnd() > jump.getStart() ) {
                System.out.println("got a ladder at position " + newPlayerPosition);
            } else {
                System.out.println("got a snake at position" + newPlayerPosition);
            }
            newPlayerPosition = jump.getEnd();
        }

        if (checkIfWinner(game, newPlayerPosition, player)) {
            game.getPlayersPositionMap().put(player.getId(), 100);
            moveResponse.setNewCell(100);
            moveResponse.setNextPlayer(setNextPlayer(game, game.getCurrentPlayer()));
            game.setCurrentPlayer(moveResponse.getNextPlayer());
            System.out.println("After Move: player with player Id: " + player.getId()+ " position: "+  100);
            return moveResponse;
        }

        System.out.println("After Move: player with player Id: " + player.getId()+ " position: "+  newPlayerPosition);

        game.getPlayersPositionMap().put(player.getId(), newPlayerPosition);
        moveResponse.setPlayer(player);
        moveResponse.setNextPlayer(setNextPlayer(game, game.getCurrentPlayer()));
        moveResponse.setNewCell(newPlayerPosition);
        game.setCurrentPlayer(moveResponse.getNextPlayer());

        return moveResponse;
    }

    private boolean checkIfWinner(Game game, int newPlayerPosition, Player player) {
        if (newPlayerPosition >= 100) {
            System.out.println("player {} " + player.getId() + " won the game");
            game.getWinnerList().add(player);
            return true;
        }
        return false;
    }

    private Player setNextPlayer(Game game, Player currentPlayer) {

        Queue<Player> players = game.getPlayerList();
        players.poll();
        players.add(currentPlayer);
        Player nextPlayer = players.peek();
        while (nextPlayer != currentPlayer) {
            if (game.getPlayersPositionMap().getOrDefault(nextPlayer.getId(), 0) + 1 < game.getBoard().getCells().size()) {
                break;
            } else {
                players.poll();
                players.add(nextPlayer);
                nextPlayer  = players.peek();
            }
        }

       return nextPlayer;
    }


    @Override
    public List<Player> winnerList(String gameId) {

        return games.get(gameId).getWinnerList();
    }
}
