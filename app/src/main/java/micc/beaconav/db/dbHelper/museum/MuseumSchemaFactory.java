package micc.beaconav.db.dbHelper.museum;

import micc.beaconav.db.dbJSONManager.tableSchemaManager.ColumnSchema;
import micc.beaconav.db.dbJSONManager.tableSchemaManager.Type;
import micc.beaconav.db.dbJSONManager.tableSchemaManager.TableSchemaFactory;

/**
 * Created by nagash on 21/01/15.
 */
public class MuseumSchemaFactory extends TableSchemaFactory {


    @Override
    public ColumnSchema[] generateColumnSchemas() {
        ColumnSchema[] columnSchemas = new ColumnSchema[5];
        columnSchemas[0] = new ColumnSchema(Type.STRING, "ID");
        columnSchemas[1] = new ColumnSchema(Type.STRING, "name");
        columnSchemas[2] = new ColumnSchema(Type.STRING, "descr");
        columnSchemas[3] = new ColumnSchema(Type.DOUBLE, "latitude");
        columnSchemas[4] = new ColumnSchema(Type.DOUBLE, "longitude");
        return columnSchemas;
    }

    @Override
    public String generateTableName() {
        return "museum";
    }
}
