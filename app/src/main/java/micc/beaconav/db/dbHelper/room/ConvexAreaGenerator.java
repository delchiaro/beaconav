package micc.beaconav.db.dbHelper.room;

import micc.beaconav.indoorEngine.vectorBuilding.ConvexArea;
import micc.beaconav.indoorEngine.vectorBuilding.Room;

/**
 * Created by nagash on 06/02/15.
 */
public class ConvexAreaGenerator {



    public static ConvexArea generateConvexAreaFromVertices(Room containerRoom, VertexRow[] vertexRows, SpotRow[] spotRows)
    {
        if(vertexRows == null ) return null;

        ConvexArea ret = new ConvexArea();
        containerRoom.addConvexArea(ret);
        for(int i = 0; i < vertexRows.length; i++)
            ret.addCorner(vertexRows[i].toVertex(),i);

        for(int i = 0; i < spotRows.length; i++)
            ret.addSpot(spotRows[i].toSpot());

        return ret;
    }


}
