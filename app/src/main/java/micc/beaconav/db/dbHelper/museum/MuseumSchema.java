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
public class MuseumSchema extends TableSchema
{

    MuseumSchema() {
        super();
    }

    @Override
    protected String generateTableName() {
        return "Museum";
    }

    @Override
    protected ColumnSchema[] generateTableColumns() {
        ColumnSchema[] columnSchemas = new ColumnSchema[5];
        columnSchemas[0] = new StringSchema("ID");
        columnSchemas[1] = new StringSchema("name");
        columnSchemas[2] = new StringSchema("descr");
        columnSchemas[3] = new DoubleSchema("latitude");
        columnSchemas[4] = new DoubleSchema("longitude");
        return columnSchemas;
    }

    @Override
    protected TableRow generateRow() {
        return new MuseumRow();
    }

}
