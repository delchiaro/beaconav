package micc.beaconav.db.dbJSONManager.tableSchemaManager;

/**
 * Created by nagash on 31/01/15.
 */
public class TableRow<F extends TableSchemaFactory> extends ATableRow
{
    private static TableSchema schema = null;

    public TableRow(F tableSchemaFactory){
        super(schema==null? (schema=tableSchemaFactory.getSchema()) : schema);
    }
    public TableRow(TableRow<F> copy) {
        super(copy);
    }

    public TableRow(ATableRow genericTableRow) {
        super(genericTableRow);
    }


    @Override
    public final TableSchema getSchema() {
        return schema;
    }

    public static final TableSchema schema(){
        return schema;
    }


}
