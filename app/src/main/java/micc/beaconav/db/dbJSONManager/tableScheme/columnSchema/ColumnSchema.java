package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema;

/**
 * Created by nagash on 21/01/15.
 */
public abstract class ColumnSchema<T>
{

    private final String name;


    public ColumnSchema(String name) {
        this.name = name;
    }


    public ColumnField<T> newField() {
        return generateField().initSchema(this);
    }

    protected abstract ColumnField<T> generateField();



    public final String name(){
        return this.name;
    }

}
