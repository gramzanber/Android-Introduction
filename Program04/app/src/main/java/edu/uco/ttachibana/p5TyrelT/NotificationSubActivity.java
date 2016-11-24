package edu.uco.ttachibana.p5TyrelT;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class NotificationSubActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        String url = MainActivity.departmentPicked.get_Url();
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
