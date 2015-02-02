package micc.beaconav.db.dbHelper.museum;

import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.localization.proximity.ProximityObject;

/**
 * Created by nagash on 22/01/15.
 */
public class MuseumRow extends TableRow implements ProximityObject
{
    static MuseumSchema schema = new MuseumSchema();

    public MuseumRow() {
        super(schema);
    }
    public MuseumRow(TableRow genericRow) {
        super(genericRow);
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
    public final double getLatitude()
    {
        return field("latitude").valueDouble();
    }
    public final double getLongitude()
    {
        return field("longitude").valueDouble();
    }
    public final String getBmpSrc()
    {
        return field("bmpSrc").valueString();
    }
}

