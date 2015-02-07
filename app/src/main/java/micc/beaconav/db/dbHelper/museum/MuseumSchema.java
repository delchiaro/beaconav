package micc.beaconav.db.dbHelper.museum;

import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.TableSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.Type;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.DoubleSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.StringSchema;

/**
 * Created by nagash on 01/02/15.
 */
public class MuseumSchema extends TableSchema<MuseumRow>
{
    static final String          tableName  = "Museum";

    static final StringSchema    ID          = new StringSchema("ID");
    static final StringSchema    name        = new StringSchema("name");
    static final StringSchema    descr       = new StringSchema("descr");
    static final DoubleSchema    latitude    = new DoubleSchema("latitude");
    static final DoubleSchema    longitude   = new DoubleSchema("longitude");

    private static final ColumnSchema[] columns = new ColumnSchema[]{ ID, name, descr, latitude, longitude};

    @Override
    protected String generateTableName() {
        return tableName;
    }

    @Override
    protected ColumnSchema[] generateTableColumns() {
        return columns;
    }

    @Override
    protected MuseumRow generateRow() {
        return new MuseumRow();
    }

}
