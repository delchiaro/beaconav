package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbJSONManager.schema.TableRow;
import micc.beaconav.indoorEngine.building.Room;

/**
 * Created by nagash on 30/01/15.
 */
public class RoomGenerator
{
    public static Room generateRoomFromVertices(VertexRow[] vertexRows)
    {
        if(vertexRows == null ) return null;

        Room ret = new Room();
        for(int i = 0; i < vertexRows.length; i++)
            ret.addCorner(vertexRows[i].toVertex(),i);

        return ret;
    }

    public static Room generateRoomFromVertices(TableRow[] vertexRows)
    {
        if(vertexRows == null || vertexRows.length <= 0) return null;

        else if( vertexRows[0].getSchema() == new RoomSchemaFactory().getSchema() )
        {
            return generateRoomFromVertices((VertexRow[])vertexRows);
        }
        else return null;
    }
}
