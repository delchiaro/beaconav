package micc.beaconav.dbJSONManager;

import micc.beaconav.dbJSONManager.schema.TableRow;
import micc.beaconav.dbJSONManager.schema.TableSchema;

/**
 * Created by nagash on 22/01/15.
 */
public abstract class TableSchemaFactory
{

    private static TableSchema schema = null;
    public TableSchema getSchema()
    {
        if(TableSchemaFactory.schema == null)
        {
            TableSchemaFactory.schema = this.generateSchema();
            return TableSchemaFactory.schema;
        }
        else return TableSchemaFactory.schema;
    }

    protected abstract TableSchema generateSchema();




}
