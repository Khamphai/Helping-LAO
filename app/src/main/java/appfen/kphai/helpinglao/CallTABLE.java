package appfen.kphai.helpinglao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by K'Phai on 10/29/2014.
 */
public class CallTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABLE_Call = "call_table";
    public static final String COLUMN_ID_CALL = "_id";
    public static final String COLUMN_NAME_CALL = "Name";
    public static final String COLUMN_TEL_CALL = "TEL";

    public CallTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }//Constructor

    public long AddCallToSQLite(String strName, String strTEL) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_NAME_CALL, strName);
        objContentValues.put(COLUMN_TEL_CALL, strTEL);
        return writeSQLite.insert(TABLE_Call, null, objContentValues);

    }


    public List<HashMap<String, String>> ReadAllCall() {

        Cursor cursor = readSQLite.query(TABLE_Call, null, null, null, null, null, null);

        List<HashMap<String, String>> call = new LinkedList<HashMap<String, String>>();

        while (cursor.moveToFirst()) {
            HashMap<String, String> item1 = new HashMap<String, String>();
            item1.put("name_call", cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CALL)));
            item1.put("number_call", cursor.getString(cursor.getColumnIndex(COLUMN_TEL_CALL)));

            call.add(item1);
        }

        return call;
    }


}// Main Class
