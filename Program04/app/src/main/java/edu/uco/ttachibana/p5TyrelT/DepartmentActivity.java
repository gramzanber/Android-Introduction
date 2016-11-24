package edu.uco.ttachibana.p5TyrelT;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class DepartmentActivity extends DialogFragment
{
    public interface PickDepartmentListener
    {
        void onPickDepartmentClick(int departmentIndex, DialogFragment dialog);
    }

    PickDepartmentListener listener;

    @Override
    public void onAttach(Context activity)
    {
        super.onAttach(activity);
        try
        {

            listener = (PickDepartmentListener) activity;
        }
        catch (ClassCastException e) { throw new ClassCastException(activity.toString() + " Implement PickDepartmentListener idiot!"); }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.departments).setItems(R.array.department_array, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) { listener.onPickDepartmentClick(which, DepartmentActivity.this); }
        });
        return builder.create();
    }
}
