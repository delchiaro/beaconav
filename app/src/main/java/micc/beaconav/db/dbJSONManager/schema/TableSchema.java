package micc.beaconav.db.dbJSONManager.schema;

import java.util.HashMap;

/**
 * Created by nagash on 21/01/15.
 */
public class TableSchema
{
    private final String name;
    private final HashMap<String, ColumnSchema> columns;


    public TableSchema(TableSchema copy) {
        this.name = copy.name;
        this.columns = new HashMap<>(copy.columns);
    }
    public TableSchema(String name, ColumnSchema... columnSchemas)
    {
        this.name = name;
        HashMap<String, ColumnSchema> initColumns = new HashMap<>(columnSchemas.length);
        for(int i = 0; i <columnSchemas.length; i++)
        {
            initColumns.put(columnSchemas[i].name(), columnSchemas[i]);
        }
        this.columns = initColumns;
    }

    public TableRow newRow()
    {
        return new TableRow(this);
    }

    public final ColumnSchema[] columns()
    {
        return columns.values().toArray(new ColumnSchema[columns.size()]);
    }
    public final HashMap<String, ColumnSchema> columnsMap()
    {
        return columns;
    }
    public final ColumnSchema column(String colName)
    {
        return columns.get(colName);
    }
    public final ColumnSchema column(int index)
    {
        return columns()[index];
    }
    public final int size()
    {
        return columns.size();
    }


    public final String getName(){
        return this.name;
    }
}
