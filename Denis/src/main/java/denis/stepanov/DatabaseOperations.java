/*
name: Denis Stepanov
student ID: n01077691
assignment #: 2
*/

package denis.stepanov;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperations extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDB";
    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TableData.PatientInfo.TABLE_NAME + "(" + TableData.PatientInfo.FIRST_NAME
                    + " TEXT," + TableData.PatientInfo.LAST_NAME + " TEXT," + TableData.PatientInfo.GENDER
                    + " TEXT," + TableData.PatientInfo.HEALTH_ID + " TEXT," + TableData.PatientInfo.DEPARTMENT + " TEXT);";

    public DatabaseOperations(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Operations", "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);
        Log.d("Database Operations", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public Cursor getContact(String healthID, SQLiteDatabase sqLiteDatabase){
        String[] columnNames = {TableData.PatientInfo.FIRST_NAME, TableData.PatientInfo.LAST_NAME,
                TableData.PatientInfo.GENDER, TableData.PatientInfo.HEALTH_ID,TableData.PatientInfo.DEPARTMENT};
        String selection = TableData.PatientInfo.HEALTH_ID+" LIKE ?";
        String[] selection_args = {healthID};
        Cursor cursor = sqLiteDatabase.query(TableData.PatientInfo.TABLE_NAME,columnNames,selection,selection_args,null,null,null);

        return cursor;
    }

    public void putInformation(SQLiteDatabase db, String fname, String lname, String gender,
                               String healthID, String department)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.PatientInfo.FIRST_NAME, fname);
        contentValues.put(TableData.PatientInfo.LAST_NAME, lname);
        contentValues.put(TableData.PatientInfo.GENDER, gender);
        contentValues.put(TableData.PatientInfo.HEALTH_ID, healthID);
        contentValues.put(TableData.PatientInfo.DEPARTMENT, department);

        db.insert(TableData.PatientInfo.TABLE_NAME, null, contentValues);
        Log.d("Database Operations", "one row inserted");
    }

    public Cursor getInformation(SQLiteDatabase db)
    {
        Cursor cursor;

        String[] columnNames = {TableData.PatientInfo.FIRST_NAME, TableData.PatientInfo.LAST_NAME,
                TableData.PatientInfo.GENDER, TableData.PatientInfo.HEALTH_ID,TableData.PatientInfo.DEPARTMENT};

        cursor = db.query(TableData.PatientInfo.TABLE_NAME, columnNames, null, null, null, null, null);

        return cursor;
    }


}
