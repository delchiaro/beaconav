package micc.beaconav.db.dbHelper.room;

import java.util.List;

import micc.beaconav.indoorEngine.vectorBuilding.Room;

/**
* Created by nagash on 30/01/15.
*/
public class RoomGenerator
{
    public static Room generateRoomFromVertices(List<VertexRow> vertexRows)
    {
        if(vertexRows == null ) return null;

        Room ret = new Room();
        for(int i = 0; i < vertexRows.size(); i++)
            ret.addCorner(vertexRows.get(i).toVertex(),i);

        return ret;
    }


    public static Room generateRoomFromVertices(VertexRow[] vertexRows)
    {
        if(vertexRows == null ) return null;

        Room ret = new Room();
        for(int i = 0; i < vertexRows.length; i++)
            ret.addCorner(vertexRows[i].toVertex(),i);

        return ret;
    }


}
