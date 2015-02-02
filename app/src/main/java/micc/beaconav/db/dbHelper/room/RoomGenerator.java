package micc.beaconav.db.dbHelper.room;

import java.util.List;

import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.indoorEngine.building.Room;

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

//
//    public static Room generateRoomFromVertices(TableRow[] vertexRows)
//    {
//        if(vertexRows == null || vertexRows.length <= 0) return null;
//
//        else if( new VertexSchemaFactory().isSameSchema(vertexRows[0]) )
//        {
//            Room ret = new Room();
//            for(int i = 0; i < vertexRows.length; i++)
//            {
//                VertexRow vertexRow =  new VertexRow(vertexRows[i]);
//                ret.addCorner(vertexRow.toVertex(), i);
//            }
//            return ret;
//        }
//        else return null;
//    }


}
