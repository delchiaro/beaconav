package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbJSONManager.TableSchemaFactory;
import micc.beaconav.db.dbJSONManager.schema.ColumnSchema;
import micc.beaconav.db.dbJSONManager.schema.TableSchema;
import micc.beaconav.db.dbJSONManager.schema.Type;

/**
 * Created by nagash on 21/01/15.
 */
public class VertexSchemaFactory extends TableSchemaFactory
{

    @Override
    protected TableSchema generateSchema() {
        ColumnSchema[] columnSchemas = new ColumnSchema[3];
        columnSchemas[0] = new ColumnSchema(Type.STRING, "ID");
        columnSchemas[1] = new ColumnSchema(Type.FLOAT, "x");
        columnSchemas[2] = new ColumnSchema(Type.FLOAT, "y");

        return new TableSchema("Vertex", columnSchemas);
    }
}
