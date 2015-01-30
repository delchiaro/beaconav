package micc.beaconav.db.dbJSONManager;

import micc.beaconav.db.dbJSONManager.schema.TableSchema;

/**
 * Created by nagash on 22/01/15.
 */
public abstract class TableSchemaFactory
{

    private static TableSchema schema = null;

    // design pattern tipo singleton ma non static
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
