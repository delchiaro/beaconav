package micc.beaconav.db.dbJSONManager.schema;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by nagash on 21/01/15.
 */
public class TableRow
{
    private final TableSchema schema;
    private HashMap<String, ColumnField> fields;

    public TableRow(TableSchema schema)
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

    public TableRow(TableRow copy)
    {
        this.schema = copy.schema;
        this.fields = copy.fields;
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
