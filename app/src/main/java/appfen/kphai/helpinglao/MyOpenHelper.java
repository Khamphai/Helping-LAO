package appfen.kphai.helpinglao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by K'Phai on 10/29/2014.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    //explicit
    private static final String DATABASE_NAME = "lao_helping.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_INFO_TABLE = "create table info_table (_id integer primary key," + " topic text, info text, pic_url text);";
    private static final String CREATE_CALL_TABLE = "create table call_table (_id integer primary key," + " Name text, TEL text);";
    private static final String CREATE_LOCATION_TABLE = "create table location_table (_id integer primary key," + " hospital_name text, lat text, lng text, Tel text);";

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_INFO_TABLE);
        sqLiteDatabase.execSQL(CREATE_CALL_TABLE);
        sqLiteDatabase.execSQL(CREATE_LOCATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}//Main Class
