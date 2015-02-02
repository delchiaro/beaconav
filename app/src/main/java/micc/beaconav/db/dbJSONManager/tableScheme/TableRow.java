package micc.beaconav.db.dbJSONManager.tableScheme;

import java.util.HashMap;
import java.util.Iterator;

import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.ColumnSchema;

/**
 * Created by nagash on 21/01/15.
 */
public class TableRow<TS extends TableSchema>
{
    private final TS schema;
    private final HashMap<String, ColumnField> fields;

    public TableRow(TS schema)
    {
        this.schema = schema;
        fields = new HashMap<>(schema.getColumnsMap().values().size());

        Iterator<ColumnSchema> iter = schema.getColumnsMap().values().iterator();

        while(iter.hasNext())
        {
            ColumnSchema columnSchema = iter.next();
            fields.put(columnSchema.name(), columnSchema.newField());
        }
    }



    public TS getSchema(){
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
