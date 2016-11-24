// Authour: Tyrel Tachibana
// Date:    8/25/2016
// Notes:   Centralized Class for implementing all the button listeners.

package com.github.noreply.users.gramzanber.Program00;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActionListener implements View.OnClickListener
{
    Context context;
    TextView introText;
    Button nameButton;
    Button degreeButton;
    Button clearButton;

    ActionListener(Context context)
    {
        this.context = context;
        this.introText = (TextView)((Activity)context).findViewById(R.id.introText);
        nameButton = (Button)((Activity)context).findViewById(R.id.nameButton);
        degreeButton = (Button)((Activity)context).findViewById(R.id.degreeButton);
        clearButton = (Button)((Activity)context).findViewById(R.id.clearButton);
    }

    @Override
    public void onClick(View view)
    {
        if(view == nameButton) { introText.setText(view.getResources().getString(R.string.programmerName)); }
        else if(view == degreeButton) { introText.setText(view.getResources().getString(R.string.degreePlan)); }
        else if(view == clearButton) { introText.setText(""); }
        else { introText.setText(view.getResources().getString(R.string.errorMessage)); }
    }
}
