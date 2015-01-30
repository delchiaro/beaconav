package micc.beaconav.db.dbHelper;

import java.util.ArrayList;

import micc.beaconav.db.dbJSONManager.JSONDownloader;

import micc.beaconav.db.dbJSONManager.JSONHandler;
import micc.beaconav.db.dbJSONManager.schema.TableRow;

/**
 * Created by nagash on 30/01/15.
 */
public class DbManager
{

    static private final String museumJSONLink = "http://whitelight.altervista.org/JSONTest.php";
    static public final JSONDownloader museumDownloader =
            new JSONDownloader(new MuseumSchemaFactory().getSchema(), museumJSONLink);

}
