package edu.uco.ttachibana.p4TyrelT;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    private static String year_picked = "0";
    private static String month_picked = "0";
    private static String day_picked = "0";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        return dialog;

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        year_picked = Integer.toString(year);
        month_picked = Integer.toString(month);
        day_picked = Integer.toString(day);
    }

    public static String getYear() { return year_picked; }
    public static String getMonth() { return month_picked; }
    public static String getDay() { return day_picked; }
}
