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
public class InfoTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABLE_Info = "info_table";
    public static final String COLUMN_ID_INFO = "_id";
    public static final String COLUMN_TOPIC_INFO = "topic";
    public static final String COLUMN_INFO_INFO = "info";
    public static final String COLUMN_PIC_URL_INFO = "pic_url";


    public InfoTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }//end of constructor



    public long AddInfoToSQLite(String strtop, String strinf, String strpic) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_TOPIC_INFO, strtop);
        objContentValues.put(COLUMN_INFO_INFO, strinf);
        objContentValues.put(COLUMN_PIC_URL_INFO, strpic);

        return writeSQLite.insert(TABLE_Info, null, objContentValues);


    }//Add Info To SQLite

    public List<HashMap<String, String>> ReadAllTopic() {


        Cursor cursor = readSQLite.query(TABLE_Info, null, null, null, null, null, null);

        List<HashMap<String, String>> topic = new LinkedList<HashMap<String, String>>();

        while (cursor.moveToNext()) {
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("topic", cursor.getString(cursor.getColumnIndex(COLUMN_TOPIC_INFO)));
            item.put("info", cursor.getString(cursor.getColumnIndex(COLUMN_INFO_INFO)));
            item.put("pic_url", cursor.getString(cursor.getColumnIndex(COLUMN_PIC_URL_INFO)));

            topic.add(item);

        }


        return topic;
    }



}//Main Class
