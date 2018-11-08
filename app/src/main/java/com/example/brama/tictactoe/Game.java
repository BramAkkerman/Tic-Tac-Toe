package com.example.brama.tictactoe;

import android.util.Log;

import java.io.Serializable;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;
    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState choose(int row, int column) {
        TileState tile = board[row][column];
        if(gameOver) {
            return TileState.INVALID;
        }
        if (tile != TileState.BLANK) {
            return TileState.INVALID;
        } else {
            if (playerOneTurn) {
                playerOneTurn = false;
                board[row][column] = TileState.CROSS;
                return TileState.CROSS;
            } else {
                playerOneTurn = true;
                board[row][column] = TileState.CIRCLE;
                return TileState.CIRCLE;
            }
        }
    }

    public GameState won(TileState type, int row, int column) {
        if ((board[row][0] == type && board[row][1] == type && board[row][2] == type)
                || (board[0][column] == type && board[1][column] == type && board[2][column] == type)
                || (row == column && board[0][0] == type && board[1][1] == type && board[2][2] == type)
                || ((row + column) == 2 && board[2][0] == type && board[1][1] == type && board[0][2] == type)
                ) {
            if (type == TileState.CROSS)
                return GameState.PLAYER_ONE;
            else if (type == TileState.CIRCLE)
                return GameState.PLAYER_TWO;
        } else if (movesPlayed == 9) {
            return GameState.DRAW;
        }
        return GameState.IN_PROGRESS;
    }

    public void incrMovesPlayed() {
        movesPlayed += 1;
    }
}
