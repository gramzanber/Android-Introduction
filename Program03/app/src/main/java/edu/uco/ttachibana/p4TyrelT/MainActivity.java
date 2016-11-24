package edu.uco.ttachibana.p4TyrelT;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity
{

    Button signupButton;
    Button departmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        loadComponents();
        loadListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings) { return true; }
        return super.onOptionsItemSelected(item);
    }

    private void loadComponents()
    {
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        signupButton = (Button) findViewById(R.id.signUp);
        departmentButton = (Button) findViewById(R.id.departmentInfo);
    }

    private void loadListeners()
    {
        ActionListener listener = new ActionListener(this);
        signupButton.setOnClickListener(listener);
        departmentButton.setOnClickListener(listener);
    }
}
