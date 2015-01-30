package micc.beaconav.db.dbJSONManager.schema;

/**
 * Created by nagash on 21/01/15.
 */
public class ColumnSchema
{

    private final Type type;
    private final String name;


    public ColumnSchema(ColumnSchema copy)
    {
        this.type = copy.type;
        this.name = copy.name;
    }
    public ColumnSchema(Type type, String name)
    {
        this.type = type;
        this.name = name;
    }


    public ColumnField newField()
    {
        return new ColumnField(this);
    }


    public final Type type(){
        return this.type;
    }
    public final String name(){
        return this.name;
    }


}
