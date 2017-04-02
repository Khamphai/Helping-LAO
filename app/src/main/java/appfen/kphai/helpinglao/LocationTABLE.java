package appfen.kphai.helpinglao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by K'Phai on 10/29/2014.
 */
public class LocationTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABLE_Location = "location_table";
    public static final String COLUMN_ID_LOCATION = "_id";
    public static final String COLUMN_HOSPITAL_NAME_LOCATION = "hospital_name";
    public static final String COLUMN_LAT_LOCATION = "lat";
    public static final String COLUMN_LNG_LOCATION = "lng";
    public static final String COLUMN_TEL_LOCATION = "Tel";

    public LocationTABLE(Context context){

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }//Constructor

    public long AddLocationToSQLite(String strhosname, String strlat, String strlng, String strtel) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_HOSPITAL_NAME_LOCATION, strhosname);
        objContentValues.put(COLUMN_LAT_LOCATION, strlat);
        objContentValues.put(COLUMN_LNG_LOCATION, strlng);
        objContentValues.put(COLUMN_TEL_LOCATION, strtel);

        return writeSQLite.insert(TABLE_Location, null, objContentValues);

    }


}//Main Class
