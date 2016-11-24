// Authour: Tyrel Tachibana
// Date:    8/25/2016
// Notes:   his class is used to set up all the runtime code needed to show the multiply activity.

package com.github.noreply.users.gramzanber.Program01;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;

public class MultiplyActivity extends Activity
{
    Button computeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiply);
        setValues();
        loadListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    public void loadListeners()
    {
        computeButton = (Button)findViewById(R.id.computeButton);
        ActionListener listener = new ActionListener(this);
        computeButton.setOnClickListener(listener);
    }

    public void setValues()
    {
        String numberOne = getIntent().getStringExtra("NUMBER_ONE");
        String numberTwo = getIntent().getStringExtra("NUMBER_TWO");
        ((EditText)findViewById(R.id.numberEditTextOne)).setText(numberOne);
        ((EditText)findViewById(R.id.numberEditTextTwo)).setText(numberTwo);
    }
}
