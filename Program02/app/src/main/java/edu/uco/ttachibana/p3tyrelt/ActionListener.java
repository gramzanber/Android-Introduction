package edu.uco.ttachibana.p3tyrelt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tyrel on 8/25/2016.
 */
public class ActionListener implements View.OnClickListener
{
    Context context;
    Button newGameButton;
    Button viewLogButton;
    Button clearLogButton;
    Button[] gameButtons;

    ActionListener(Context context)
    {
        this.context = context;
        newGameButton = (Button)((Activity)context).findViewById(R.id.newGameButton);
        viewLogButton = (Button)((Activity)context).findViewById(R.id.viewLogButton);
        clearLogButton = (Button)((Activity)context).findViewById(R.id.clearLogButton);
        gameButtons = new Button[9];
        int resourceId;
        for(int index = 0; index < 9; index = index + 1)
        {
            resourceId = (R.id.gameButton1) + index;
            gameButtons[index] = (Button)((Activity)context).findViewById(resourceId);
        }
    }

    @Override
    public void onClick(View view)
    {
        if(view == newGameButton)
        {
            ((TextView)((Activity)context).findViewById(R.id.introText)).setText("New Game, O goes first!");
            ((TicTacToeActivity)context).newGame();
        }
        else if(view == viewLogButton)
        {
            Intent newIntent = new Intent(context, LogActivity.class);
            context.startActivity(newIntent);
        }
        else if(view == clearLogButton)
        {
            ((LogActivity)context).clearLog();
        }
        else if(view == gameButtons[0])
        {
            ((TicTacToeActivity)context).makeMove(0, 0, 0);
        }
        else if(view == gameButtons[1])
        {
            ((TicTacToeActivity)context).makeMove(1, 0, 1);
        }
        else if(view == gameButtons[2])
        {
            ((TicTacToeActivity)context).makeMove(2, 0, 2);
        }
        else if(view == gameButtons[3])
        {
            ((TicTacToeActivity)context).makeMove(3, 1, 0);
        }
        else if(view == gameButtons[4])
        {
            ((TicTacToeActivity)context).makeMove(4, 1, 1);
        }
        else if(view == gameButtons[5])
        {
            ((TicTacToeActivity)context).makeMove(5, 1, 2);
        }
        else if(view == gameButtons[6])
        {
            ((TicTacToeActivity)context).makeMove(6, 2, 0);
        }
        else if(view == gameButtons[7])
        {
            ((TicTacToeActivity)context).makeMove(7, 2, 1);
        }
        else if(view == gameButtons[8])
        {
            ((TicTacToeActivity)context).makeMove(8, 2, 2);
        }
        else
        {
            Toast notification = Toast.makeText(context, R.string.errorMessage, Toast.LENGTH_LONG);
            notification.show();
        }
    }
}
