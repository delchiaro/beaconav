package micc.beaconav.db.dbJSONManager.tableSchemaManager;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by nagash on 21/01/15.
 */
public class ATableRow
{
    private TableSchema schema;
    private HashMap<String, ColumnField> fields;

    public ATableRow(TableSchema schema)
    {
        this.schema = schema;
        fields = new HashMap<>(schema.columnsMap().values().size());

        Iterator<ColumnSchema> iter = schema.columnsMap().values().iterator();

        while(iter.hasNext())
        {
            ColumnSchema columnSchema = iter.next();
            fields.put(columnSchema.name(), columnSchema.newField());
        }
    }

    public ATableRow(ATableRow copy)
    {
        this.fields = copy.fields;
    }


    public TableSchema getSchema(){
        return schema;
    }

    public int size(){
        return fields.values().size();
    }
    public ColumnField[] fields(){
        return fields.values().toArray(new ColumnField[fields.size()]);
    }
    public ColumnField field(String name){
        return fields.get(name);
    }
    public ColumnField field(int index){
        return fields()[index];
    }





}
