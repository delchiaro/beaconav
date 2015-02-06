package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbJSONManager.tableScheme.TableSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.DoubleSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.FloatSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.IntegerSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.StringSchema;

/**
 * Created by nagash on 01/02/15.
 */
public class RoomSchema extends TableSchema<RoomRow>
{
    static final String          tableName  = "Room";

    static final StringSchema    ID          = new StringSchema("ID");
    static final StringSchema    name        = new StringSchema("name");
    static final StringSchema    descr       = new StringSchema("descr");
    static final FloatSchema    x            = new FloatSchema("x");
    static final FloatSchema    y            = new FloatSchema("y");
    static final IntegerSchema  floorIndex   = new IntegerSchema("floorIndex");


    private static final ColumnSchema[] columns = new ColumnSchema[]{ ID, name, descr, x, y, floorIndex};

    @Override
    protected String generateTableName() {
        return tableName;
    }

    @Override
    protected ColumnSchema[] generateTableColumns() {

        return columns;
    }

    @Override
    protected RoomRow generateRow() {
        return new RoomRow();
    }

}
