package edu.uco.ttachibana.p5TyrelT;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements DepartmentActivity.PickDepartmentListener
{
    private Button buttonContacts;
    private Button buttonDepartment;
    private int notificationCounter;
    private PendingIntent contentIntent;
    private static final int MY_NOTIFICATION_ID = 1;
    public static Department departmentPicked;
    public Department[] departments =
    {
            new Department("Biology", "http://biology.uco.edu/"),
            new Department("Chemistry", "http://www.uco.edu/cms/chemistry/"),
            new Department("Computer Science", "http://cs.uco.edu/www/"),
            new Department("Engineering", "http://www.uco.edu/cms/engineering/"),
            new Department("Funeral Services", "http://www.uco.edu/cms/funeral/index.asp"),
            new Department("Mathematics & Statistics", "http://www.math.uco.edu/"),
            new Department("Nursing", "http://www.uco.edu/cms/nursing/")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonContacts = (Button) findViewById(R.id.buttonMycontacts);
        buttonDepartment = (Button) findViewById(R.id.buttonDepartment);

        buttonContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        buttonDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DepartmentActivity d = new DepartmentActivity();
                d.show(getFragmentManager(), "department");
            }
        });
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

    @Override
    public void onPickDepartmentClick(int departmentIndex, DialogFragment dialog)
    {
        departmentPicked = departments[departmentIndex];
        Intent letsTry = new Intent(Intent.ACTION_VIEW, Uri.parse(departmentPicked.get_Url()));
        contentIntent = PendingIntent.getActivity(this, 0,
                letsTry, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder notificationBuilder = new Notification.Builder(
                getApplicationContext())
                .setSmallIcon(android.R.drawable.stat_notify_sync)
                .setAutoCancel(true)
                .setContentTitle(departmentPicked.get_name())
                .setContentText("You've been notified" + " (" + ++notificationCounter + ")")
                .setContentIntent(contentIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(MY_NOTIFICATION_ID,
                notificationBuilder.build());
    }
}
