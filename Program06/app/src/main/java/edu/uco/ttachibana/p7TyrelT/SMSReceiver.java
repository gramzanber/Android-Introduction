// created 10/10/2016

package edu.uco.ttachibana.p7TyrelT;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class SMSReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();
        SmsMessage[] message;
        String phoneNumber = new String();
        String phoneMessage = new String();
        if (bundle != null)
        {
            Object[] pdus = (Object[]) bundle.get("pdus");
            message = new SmsMessage[pdus.length];
            for (int i=0; i < message.length; i++)
            {
                String format = bundle.getString("format");
                message[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                phoneNumber = message[i].getOriginatingAddress();
                phoneMessage = message[i].getMessageBody().toString();
            }
            if(phoneNumber.equals("1111111111"))
            {
                if(phoneMessage.equals("phone"))
                {
                    Intent intent2 = new Intent(context, MapsPhoneActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent2);

                }
                else if(phoneMessage.equals("web"))
                {
                    Intent intent2 = new Intent(context, MapsWebActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent2);
                }
            }
            else if(phoneNumber.equals("1111112222"))
            {
                Intent intent2 = new Intent(context, WeatherActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent2.putExtra("CITY", phoneMessage);
                context.startActivity(intent2);
            }
            else
            {
                Toast.makeText(context , "Sender: " + phoneNumber + "\n" + "Message: " + phoneMessage, Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(context , "Major Error: Use magic escape pod!", Toast.LENGTH_LONG).show();
        }
    }
}
