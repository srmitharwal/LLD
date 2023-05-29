package snakeandladder.models;

import snakeandladder.models.move.Jump;

public class Cell {
    private int cellNo;

    private Jump jump;

    public Cell(int cellNo) {
        this.cellNo = cellNo;
        this.jump = null;
    }

    public int getCellNo() {
        return cellNo;
    }

    public void setCellNo(int cellNo) {
        this.cellNo = cellNo;
    }

    public Jump getJump() {
        return jump;
    }

    public void setJump(Jump jump) {
        this.jump = jump;
    }
}
