package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.FloatField;
import micc.beaconav.indoorEngine.spot.Vertex;

/**
* Created by nagash on 22/01/15.
*/
public class VertexRow extends TableRow<VertexSchema>
{


    public VertexRow(VertexSchema tableSchema) {
        super(tableSchema);
    }


    public final String getID(){
         return (String) field("ID").getValue();
    }
    public final float getX()
    {
        return (float) field("x").getValue();
    }
    public final float getY()
    {
        return ((FloatField)field("y")).getValue();
    }


    public final Vertex toVertex() {
        return new Vertex(getX(), getY());
    }

}

