package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbJSONManager.schema.TableRow;
import micc.beaconav.db.dbJSONManager.schema.TableSchema;
import micc.beaconav.indoorEngine.building.Room;
import micc.beaconav.localization.proximity.ProximityObject;

/**
 * Created by nagash on 22/01/15.
 */
public class RoomRow extends TableRow
{

    private static TableSchema schema = new RoomSchemaFactory().generateSchema();

    public RoomRow() {
        super(schema);
    }

    public RoomRow(TableRow copy)
    {
        super(copy);
    }


    //todo


}

