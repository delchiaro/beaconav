package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbHelper.IArtRow;
import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.DoubleField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.FloatField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.IntegerField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.StringField;
import micc.beaconav.localization.proximity.ProximityObject;

/**
 * Created by nagash on 22/01/15.
 */
public class RoomRow extends TableRow<RoomSchema>
{
    static final RoomSchema schema = new RoomSchema();

    public final StringField ID          = (StringField) field(schema.ID);
    public final StringField name        = (StringField) field(schema.name);
    public final StringField descr       = (StringField) field(schema.descr);
    public final FloatField  x           = (FloatField) field(schema.x);
    public final FloatField  y           = (FloatField) field(schema.y);
    public final IntegerField floorIndex = (IntegerField) field(schema.floorIndex);


    public RoomRow() {
        super(schema);
    }


}

