package micc.beaconav.db.dbHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import micc.beaconav.db.dbHelper.museum.MuseumSchemaFactory;
import micc.beaconav.db.dbHelper.room.VertexRow;
import micc.beaconav.db.dbHelper.room.VertexSchemaFactory;
import micc.beaconav.db.dbJSONManager.JSONDownloader;
import micc.beaconav.indoorEngine.building.Room;

/**
 * Created by nagash on 30/01/15.
 */
public class DbManager
{

    static private final String museumJSONLink = "http://trinity.micc.unifi.it/museumapp/JSON_Museums.php";
    static public final JSONDownloader museumDownloader =
            new JSONDownloader(new MuseumSchemaFactory().getSchema(), museumJSONLink);




    static private String vertexJSONLink = "http://trinity.micc.unifi.it/museumapp/JSON_RoomVertices.php";
    static private HashMap<Integer, JSONDownloader> vertexFromRoomDownloaders = new HashMap<>();

    static public JSONDownloader getVertexFromRoomDownloader(int roomID)
    {
        JSONDownloader dl;
        if(vertexFromRoomDownloaders.get(roomID) == null)
        {
            //vertexJSONLink += "id=" + roomID; // TODO: implementare id in php
            dl = new JSONDownloader(new VertexSchemaFactory().getSchema(), vertexJSONLink);
            vertexFromRoomDownloaders.put(roomID, dl);
        }
        else dl = vertexFromRoomDownloaders.get(roomID);
        return dl;
    }


}
