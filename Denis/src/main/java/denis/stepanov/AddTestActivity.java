/*
name: Denis Stepanov
student ID: n01077691
assignment #: 2
*/

package denis.stepanov;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddTestActivity extends AppCompatActivity {
    EditText edit_cho;
    EditText edit_temp;
    EditText edit_date;
    EditText edit_resp_rate, edit_ox_level, edit_heart_rate;
    Button button_addtest;
    RadioButton radio_positive;
    RadioButton radio_negative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);

        //initialize
        edit_cho = (EditText) findViewById(R.id.cholesterol_number);
        edit_temp = (EditText) findViewById(R.id.temperature_number);
        edit_date = (EditText) findViewById(R.id.date_number);
        edit_resp_rate = (EditText) findViewById(R.id.resp_rate_number);
        edit_ox_level = (EditText) findViewById(R.id.blood_oxygen_level_number);
        edit_heart_rate = (EditText) findViewById(R.id.heart_beat_rate_number);
        button_addtest = (Button) findViewById(R.id.button_add_test);
        radio_positive = (RadioButton) findViewById(R.id.radio_positive);
        radio_negative = (RadioButton) findViewById(R.id.radio_negative);

        //button onClick check
        button_addtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check cholesterol
                boolean choTest = checkCho(edit_cho.getText().toString());
                if (!choTest) {
                    Toast.makeText(AddTestActivity.this, R.string.invalid_cho, Toast.LENGTH_SHORT)
                            .show();
                }
                //check temperature
                boolean tempTest = checkTemp(edit_temp.getText().toString());
                if (!tempTest) {
                    Toast.makeText(AddTestActivity.this, R.string.invalid_temp, Toast.LENGTH_SHORT)
                            .show();
                }
                //check date
                boolean dateTest = checkDate(edit_date.getText().toString());
                if (!dateTest) {
                    Toast.makeText(AddTestActivity.this, R.string.invalid_date, Toast.LENGTH_SHORT)
                            .show();
                }

                //check Respiratory rate
                boolean respTest = checkResp(edit_resp_rate.getText().toString());
                if (!respTest) {
                    Toast.makeText(AddTestActivity.this, R.string.invalid_resp_rate, Toast.LENGTH_SHORT)
                            .show();
                }

                //check Blood Oxygen Level
                boolean bloodTest = checkBlood(edit_ox_level.getText().toString());
                if (!bloodTest) {
                    Toast.makeText(AddTestActivity.this, R.string.invalid_blood_oxygen_level, Toast.LENGTH_SHORT)
                            .show();
                }

                //check Heart Beat Rate
                boolean beatRateTest = checkBeatRate(edit_heart_rate.getText().toString());
                if (!beatRateTest) {
                    Toast.makeText(AddTestActivity.this, R.string.invalid_heart_beat_rate, Toast.LENGTH_SHORT)
                            .show();
                }

                Intent intent = new Intent(AddTestActivity.this, DenisActivity.class);

                //check all
                if ((choTest && tempTest && dateTest && respTest && bloodTest && beatRateTest)) {
                    startActivity(intent);
                    }
            }
        });
    }

    //Check regexp cho
    public boolean checkCho(String cho) {
        if(cho.length() >= 2 && cho.matches("^[0-9]*$"))
            return true;
        else
            return false;
    }

    //Check regexp temp
    public boolean checkTemp(String temp) {
        if(temp.matches("^\\d{2}\\.\\d{1}$"))
            return true;
        else
            return false;
    }

    //Check regexp date
    public boolean checkDate(String date) {
        if(date.length() > 0)
            return true;
        else
            return false;
    }

    //Check regexp respritaroty rate
    public boolean checkResp(String resp) {
        if(resp.length() >= 2 && resp.matches("^[0-9]*$"))
            return true;
        else
            return false;
    }

    //Check regexp blood oxygen level
    public boolean checkBlood(String blood) {
        if(blood.length() >= 2 && blood.matches("^[0-9]*$"))
            return true;
        else
            return false;
    }

    //Check regexp heart beat rate
    public boolean checkBeatRate(String rate) {
        if(rate.length() >= 2 && rate.matches("^[0-9]*$"))
            return true;
        else
            return false;
    }

    //initialize date picker fragment
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent = null;

        switch(item.getItemId())
        {
            case R.id.search:
                intent = new Intent(AddTestActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
