// Authour: Tyrel Tachibana
// Date:    8/25/2016
// Notes:   This class is used to set up all the runtime code needed to show the main activity.

package com.github.noreply.users.gramzanber.Program00;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;

public class MainActivity extends Activity
{
    Button nameButton;
    Button degreeButton;
    Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListeners();
    }

    public void addListeners()
    {
        nameButton = (Button)findViewById(R.id.nameButton);
        degreeButton = (Button)findViewById(R.id.degreeButton);
        clearButton = (Button)findViewById(R.id.clearButton);
        ActionListener listener = new ActionListener(this);
        nameButton.setOnClickListener(listener);
        degreeButton.setOnClickListener(listener);
        clearButton.setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
}
