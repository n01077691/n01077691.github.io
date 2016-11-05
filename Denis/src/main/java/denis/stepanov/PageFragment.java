/*
name: Denis Stepanov
student ID: n01077691
assignment #: 2
*/

package denis.stepanov;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String PREFS = "sharedPreferences";
    private int mPage;
    ListView listView;

    SQLiteDatabase sqLiteDatabase;
    DatabaseOperations databaseOperations;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    public static PageFragment newInstance(int page)
    {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);

        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);

        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view;

        if(mPage == 1)
        {
            view = inflater.inflate(R.layout.fragment1, container, false);

            listView = (ListView)view.findViewById(R.id.AllPatients);

            listDataAdapter = new ListDataAdapter(getActivity(), R.layout.list_view_layout);
            listView.setAdapter(listDataAdapter);

            databaseOperations = new DatabaseOperations(getActivity());
            sqLiteDatabase = databaseOperations.getReadableDatabase();

            cursor = databaseOperations.getInformation(sqLiteDatabase);

            if(cursor.moveToFirst())
            {
                do{
                    String first_name, last_name, healthID, gender, department;

                    first_name = cursor.getString(0);
                    last_name = cursor.getString(1);
                    gender = cursor.getString(2);
                    healthID = cursor.getString(3);
                    department = cursor.getString(4);

                    DataProvider dataProvider = new DataProvider(first_name, last_name, gender, healthID, department);

                    listDataAdapter.add(dataProvider);

                }while(cursor.moveToNext());
            }
        }
        else {
            view = inflater.inflate(R.layout.fragment2, container, false);

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS, 0);
            String dept = sharedPreferences.getString("spinner", "DEFAULT");
            String fname = sharedPreferences.getString("fname", "DEFAULT");
            String lname = sharedPreferences.getString("lname", "DEFAULT");
            String id = sharedPreferences.getString("id", "DEFAULT");

            String text = ("Patient: " + fname + " " + lname + "\nDepartment: " + dept);

            TextView tv1 = (TextView) view.findViewById(R.id.textView1);
            Boolean rb2 = sharedPreferences.getBoolean("radio_m", false);
            Boolean rb3 = sharedPreferences.getBoolean("radio_f", false);
            String text2 = ("Patient: " + fname + " " + lname + "\nDepartment: " + dept + "\nID: " + id);
            if(rb2){
                String gender = ("\nGender: Male");
                String final_text = (text2 + gender);
                tv1.setText(final_text);
            }else {
                String gender = ("\nGender: Female");
                String final_text = (text2 + gender);
                tv1.setText(final_text);
            }

        }
        return view;
    }
}
