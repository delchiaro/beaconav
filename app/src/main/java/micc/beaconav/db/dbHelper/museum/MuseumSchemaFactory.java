package micc.beaconav.db.dbHelper.museum;

import micc.beaconav.db.dbJSONManager.schema.ColumnSchema;
import micc.beaconav.db.dbJSONManager.TableSchemaFactory;
import micc.beaconav.db.dbJSONManager.schema.TableSchema;
import micc.beaconav.db.dbJSONManager.schema.Type;

/**
 * Created by nagash on 21/01/15.
 */
public class MuseumSchemaFactory extends TableSchemaFactory
{

    @Override
    protected TableSchema generateSchema() {
        ColumnSchema[] columnSchemas = new ColumnSchema[5];
        columnSchemas[0] = new ColumnSchema(Type.STRING, "ID");
        columnSchemas[1] = new ColumnSchema(Type.STRING, "name");
        columnSchemas[2] = new ColumnSchema(Type.STRING, "descr");
        columnSchemas[3] = new ColumnSchema(Type.DOUBLE, "latitude");
        columnSchemas[4] = new ColumnSchema(Type.DOUBLE, "longitude");

        //"ID":"uffizi","latitude":"43.767829","longitude":"11.255251","width":"900","height":"600","bmpSrc":"","name":"Galleria degli Uffizi","descr":"Galleria degli Uggizi"},
        return new TableSchema("museum", columnSchemas);

    }
}
