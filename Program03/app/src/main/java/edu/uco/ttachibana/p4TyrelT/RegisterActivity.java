package edu.uco.ttachibana.p4TyrelT;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RegisterActivity extends Activity
{
    private String name;
    private String password;
    private String gender;
    private String pickedClass;
    private String skills;
    private String date;
    private TextView textviewName;
    private TextView textviewPassword;
    private TextView textviewGender;
    private TextView textviewSkills;
    private TextView textviewPickedClass;
    private TextView textviewDateOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textviewName = (TextView) findViewById(R.id.textviewName);
        textviewPassword = (TextView) findViewById(R.id.textviewPassword);
        textviewGender = (TextView) findViewById(R.id.textviewGender);
        textviewPickedClass = (TextView) findViewById(R.id.textviewClassification);
        textviewSkills = (TextView) findViewById(R.id.textviewSkills);
        textviewDateOfBirth = (TextView) findViewById(R.id.textviewDateOfBirth);

        name = getIntent().getStringExtra("NAME");
        password = getIntent().getStringExtra("PASSWORD");
        gender = getIntent().getStringExtra("GENDER");
        pickedClass = getIntent().getStringExtra("CLASS");
        skills = getIntent().getStringExtra("SKILLS");
        date = DatePickerFragment.getMonth() + "/" + DatePickerFragment.getDay() + "/" + DatePickerFragment.getYear();

        textviewName.setText(name);
        textviewPassword.setText(password);
        textviewGender.setText(gender);
        textviewSkills.setText(skills);
        textviewPickedClass.setText(pickedClass);
        textviewDateOfBirth.setText(date);
    }
}
