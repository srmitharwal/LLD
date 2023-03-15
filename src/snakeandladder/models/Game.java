package snakeandladder.models;

import java.util.*;

public class Game {
    private String id;

    private Queue<Player> playerList;

    private Map<Integer, Integer>playersPositionMap;

    private List<Player> winnerList;

    private Board board;

    private Player currentPlayer;


    public Game(){

    }

    public Game(String id, List<Player> players, Board board) {
        this.id = id;
        this.playerList = new LinkedList<>();
        this.playerList.addAll(players);
        this.board = board;
        playersPositionMap = new HashMap<>();
        winnerList = new ArrayList<>();
        this.currentPlayer = playerList.peek();
    }

    public List<Player> getWinnerList() {
        return new ArrayList<>(winnerList);
    }

    public void setWinnerList(List<Player> winnerList) {
        this.winnerList = winnerList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Queue<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(Queue<Player> playerList) {
        this.playerList = playerList;
    }

    public Map<Integer, Integer> getPlayersPositionMap() {
        return playersPositionMap;
    }

    public void setPlayersPositionMap(Map<Integer, Integer> playersPositionMap) {
        this.playersPositionMap = playersPositionMap;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
