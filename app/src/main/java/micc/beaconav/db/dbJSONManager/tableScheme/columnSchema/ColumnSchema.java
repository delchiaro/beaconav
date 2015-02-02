package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema;

/**
 * Created by nagash on 21/01/15.
 */
public abstract class ColumnSchema
{

    private final Class type;
    private final String name;


    public ColumnSchema(String name) {
        this.name = name;
        this.type = generateType();
    }


    public ColumnField newField() {
        return generateField().initSchema(this);
    }
    protected abstract Class generateType();
    protected abstract ColumnField generateField();



    public final Class type(){
        return this.type;
    }
    public final String name(){
        return this.name;
    }


}
