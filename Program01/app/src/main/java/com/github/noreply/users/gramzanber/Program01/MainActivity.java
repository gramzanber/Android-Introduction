// Authour: Tyrel Tachibana
// Date:    8/25/2016
// Notes:   his class is used to set up all the runtime code needed to show the main activity.

package com.github.noreply.users.gramzanber.Program01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{
    Button addButton;
    Button multiplyButton;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        addButton = (Button)findViewById(R.id.addButton);
        multiplyButton = (Button)findViewById(R.id.multiplyButton);
        ActionListener listener = new ActionListener(this);
        addButton.setOnClickListener(listener);
        multiplyButton.setOnClickListener(listener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        this.result = data.getDoubleExtra("RESULT", 0.00);
        ((EditText)findViewById(R.id.resultEditText)).setText(result + "");
        String action = data.getStringExtra("ACTION");
        String actionMessage = (action.equals("ADD")) ? "addition" : "multiplication";
        Toast notification = Toast.makeText(this, "Result of " + actionMessage, Toast.LENGTH_LONG);
        notification.show();
    }
}
