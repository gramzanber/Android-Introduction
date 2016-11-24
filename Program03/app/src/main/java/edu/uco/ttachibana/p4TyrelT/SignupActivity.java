package edu.uco.ttachibana.p4TyrelT;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

public class SignupActivity extends Activity
{
    private Button buttonRegister;
    private Button buttonDate;
    private Spinner spinner;
    private RadioButton radiobuttonMale;
    private RadioButton radiobuttonFemale;
    private CheckBox checkboxAndroid;
    private CheckBox checkboxCPlusPlus;
    private CheckBox checkboxiOS;
    private CheckBox checkboxJava;
    private CheckBox checkboxSwift;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        loadComponents();
        loadListeners();
    }

    private void loadComponents()
    {
        setContentView(R.layout.activity_signup);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        spinner = (Spinner) findViewById(R.id.spinnerClassification);
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.arrayClassification, R.layout.spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinAdapter);

        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        buttonDate = (Button)findViewById(R.id.buttonDate);
        radiobuttonMale = (RadioButton)findViewById(R.id.radiobuttonMale);
        radiobuttonFemale = (RadioButton)findViewById(R.id.radiobuttonFemale);
        checkboxAndroid = (CheckBox)findViewById(R.id.checkboxAndroid);
        checkboxJava = (CheckBox)findViewById(R.id.checkboxJava);
        checkboxCPlusPlus = (CheckBox)findViewById(R.id.checkboxCPlusPlus);
        checkboxSwift = (CheckBox)findViewById(R.id.checkboxSwift);
        checkboxiOS = (CheckBox)findViewById(R.id.checkboxiOS);
    }

    private void loadListeners()
    {
        ActionListener listener = new ActionListener(this);
        buttonDate.setOnClickListener(listener);
        buttonRegister.setOnClickListener(listener);
        radiobuttonMale.setOnClickListener(listener);
        radiobuttonFemale.setOnClickListener(listener);
        checkboxAndroid.setOnClickListener(listener);
        checkboxJava.setOnClickListener(listener);
        checkboxCPlusPlus.setOnClickListener(listener);
        checkboxSwift.setOnClickListener(listener);
        checkboxiOS.setOnClickListener(listener);
        spinner.setOnItemSelectedListener(listener);
    }
}
