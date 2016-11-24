package edu.uco.ttachibana.p4TyrelT;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

public class ActionListener implements View.OnClickListener, AbsListView.MultiChoiceModeListener, AdapterView.OnItemSelectedListener
{
    private Context context;
    private Button signupButton;
    private Button departmentButton;
    private Button buttonDate;
    private Button buttonRegister;
    private ListView listview;
    private String gender;
    private String skills;
    private String pickedClass;
    private EditText name;
    private EditText password;
    private RadioButton radiobuttonMale;
    private RadioButton radiobuttonFemale;
    private CheckBox checkboxAndroid;
    private CheckBox checkboxCPlusPlus;
    private CheckBox checkboxiOS;
    private CheckBox checkboxJava;
    private CheckBox checkboxSwift;
    private Spinner spinner;
    private Department[] departments = {
            new Department("Biology", "405.974.5017", "http://biology.uco.edu/"),
            new Department("Chemistry", "405.974.2000", "http://www.uco.edu/cms/chemistry/"),
            new Department("Computer Science", "405.974.2000", "http://cs.uco.edu/www/"),
            new Department("Engineering", "405.974.2000", "http://www.uco.edu/cms/engineering/"),
            new Department("Funeral Services", "405.974.5001", "http://www.uco.edu/cms/funeral/index.asp"),
            new Department("Mathematics & Statistics", "405.974.5012", "http://www.math.uco.edu/"),
            new Department("Nursing", "405.974.2000", "http://www.uco.edu/cms/nursing/")
    };

    ActionListener(Context context)
    {
        this.context = context;
        signupButton = (Button)((Activity)context).findViewById(R.id.signUp);
        departmentButton = (Button)((Activity)context).findViewById(R.id.departmentInfo);
        listview = (ListView)((Activity)context).findViewById(R.id.listview);
        buttonDate = (Button)((Activity)context).findViewById(R.id.buttonDate);
        buttonRegister = (Button)((Activity)context).findViewById(R.id.buttonRegister);
        radiobuttonMale = (RadioButton)((Activity)context).findViewById(R.id.radiobuttonMale);
        radiobuttonFemale = (RadioButton)((Activity)context).findViewById(R.id.radiobuttonFemale);
        checkboxAndroid = (CheckBox)((Activity)context).findViewById(R.id.checkboxAndroid);
        checkboxJava = (CheckBox)((Activity)context).findViewById(R.id.checkboxJava);
        checkboxCPlusPlus = (CheckBox)((Activity)context).findViewById(R.id.checkboxCPlusPlus);
        checkboxSwift = (CheckBox)((Activity)context).findViewById(R.id.checkboxSwift);
        checkboxiOS = (CheckBox)((Activity)context).findViewById(R.id.checkboxiOS);
        spinner = (Spinner)((Activity)context).findViewById(R.id.spinnerClassification);
        skills = "";
    }

    @Override
    public void onClick(View view)
    {
        if(view == signupButton)
        {
            Intent intent = new Intent(context, SignupActivity.class);
            context.startActivity(intent);
        }
        else if(view == departmentButton)
        {
            Intent intent = new Intent(context, DepartmentActivity.class);
            context.startActivity(intent);
        }
        else if(view == buttonDate)
        {
            DialogFragment fragment = new DatePickerFragment();
            fragment.show(((Activity)context).getFragmentManager(), "datePicker");
        }
        else if(view == buttonRegister)
        {
            name = (EditText)((Activity)context).findViewById(R.id.edittextName);
            password = (EditText)((Activity)context).findViewById(R.id.edittextPassword);
            Intent intent = new Intent(context, RegisterActivity.class);
            intent.putExtra("NAME", name.getText().toString());
            intent.putExtra("PASSWORD", password.getText().toString());
            intent.putExtra("SKILLS", skills);
            intent.putExtra("GENDER", gender);
            intent.putExtra("CLASS", pickedClass);
            context.startActivity(intent);
        }
        else if(view == radiobuttonMale) { gender = "Male"; }
        else if(view == radiobuttonFemale) { gender = "Female"; }
        else if(view == checkboxAndroid) { skills = skills + "Android" + System.getProperty("line.separator"); }
        else if(view == checkboxJava) { skills = skills + "Java" + System.getProperty("line.separator"); }
        else if(view == checkboxCPlusPlus) { skills = skills + "C++" + System.getProperty("line.separator"); }
        else if(view == checkboxSwift) { skills = skills + "Swift" + System.getProperty("line.separator"); }
        else if(view == checkboxiOS) { skills = skills + "IOS" + System.getProperty("line.separator"); }
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_call:
                performCall();
                mode.finish(); // Action picked, so close the CAB
                return true;
            case R.id.menu_email:
                performWebSearch();
                mode.finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu)
    {
        // Inflate a menu resource providing context menu items
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode)
    {

    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu)
    {
        return false; // return false if nothing is done
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked)
    {
        actionMode.setSubtitle("(" + listview.getCheckedItemCount() + " selected)");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        pickedClass = parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        pickedClass = "Freshman";
    }

    private void performCall()
    {
        SparseBooleanArray selected = listview.getCheckedItemPositions();
        String phone = "";
        for(int i = 0; i < selected.size(); i++)
        {
            if(selected.valueAt(i))
            {
                int pos = selected.keyAt(i);
                phone = departments[pos].get_phone();
            }
        }
        phone = "tel:" + phone;
        Uri number = Uri.parse(phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, number);
        context.startActivity(intent);
    }

    private void performWebSearch()
    {
        SparseBooleanArray selected = listview.getCheckedItemPositions();
        String url = "";
        for(int i = 0; i < selected.size(); i++)
        {
            if(selected.valueAt(i))
            {
                int pos = selected.keyAt(i);
                url = departments[pos].get_Url();
            }
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);

    }
}
