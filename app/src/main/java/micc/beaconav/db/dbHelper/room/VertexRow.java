package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbJSONManager.schema.TableRow;
import micc.beaconav.db.dbJSONManager.schema.TableSchema;
import micc.beaconav.indoorEngine.spot.Vertex;
import micc.beaconav.localization.proximity.ProximityObject;

/**
 * Created by nagash on 22/01/15.
 */
public class VertexRow extends TableRow
{

    private static TableSchema schema = new VertexSchemaFactory().generateSchema();

    public VertexRow() {
        super(schema);
    }

    public VertexRow(TableRow copy)
    {
        super(copy);
    }



    public final String getID(){
         return field("ID").valueString();
    }
    public final float getX()
    {
        return field("x").valueFloat();
    }
    public final float getY()
    {
        return field("y").valueFloat();
    }


    public final Vertex toVertex()
    {
        return new Vertex(getX(), getY());
    }
}

