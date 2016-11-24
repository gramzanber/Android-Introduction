package edu.uco.ttachibana.p4TyrelT;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DepartmentActivity extends Activity
{
    private ListView listview;
    ArrayAdapter<Department> adapter;
    private Department[] departments = {
            new Department("Biology", "405.974.5017", "http://biology.uco.edu/"),
            new Department("Chemistry", "405.974.2000", "http://www.uco.edu/cms/chemistry/"),
            new Department("Computer Science", "405.974.2000", "http://cs.uco.edu/www/"),
            new Department("Engineering", "405.974.2000", "http://www.uco.edu/cms/engineering/"),
            new Department("Funeral Services", "405.974.5001", "http://www.uco.edu/cms/funeral/index.asp"),
            new Department("Mathematics & Statistics", "405.974.5012", "http://www.math.uco.edu/"),
            new Department("Nursing", "405.974.2000", "http://www.uco.edu/cms/nursing/")
    };

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private void loadComponents()
    {
        setContentView(R.layout.activity_department);
        listview = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_view, departments);
        listview.setAdapter(adapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    }

    private void loadListeners()
    {
        ActionListener listener = new ActionListener(this);
        listview.setMultiChoiceModeListener(listener);
    }
}
