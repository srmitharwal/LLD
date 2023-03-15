package snakeandladder.models;

import snakeandladder.models.move.Jump;
import snakeandladder.models.move.Ladder;
import snakeandladder.models.move.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private List<Cell> cells;

    private int width;

    private int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        initCells();
        addSnakesAndLadder();
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCell(List<Cell> cell) {
        this.cells = cells;
    }

    public int getSize() {
        return width * height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private void initCells() {
        int size = getSize();
        cells = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            cells.add(new Cell(i));

        }

    }

    private void addSnakesAndLadder() {
        int noOfSnakes = 4 + new Random().nextInt(2);
        int noOfLadder = 4 + new Random().nextInt(2);

        while (noOfLadder > 0) {
            int ladderStart = 2 + new Random().nextInt(width*height - 5);
            int ladderEnd = 2 + new Random().nextInt(width*height - 5);

            if (ladderStart >= ladderEnd) {
                continue;
            }

            Jump ladder = new Ladder(ladderStart, ladderEnd);
            Cell cell = cells.get(ladderStart);
            cell.setJump(ladder);
            noOfLadder--;
        }

        while (noOfSnakes > 0) {
            int snakeHead = 2 + new Random().nextInt(width*height - 5);
            int snakeTail = 2 + new Random().nextInt(width*height - 5);

            if (snakeHead <= snakeTail) {
                continue;
            }

            Jump snake = new Snake(snakeHead, snakeTail);
            Cell cell = cells.get(snakeHead);
            cell.setJump(snake);
            noOfSnakes--;
        }

    }
}
