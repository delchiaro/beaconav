package micc.beaconav.dbHelper;

import micc.beaconav.dbJSONManager.schema.TableRow;
import micc.beaconav.dbJSONManager.schema.TableSchema;

/**
 * Created by nagash on 22/01/15.
 */
public class MuseumRow extends TableRow{

    private static TableSchema schema = new MuseumSchemaFactory().generateSchema();

    public MuseumRow() {
        super(schema);
    }

    public MuseumRow(TableRow copy)
    {
        super(copy);
    }



    public final String getID(){
         return field("ID").valueString();
    }
    public final String getName()
    {
        return field("name").valueString();
    }
    public final String getDescr()
    {
        return field("descr").valueString();
    }
    public final Double getLatitude()
    {
        return field("latitude").valueDouble();
    }
    public final Double getLongitude()
    {
        return field("longitude").valueDouble();
    }
    public final String getBmpSrc()
    {
        return field("bmpSrc").valueString();
    }



}

