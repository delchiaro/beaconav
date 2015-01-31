package micc.beaconav.db.dbJSONManager.tableSchemaManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nagash on 22/01/15.
 */
public class TableSchemaManager
{
    private static TableSchemaManager istance = null;

    private Map<Class,TableSchema> schemasMap = null;

    private TableSchemaManager(){
        schemasMap = new HashMap<>();
    }

    public static TableSchemaManager getIstance()
    {
        if(istance == null){
            istance = new TableSchemaManager();
        }
        return istance;
    }


    public TableSchema getSchema(TableSchemaFactory schemaFactory)
    {
        if(schemasMap.containsKey(schemaFactory.getClass()) == false)
        {
            TableSchema newSchema = new TableSchema(schemaFactory.generateTableName(), schemaFactory.generateColumnSchemas());
            schemasMap.put(schemaFactory.getClass(), newSchema);
            return newSchema;
        }
        else return schemasMap.get(schemaFactory.getClass());
    }

}
