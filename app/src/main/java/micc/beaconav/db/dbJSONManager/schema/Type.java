package micc.beaconav.db.dbJSONManager.schema;

/**
 * Created by nagash on 21/01/15.
 */
public enum Type
{
    INT, LONG,  FLOAT, DOUBLE,  STRING, BOOL;

    public static Object parseTypeValue(Type type, String value)
    {
        switch(type)
        {
            case INT: return Integer.parseInt(value);
            case LONG: return Long.parseLong(value);
            case FLOAT: return Float.parseFloat(value);
            case DOUBLE: return Double.parseDouble(value);
            case BOOL: return Boolean.parseBoolean(value);
            case STRING: return value;
        }
        return null;
    }

}

