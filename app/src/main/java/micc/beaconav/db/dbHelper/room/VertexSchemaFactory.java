package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbJSONManager.tableSchemaManager.TableSchemaFactory;
import micc.beaconav.db.dbJSONManager.tableSchemaManager.ColumnSchema;
import micc.beaconav.db.dbJSONManager.tableSchemaManager.Type;

/**
 * Created by nagash on 21/01/15.
 */
public class VertexSchemaFactory extends TableSchemaFactory
{

    @Override
    public ColumnSchema[] generateColumnSchemas() {
        ColumnSchema[] columnSchemas = new ColumnSchema[3];
        columnSchemas[0] = new ColumnSchema(Type.STRING, "ID");
        columnSchemas[1] = new ColumnSchema(Type.FLOAT, "x");
        columnSchemas[2] = new ColumnSchema(Type.FLOAT, "y");
        return columnSchemas;
    }

    @Override
    public String generateTableName() {
        return "Vertex";
    }

}
