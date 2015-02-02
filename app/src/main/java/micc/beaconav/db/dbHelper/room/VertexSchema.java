package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbJSONManager.tableScheme.TableSchema;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by nagash on 02/02/15.
 */
public class VertexSchema extends TableSchema<VertexRow> {


    @Override
    protected String generateTableName() {
        return "Vertex";
    }

    @Override
    protected ColumnSchema[] generateTableColumns() {
        return new ColumnSchema[0];
    }

    @Override
    protected VertexRow generateRow() {
        return new VertexRow(this);
    }
}
