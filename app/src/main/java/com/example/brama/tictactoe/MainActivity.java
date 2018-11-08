package com.example.brama.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Game game;
    GridLayout userInterface;
    GameState won = GameState.IN_PROGRESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
        userInterface = findViewById(R.id.gridLayout);
    }

    public void tileClicked(View view) {
        if (won == GameState.IN_PROGRESS) {
            int id = view.getId();
            int[] rowAndColumn = rowColumn(id);
            int row = rowAndColumn[0];
            int column = rowAndColumn[1];

            Button tile = (Button) view;
            TileState state = game.choose(row, column);
            TextView wonText = findViewById(R.id.gameState);

            switch (state) {
                case CROSS:
                    tile.setText("X");
                    tile.setEnabled(false);
                    game.incrMovesPlayed();
                    won = game.won(TileState.CROSS, row, column);
                    if (won == GameState.PLAYER_ONE) {
                        wonText.setText("Player 1 has won!");
                        wonText.setVisibility(View.VISIBLE);
                        int count = userInterface.getChildCount();
                        for (int i=0;i<count;i++) {
                            Button btn = (Button) userInterface.getChildAt(i);
                            btn.setEnabled(false);
                        }
                    }
                    break;
                case CIRCLE:
                    tile.setText("O");
                    tile.setEnabled(false);
                    game.incrMovesPlayed();
                    won = game.won(TileState.CIRCLE, row, column);
                    if (won == GameState.PLAYER_TWO) {
                        wonText.setText("Player 2 has won!");
                        wonText.setVisibility(View.VISIBLE);
                        int count = userInterface.getChildCount();
                        for (int i=0;i<count;i++) {
                            Button btn = (Button) userInterface.getChildAt(i);
                            btn.setEnabled(false);
                        }
                    }
                    break;
                case INVALID:
                    break;
            }

            if (won == GameState.DRAW) {
                wonText.setText("It's a draw!");
                wonText.setVisibility(View.VISIBLE);
            }
        }
    }

    private int[] rowColumn(int id) {
        int[] rowAndColumn = new int[2];
        switch (id) {
            case R.id.r1c1:
                rowAndColumn[0] = 0;
                rowAndColumn[1] = 0;
                break;
            case R.id.r1c2:
                rowAndColumn[0] = 0;
                rowAndColumn[1] = 1;
                break;
            case R.id.r1c3:
                rowAndColumn[0] = 0;
                rowAndColumn[1] = 2;
                break;
            case R.id.r2c1:
                rowAndColumn[0] = 1;
                rowAndColumn[1] = 0;
                break;
            case R.id.r2c2:
                rowAndColumn[0] = 1;
                rowAndColumn[1] = 1;
                break;
            case R.id.r2c3:
                rowAndColumn[0] = 1;
                rowAndColumn[1] = 2;
                break;
            case R.id.r3c1:
                rowAndColumn[0] = 2;
                rowAndColumn[1] = 0;
                break;
            case R.id.r3c2:
                rowAndColumn[0] = 2;
                rowAndColumn[1] = 1;
                break;
            case R.id.r3c3:
                rowAndColumn[0] = 2;
                rowAndColumn[1] = 2;
                break;
        }
        return rowAndColumn;
    }

    public void resetClicked(View v) {
        game = new Game();
        won = GameState.IN_PROGRESS;
        int count = userInterface.getChildCount();
        for (int i=0;i<count;i++) {
            Button tile = (Button) userInterface.getChildAt(i);
            tile.setText("");
            tile.setEnabled(true);
        }
        TextView wonText = findViewById(R.id.gameState);
        wonText.setVisibility(View.INVISIBLE);
    }
}
