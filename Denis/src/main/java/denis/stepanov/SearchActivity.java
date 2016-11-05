/*
name: Denis Stepanov
student ID: n01077691
assignment #: 2
*/

package denis.stepanov;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    Spinner spinner2;
    EditText edit_id12;
    Button button_id;
    Button button_dept;
    TextView tv1;
    TextView tv2;
    TextView display_fname, display_lname, display_gender, display_dept, display_id;
    DatabaseOperations databaseOperations;
    SQLiteDatabase sqLiteDatabase;
    String search_id;
    TextView fname,lname,gender,id,dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edit_id12 = (EditText) findViewById(R.id.edit_text_id);
        Button button_id = (Button) findViewById(R.id.button_search_by_id);
        Button button_dept = (Button) findViewById(R.id.button_filter);
        TextView tv1 = (TextView) findViewById(R.id.patient_id_text);
        TextView tv2 = (TextView) findViewById(R.id.patient_dept_text);

        display_fname = (TextView)findViewById(R.id.display_fname);
        display_lname = (TextView)findViewById(R.id.display_lname);
        display_gender = (TextView)findViewById(R.id.display_gender);
        display_dept = (TextView)findViewById(R.id.display_dept);
        display_id = (TextView)findViewById(R.id.display_id);

        display_fname.setVisibility(View.GONE);
        display_lname.setVisibility(View.GONE);
        display_gender.setVisibility(View.GONE);
        display_dept.setVisibility(View.GONE);
        display_id.setVisibility(View.GONE);

        fname = (TextView)findViewById(R.id.listviewfirstname_search);
        lname = (TextView)findViewById(R.id.listviewlastname_search);
        gender = (TextView)findViewById(R.id.listviewgender_search);
        id = (TextView)findViewById(R.id.listviewid_search);
        dept = (TextView)findViewById(R.id.listviewdept_search);

        fname.setVisibility(View.GONE);
        lname.setVisibility(View.GONE);
        gender.setVisibility(View.GONE);
        id.setVisibility(View.GONE);
        dept.setVisibility(View.GONE);


        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/merri.ttf");
        tv1.setTypeface(face);
        tv2.setTypeface(face);

        //spinner selections
        spinner2 = (Spinner) findViewById(R.id.spinner_search);
        List<String> list = new ArrayList<String>();
        list.add(getString(R.string.audiology));
        list.add(getString(R.string.physiotherapy));
        list.add(getString(R.string.cardiology));
        list.add(getString(R.string.urology));

        //spinner adapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }



    public void searchContact(View view){
        search_id = edit_id12.getText().toString();
        databaseOperations = new DatabaseOperations(getApplicationContext());
        sqLiteDatabase = databaseOperations.getReadableDatabase();
        Cursor cursor = databaseOperations.getContact(search_id,sqLiteDatabase);

        //hide keyboard
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        if(cursor.moveToFirst()){
            String FNAME = cursor.getString(0);
            String LNAME = cursor.getString(1);
            String GENDER = cursor.getString(2);
            String ID = cursor.getString(3);
            String DEPT = cursor.getString(4);

            display_fname.setText(FNAME);
            display_lname.setText(LNAME);
            display_gender.setText(GENDER);
            display_id.setText(ID);
            display_dept.setText(DEPT);

            display_fname.setVisibility(View.VISIBLE);
            display_lname.setVisibility(View.VISIBLE);
            display_gender.setVisibility(View.VISIBLE);
            display_id.setVisibility(View.VISIBLE);
            display_dept.setVisibility(View.VISIBLE);

            fname.setVisibility(View.VISIBLE);
            lname.setVisibility(View.VISIBLE);
            gender.setVisibility(View.VISIBLE);
            id.setVisibility(View.VISIBLE);
            dept.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(SearchActivity.this, R.string.invalid_row_id, Toast.LENGTH_SHORT)
                    .show();
        }

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
                Toast.makeText(SearchActivity.this, getString(R.string.alt_search), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
