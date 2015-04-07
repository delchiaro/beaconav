package micc.beaconav.indoorEngine.databaseLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by nagash on 15/03/15.
 */
public class BuildingSqliteHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static String DB_NAME = "building";
    public static String DB_FILEPATH = "/data/data/{package_name}/databases/database.db";



    public BuildingSqliteHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        try {
            importDatabase(DB_FILEPATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Copies the database file at the specified location over the current
     * internal application database.
     * */
    public boolean importDatabase(String dbPath) throws IOException {

        // Close the SQLiteOpenHelper so it will commit the created empty
         close();
        File newDb = new File(dbPath);
        File oldDb = new File(DB_FILEPATH);
        if (newDb.exists()) {
            FileUtils.copyFile(new FileInputStream(newDb), new FileOutputStream(oldDb));
            // Access the copied database so SQLiteHelper will cache it and mark
            // it as created.
            getWritableDatabase().close();
            return true;
        }
        return false;
    }






    @Override
    public void onCreate(SQLiteDatabase db) {
        return; // questo software fa da client non scrive niente sul db dell'edificio
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        return; // questo software fa da client non scrive niente sul db dell'edificio
    }
}
