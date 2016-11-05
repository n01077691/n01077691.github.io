/*
name: Denis Stepanov
student ID: n01077691
assignment #: 2
*/


package denis.stepanov;
//change spinner

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;



public class AddPatientActivity extends AppCompatActivity {
    public static final String PREFS = "sharedPreferences";
    EditText editFname;
    EditText editLname;
    EditText editID;
    Button button;
    Spinner spinner;
    RadioButton radio_male;
    RadioButton radio_female;
    DatabaseOperations databaseOperations;
    SQLiteDatabase sqLiteDatabase;
    RadioGroup radioGroup1;
    String gender;
    Boolean check_Radio;
    TextView text_id;
    String number = "100000";
    Integer number_r = 0,
            new_int = 0;


    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        //initialize
        editFname = (EditText) findViewById(R.id.editText);
        editLname = (EditText) findViewById(R.id.editText2);
        radio_male = (RadioButton) findViewById(R.id.male);
        radio_female = (RadioButton) findViewById(R.id.female);
        button = (Button) findViewById(R.id.button);
        text_id = (TextView)findViewById(R.id.text_view_id);



        //spinner selections
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add(getString(R.string.audiology));
        list.add(getString(R.string.physiotherapy));
        list.add(getString(R.string.cardiology));
        list.add(getString(R.string.urology));

        //Final values decalred above to simplify the code
        final SharedPreferences sharedPreferences = getSharedPreferences(PREFS,0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();


        number_r = Integer.parseInt(number);
        text_id.setText(number);

        //increment id to always have it unique (no need for 6digit limit, 999999)
        //max limit - integer max value
        number_r = sharedPreferences.getInt("number", -1);
        number_r = number_r + 1;
        number = number_r.toString();
        text_id.setText(number);

        check_Radio = false;
        //Gender Radio Button
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup);
        //radioButton1 = (RadioButton)findViewById(radioGroup1.getCheckedRadioButtonId());
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId)
                {
                    case R.id.male:
                        gender = getString(R.string.male);
                        check_Radio = true;
                        break;

                    case R.id.female:
                        gender = getString(R.string.female);
                        check_Radio = true;
                        break;
                }
            }
        });

        //spinner adapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        //add patient button
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //check
                boolean fNameTest = checkFname(editFname.getText().toString());
                if(!fNameTest){
                    Toast.makeText(AddPatientActivity.this, R.string.invalid_fname, Toast.LENGTH_SHORT)
                            .show();
                }
                //check2
                boolean lNameTest = checkLname(editLname.getText().toString());
                if(!lNameTest)
                {
                    Toast.makeText(AddPatientActivity.this, R.string.invalid_lname, Toast.LENGTH_SHORT)
                            .show();
                }

                if(!check_Radio)
                {
                    Toast.makeText(AddPatientActivity.this, R.string.invalid_radio, Toast.LENGTH_SHORT)
                            .show();
                }

                Intent intent = new Intent(AddPatientActivity.this, DenisActivity.class);

                if ((fNameTest && lNameTest && check_Radio)) {
                    String fname = editFname.getText().toString();
                    String lname = editLname.getText().toString();
                    String healthID = text_id.getText().toString();
                    String department = spinner.getSelectedItem().toString();

                    editor.putInt("number", number_r);
                    editor.commit();


                    databaseOperations = new DatabaseOperations(context);
                    sqLiteDatabase = databaseOperations.getWritableDatabase();
                    databaseOperations.putInformation(sqLiteDatabase, fname, lname, gender, healthID, department);

                    Toast.makeText(AddPatientActivity.this, "Patient Added", Toast.LENGTH_SHORT)
                            .show();

                    databaseOperations.close();

                    startActivity(intent);

                }
            }

        });
    }

    public boolean checkFname(String fname) {
        if(fname.length() >= 2 && fname.matches("[a-zA-Z]+(\\-[a-zA-Z]+)?"))
            return true;
        else
            return false;
    }

    public boolean checkLname(String lname) {
        if(lname.length() >= 2 && lname.matches("[a-zA-Z]+(\\-[a-zA-Z]+)?"))
            return true;
        else
            return false;
    }

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
                intent = new Intent(AddPatientActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
