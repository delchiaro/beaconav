package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.FloatField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.IntegerField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.StringField;

/**
 * Created by nagash on 22/01/15.
 */
public class ConvexAreaRow extends TableRow<ConvexAreaSchema>
{
    static final ConvexAreaSchema schema = new ConvexAreaSchema();

    public final StringField ID          = (StringField) field(schema.ID);
    public final StringField ID_schema        = (StringField) field(schema.ID_room);

    public ConvexAreaRow() {
        super(schema);
    }


}

