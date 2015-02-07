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

    public static final StringSchema    ID          = new StringSchema("ID");
    public static final StringSchema    name        = new StringSchema("name");
    public static final StringSchema    descr       = new StringSchema("descr");
    public static final FloatSchema    x            = new FloatSchema("x");
    public static final FloatSchema    y            = new FloatSchema("y");
    public static final IntegerSchema  floorIndex   = new IntegerSchema("floorIndex");

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
