package micc.beaconav.db.dbJSONManager.tableSchemaManager;

/**
 * Created by Nagash on 20/01/2015.
 */

public class ColumnField
{

    private final ColumnSchema schema;
    private Object value;


    public ColumnField(ColumnField schemaCopy)
    {
        this.schema = schemaCopy.schema;
        initValue();
    }
    public ColumnField(ColumnSchema schema)
    {
        this.schema = schema;
        initValue();
    }



    private void initValue(){
        this.value = this.type().getInitValue();
    }

    public void set(Object newVal)
    {
        this.value = newVal;
    }
    public void set(String newValue){
        Type type = this.type();
        this.value = this.type().fromString(newValue);
    }


    public final Type type(){
        return this.schema.type();
    }
    public final String columnName(){
        return this.schema.name();
    }

    public Object value(){
        return this.value;
    }

    public String valueString(){
        if(this.type() == Type.STRING)
            return (String)this.value;
        else return null;
    }
    public Integer valueInt(){
        if(this.type() == Type.INT)
            return (int)this.value;
        else return null;
    }
    public Long valueLong(){
        if(this.type() == Type.LONG)
            return (long)this.value;
        else return null;
    }
    public Float valueFloat(){
        if(this.type() == Type.FLOAT)
            return (float)this.value;
        else return null;
    }
    public Double valueDouble(){
        if(this.type() == Type.DOUBLE)
            return (double)this.value;
        else return null;
    }
    public Boolean valueBool(){
        if(this.type() == Type.BOOL)
            return (boolean)this.value;
        else return null;
    }


}
