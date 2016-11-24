// Authour: Tyrel Tachibana
// Date:    8/25/2016
// Notes:   Centralized Class for implementing all the button listeners.

package com.github.noreply.users.gramzanber.Program01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActionListener implements View.OnClickListener
{
    Context context;
    Button addButton;
    Button multiplyButton;
    Button computeButton;

    ActionListener(Context context)
    {
        this.context = context;
        addButton = (Button)((Activity)context).findViewById(R.id.addButton);
        multiplyButton = (Button)((Activity)context).findViewById(R.id.multiplyButton);
        computeButton = (Button)((Activity)context).findViewById(R.id.computeButton);
    }

    @Override
    public void onClick(View view)
    {
        if(view == addButton)
        {
            String numberOne = ((EditText)((Activity)context).findViewById(R.id.numberEditTextOne)).getText().toString();
            String numberTwo = ((EditText)((Activity)context).findViewById(R.id.numberEditTextTwo)).getText().toString();
            Intent newIntent = new Intent(context, AdditionActivity.class);
            newIntent.putExtra("NUMBER_ONE", numberOne);
            newIntent.putExtra("NUMBER_TWO", numberTwo);
            ((Activity) context).startActivityForResult(newIntent, 0);
        }
        else if(view == multiplyButton)
        {
            String numberOne = ((EditText)((Activity)context).findViewById(R.id.numberEditTextOne)).getText().toString();
            String numberTwo = ((EditText)((Activity)context).findViewById(R.id.numberEditTextTwo)).getText().toString();
            Intent newIntent = new Intent(context, MultiplyActivity.class);
            newIntent.putExtra("NUMBER_ONE", numberOne);
            newIntent.putExtra("NUMBER_TWO", numberTwo);
            ((Activity) context).startActivityForResult(newIntent, 1);
        }
        else if(view == computeButton)
        {
            double numberOne = Double.parseDouble(((EditText)((Activity)context).findViewById(R.id.numberEditTextOne)).getText().toString());
            double numberTwo = Double.parseDouble(((EditText)((Activity)context).findViewById(R.id.numberEditTextTwo)).getText().toString());
            if(context instanceof AdditionActivity)
            {
                ((EditText)((Activity)context).findViewById(R.id.resultEditText)).setText(numberOne + numberTwo + "");
                Intent newIntent = new Intent();
                newIntent.putExtra("RESULT", (numberOne + numberTwo));
                newIntent.putExtra("ACTION", "ADD");
                ((Activity) context).setResult(Activity.RESULT_OK, newIntent);
            }
            else if(context instanceof MultiplyActivity)
            {
                ((EditText)((Activity)context).findViewById(R.id.resultEditText)).setText(numberOne * numberTwo + "");
                Intent newIntent = new Intent();
                newIntent.putExtra("RESULT", numberOne * numberTwo);
                newIntent.putExtra("ACTION", "MULTIPLY");
                ((Activity) context).setResult(Activity.RESULT_OK, newIntent);
            }
            else
            {
                Toast notification = Toast.makeText(context, R.string.errorToast, Toast.LENGTH_LONG);
                notification.show();
            }
        }
        else
        {
            Toast notification = Toast.makeText(context, R.string.errorMessage, Toast.LENGTH_LONG);
            notification.show();
        }
    }
}
