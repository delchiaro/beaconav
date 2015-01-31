package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbJSONManager.TableSchemaFactory;
import micc.beaconav.db.dbJSONManager.schema.ColumnSchema;
import micc.beaconav.db.dbJSONManager.schema.TableSchema;
import micc.beaconav.db.dbJSONManager.schema.Type;

/**
 * Created by nagash on 21/01/15.
 */
public class RoomSchemaFactory extends TableSchemaFactory
{

    @Override
    protected TableSchema generateSchema() {
        ColumnSchema[] columnSchemas = new ColumnSchema[5];

        // todo
        return new TableSchema("museum", columnSchemas);
    }
}
