package edu.uco.ttachibana.p3tyrelt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class TicTacToeActivity extends AppCompatActivity
{
    private Game ticTacToe;
    private Button[] gameButtons;
    public static Log logger;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);
        ticTacToe = new Game();
        gameButtons = new Button[9];
        logger = new Log();
        loadListeners();
    }

    private void loadListeners()
    {
        ActionListener listener = new ActionListener(this);
        int resourceId;
        for(int index = 0; index < 9; index = index + 1)
        {
            resourceId = (R.id.gameButton1) + index;
            gameButtons[index] = (Button)findViewById(resourceId);
            gameButtons[index].setOnClickListener(listener);
        }
        findViewById(R.id.newGameButton).setOnClickListener(listener);
        findViewById(R.id.viewLogButton).setOnClickListener(listener);
    }

    public void makeMove(int index, int row, int column)
    {
        gameButtons[index].setEnabled(false);
        // Added the blank string because setText does not take chars
        gameButtons[index].setText(ticTacToe.getPlayer() + "");
        this.ticTacToe.makeMove(ticTacToe.getPlayer(), row, column);
        if(!ticTacToe.anyWinners())
        {
            if(ticTacToe.getTurn() != 9)
            {
                ((TextView)findViewById(R.id.introText)).setText(ticTacToe.getPlayer() + "'s turn!");
            }
            else
            {
                ((TextView)findViewById(R.id.introText)).setText("Game Over: TIE - No winner!!!");
                logger.addHistory("draw", "", "");
            }
        }
        else
        {
            ((TextView)findViewById(R.id.introText)).setText("Game Over: Winner is " + ticTacToe.getPlayer() + "!!!");
            this.setWinner();
        }
    }

    private void setWinner()
    {
        String date = Calendar.YEAR + "/" + Calendar.MONTH + " " + Calendar.HOUR + ":" + Calendar.MINUTE;
        logger.addHistory(ticTacToe.getPlayer() + "", "Winner", date);
        for(int i = 0; i < 9; i = i + 1)
        {
            gameButtons[i].setEnabled(false);
        }
    }

    public void newGame()
    {
        ticTacToe = new Game();
        for(int index = 0; index < 9; index = index + 1)
        {
            gameButtons[index].setText("");
            gameButtons[index].setEnabled(true);
        }
    }
}
