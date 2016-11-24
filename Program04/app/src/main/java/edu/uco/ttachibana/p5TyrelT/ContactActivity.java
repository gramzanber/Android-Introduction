package edu.uco.ttachibana.p5TyrelT;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class ContactActivity extends Activity implements NamesFragment.ListSelectionListener
{
    private FragmentManager fragmentManager;
    public static ArrayList<Contact> contactsList;
    public static Contact[] contacts =
    {
            new Contact("Tyrel", "Tachibana", "4053881790", "ttachibana@uco.edu"),
            new Contact("Blake", "Lively", "555555555", "blively@uco.edu"),
            new Contact("Kristen", "Bell", "555555555", "kbell@uco.edu"),
            new Contact("Mila", "Kunis", "555555555", "mkunis@uco.edu"),
            new Contact("Jennifer", "Lawrence", "555555555", "jlawrence@uco.edu"),
            new Contact("Hayden", "Panettiere", "555555555", "hpanettiere@uco.edu"),
            new Contact("Shailene", "Woodley", "555555555", "swoodley@uco.edu"),
            new Contact("Emma", "Watson", "555555555", "ewatson@uco.edu"),
            new Contact("Jennifer", "Aniston", "555555555", "janiston@uco.edu"),
            new Contact("Ariel", "Winter", "555555555", "awinter@uco.edu"),
            new Contact("Alexis", "Bledel", "555555555", "abledel@uco.edu"),
            new Contact("Kaley", "Cuoco", "555555555", "kcuoco@uco.edu"),
            new Contact("Sofia", "Vergara", "555555555", "svergara@uco.edu"),
            new Contact("Dakota", "Fanning", "555555555", "dfanning@uco.edu"),
            new Contact("Eva", "Lovia", "555555555", "elovia@uco.edu"),
            new Contact("Shiori", "Kamisaki", "555555555", "skamisaki@uco.edu"),
            new Contact("Riley", "Reid", "555555555", "rreid@uco.edu"),
            new Contact("Mia", "Khalifa", "555555555", "mkhalifa@uco.edu"),
            new Contact("Asa", "Akira", "555555555", "aakira@uco.edu"),
            new Contact("Mia", "Malkova", "555555555", "mmalkova@uco.edu"),
            new Contact("August", "Ames", "555555555", "aames@uco.edu"),
            new Contact("Sasha", "Grey", "555555555", "sgrey@uco.edu"),
            new Contact("Sara", "Jay", "555555555", "sjay@uco.edu"),
            new Contact("Veronica", "Rodriguez", "555555555", "vrodriguez@uco.edu"),
            new Contact("Hitomi", "Tanaka", "555555555", "htanaka@uco.edu"),
            new Contact("Elsa", "Jean", "555555555", "ejean@uco.edu"),
            new Contact("Dakota", "Skye", "555555555", "dskye@uco.edu"),
            new Contact("Anthony", "Lebel", "555555555", "alebel@uco.edu"),
            new Contact("Micah", "McKinnon", "555555555", "mmckinnon@uco.edu"),
            new Contact("Maggie", "Chang", "555555555", "pchang73@uco.edu")
    };

    private EmailFragment emailFragment = new EmailFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        contactsList = new ArrayList<Contact>(Arrays.asList(contacts));
        Collections.sort(contactsList);

        super.onCreate(savedInstanceState);
        //ListView listview = (ListView)findViewById(R.id.listNames);
        //ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, contacts);
        //listview.setAdapter(adapter);

        setContentView(R.layout.activity_contact);
        fragmentManager = getFragmentManager();
    }


    @Override
    public void onListSelection(int index)
    {
        if(!emailFragment.isAdded())
        {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.email, emailFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
        }
        if(emailFragment.getShownIndex() != index) { emailFragment.showIndex(index); }
    }
}
