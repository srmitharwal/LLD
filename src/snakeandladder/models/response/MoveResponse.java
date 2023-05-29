package snakeandladder.models.response;

import snakeandladder.models.Player;

public class MoveResponse {
    int newCell;

    Player player;

    Player nextPlayer;

    String msg;

    public int getNewCell() {
        return newCell;
    }

    public void setNewCell(int newCell) {
        this.newCell = newCell;
    }

    public Player getPlayerId() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
