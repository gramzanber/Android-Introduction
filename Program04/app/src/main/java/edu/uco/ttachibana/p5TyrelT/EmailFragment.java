package edu.uco.ttachibana.p5TyrelT;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EmailFragment extends Fragment
{
    private int staticContactIndex = -1;
    private int contactAmount;
    private TextView contactName = null;

    private TextView contactPhoneNumber = null;
    private TextView contactEmailAddress = null;

    int getShownIndex() { return staticContactIndex; }

    void showIndex(int newIndex)
    {
        if(newIndex < 0 || newIndex >= contactAmount) return;
        staticContactIndex = newIndex;
        Contact temp = ContactActivity.contactsList.get(staticContactIndex);
        contactName.setText(temp.getFirstName() + " " + temp.getLastName());
        contactPhoneNumber.setText(temp.getPhone());
        contactEmailAddress.setText(temp.getEmail());
        contactEmailAddress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Contact temp = ContactActivity.contactsList.get(staticContactIndex);
                String uriText = "mailto:" + contactEmailAddress.getText() +
                        "?subject=" + Uri.encode("Invitation to Party") + "&body="
                        + Uri.encode("Hey there, " + temp.getFirstName() + " " + temp.getLastName() + " would you like to go out and dance with me?");
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(uriText));
                startActivity(intent);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_email, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        contactName = (TextView) getActivity().findViewById(R.id.textViewName );
        contactPhoneNumber = (TextView) getActivity().findViewById(R.id.textViewPhone );
        contactEmailAddress = (TextView) getActivity().findViewById(R.id.textViewEmail );
        contactAmount = ContactActivity.contactsList.size();
    }
}
