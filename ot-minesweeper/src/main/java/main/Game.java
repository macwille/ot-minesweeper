package main;

import java.util.Random;

/**
 * Class that represents a game of minesweeper without a GUI
 *
 * @since 1.0
 * @author Ville Manninen
 */
public class Game {

    private static int[][] grid;
    private static Player player;
    private static final int WIDTH = 10;
    private static int mines;
    private static int turn;
    private static boolean gameOver;

    /**
     * Creates a game of minesweeper with WIDTH x WIDTH grid. Width is equal to
     * 10. holding 30 mines starting at turn 1.
     */
    public Game() {
        this.grid = new int[WIDTH][WIDTH];
        this.mines = 30;
        this.gameOver = false;
        this.turn = 1;

    }

    /**
     * Fills the grid with int value 0 and then places 30 mines randomly.
     *
     * @since 1.0
     */
    public void newGame() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                grid[i][j] = 0;
            }
        }
        int minesLeft = this.mines;
        Random random = new Random();
        while (minesLeft > 0) {
            int iRandom = random.nextInt(WIDTH);
            int jRandom = random.nextInt(WIDTH);
            if (grid[iRandom][jRandom] == 0) {
                grid[iRandom][jRandom] = 1;
                minesLeft--;
            }
        }
    }

    /**
     * Method to increment the turn parameter by 1. If the incremented turn is
     * equal to empty squares of the game method calls setGameOver(); ending the
     * game.
     */
    public void nextTurn() {
        turn++;
        if (turn == ((WIDTH * WIDTH) - this.mines)) {
            setGameOver();
        }
    }

    public int getTurn() {
        return this.turn;
    }

    public void setGameOver() {
        this.gameOver = true;
    }

    /**
     * Returns true if game is over else returns false.
     *
     * @return boolean - return Game parameter gameOver
     */
    public boolean getGameover() {
        return this.gameOver;
    }

    /**
     * Method for checking if a square holds a mine.
     *
     * @param x - height coordinate
     * @param y - width coordinate
     * @return true if square if a mine else returns false
     */
    public boolean checkForMine(int x, int y) {

        return grid[y][x] == 1;

    }

    /**
     * Method for checking the number on neighbouring squares that hold a mine.
     *
     * @param x - height coordinate
     * @param y - width coordinate
     * @return int - number of neighbour squares that hold a mine.
     */
    public int checkNeighbours(int x, int y) {
        int mines = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (checkInsideGrid(i, j)) {

                    if (checkForMine(i, j)) {
                        mines++;
                    }
                }
            }
        }
        if (checkForMine(x, y)) {
            mines--;
        }
        return mines;
    }

    /**
     * Checks if given coordinate values are inside the game grid.
     *
     * @param x - height coordinate
     * @param y - width coordinate
     * @return true if x and y values are inside the grid
     */
    public boolean checkInsideGrid(int x, int y) {
        return x >= 0 && x <= WIDTH && y >= 0 && y <= WIDTH;
    }

    public void setPlayer(String name) {
        Player player = new Player(name);
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns a String representation of the current game 1 equals a mine and 0
     * equals no mine Overrides the method toString()
     *
     * @return String - representation of the game as a String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                sb.append(grid[i][j]);

            }
            sb.append("\n");
        }
        return sb.toString();
    }
}