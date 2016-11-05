/*
name: Denis Stepanov
student ID: n01077691
assignment #: 2
*/

package denis.stepanov;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        TextView tv1= (TextView) getActivity().findViewById(R.id.date_number);

        //one way to show the date
        String day1 = getString(R.string.day);
        String month1 = getString(R.string.month);
        String year1 = getString(R.string.year);
        String text = (year1 +view.getYear()+ month1 +view.getMonth()+ day1 +view.getDayOfMonth());

        //another way
        String text1 = (view.getDayOfMonth() + "/" + view.getMonth() + "/" + view.getYear());

        //first way
        //tv1.setText(text);

        //second way
        tv1.setText(text1);
    }
}
