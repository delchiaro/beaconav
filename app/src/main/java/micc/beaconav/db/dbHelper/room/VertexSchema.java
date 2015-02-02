package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbJSONManager.tableScheme.TableSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.DoubleSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.FloatSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.StringSchema;

/**
 * Created by nagash on 02/02/15.
 */
public class VertexSchema extends TableSchema<VertexRow> {


    static final String          tableName  = "Vertex";

    static final StringSchema   ID  = new StringSchema("ID");
    static final FloatSchema    x   = new FloatSchema("x");
    static final FloatSchema    y   = new FloatSchema("y");

    private static final ColumnSchema[] columns = new ColumnSchema[]{ ID, x, y};


    @Override
    protected String generateTableName() {
        return tableName;
    }

    @Override
    protected ColumnSchema[] generateTableColumns() {
        return columns;
    }

    @Override
    protected VertexRow generateRow() {
        return new VertexRow(this);
    }
}
