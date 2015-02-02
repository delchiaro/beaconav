package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.indoorEngine.spot.Vertex;

/**
 * Created by nagash on 22/01/15.
 */
public class VertexRow extends TableRow<VertexSchemaFactory>
{


    public VertexRow(VertexSchemaFactory tableSchemaFactory) {
        super(tableSchemaFactory);
    }

    public VertexRow(TableRow<VertexSchemaFactory> copy) {
        super(copy);
    }

    public VertexRow(TableRow genericTableRow) {
        super(genericTableRow);
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

