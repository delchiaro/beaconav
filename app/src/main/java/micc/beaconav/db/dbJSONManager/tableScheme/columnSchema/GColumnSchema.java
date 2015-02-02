package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema;

/**
 * Created by nagash on 21/01/15.
 */
public abstract class GColumnSchema<T>
{


    private final Class type;
    private final String name;


    public GColumnSchema(String name) {
        this.name = name;
        this.type = generateType();
    }


    public GColumnField<T> newField() {
        return generateField().initSchema(this);
    }
    protected abstract Class generateType();
    protected abstract GColumnField<T> generateField();



    public final Class type(){
        return this.type;
    }
    public final String name(){
        return this.name;
    }

}
