package micc.beaconav.db.dbJSONManager.tableSchemaManager;

/**
 * Created by nagash on 31/01/15.
 */
public abstract class TableSchemaFactory {

    public abstract ColumnSchema[] generateColumnSchemas();
    public abstract String generateTableName();


    public final TableSchema getSchema(){
        return TableSchemaManager.getIstance().getSchema(this);
    }

    public final boolean isSameSchema(ATableRow rowToCheck)
    {
        if(rowToCheck.getSchema() == this.getSchema()) return true;
        else return false;
    }
}
