package micc.beaconav.db.dbHelper;

import java.util.HashMap;

import micc.beaconav.db.dbHelper.museum.MuseumRow;
import micc.beaconav.db.dbHelper.museum.MuseumSchema;
import micc.beaconav.db.dbHelper.room.VertexSchema;
import micc.beaconav.db.dbJSONManager.JSONDownloader;

/**
 * Created by nagash on 30/01/15.
 */
public class DbManager
{


    static MuseumSchema museumSchema = new MuseumSchema();

    static private final String museumJSONLink = "http://trinity.micc.unifi.it/museumapp/JSON_Museums.php";
    static public final JSONDownloader<MuseumRow, MuseumSchema> museumDownloader =
            new JSONDownloader<MuseumRow, MuseumSchema>(museumSchema, museumJSONLink);




    static private String vertexJSONLink = "http://trinity.micc.unifi.it/museumapp/JSON_RoomVertices.php";
    static private HashMap<Integer, JSONDownloader> vertexFromRoomDownloaders = new HashMap<>();

    static public JSONDownloader getVertexFromRoomDownloader(int roomID)
    {
        JSONDownloader dl;
        if(vertexFromRoomDownloaders.get(roomID) == null)
        {
            //vertexJSONLink += "id=" + roomID; // TODO: implementare id in php
            dl = new JSONDownloader(new VertexSchema(), vertexJSONLink);
            vertexFromRoomDownloaders.put(roomID, dl);
        }
        else dl = vertexFromRoomDownloaders.get(roomID);
        return dl;
    }


}
