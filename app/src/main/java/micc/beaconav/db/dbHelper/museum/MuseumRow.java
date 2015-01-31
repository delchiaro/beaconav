package micc.beaconav.db.dbHelper.museum;

import micc.beaconav.db.dbJSONManager.tableSchemaManager.ATableRow;
import micc.beaconav.db.dbJSONManager.tableSchemaManager.TableRow;
import micc.beaconav.db.dbJSONManager.tableSchemaManager.TableSchema;
import micc.beaconav.db.dbJSONManager.tableSchemaManager.TableSchemaManager;
import micc.beaconav.localization.proximity.ProximityObject;

/**
 * Created by nagash on 22/01/15.
 */
public class MuseumRow extends TableRow<MuseumSchemaFactory> implements ProximityObject
{
    public MuseumRow(MuseumSchemaFactory tableSchemaFactory) {
        super(tableSchemaFactory);
    }

    public MuseumRow(TableRow<MuseumSchemaFactory> copy) {
        super(copy);
    }

    public MuseumRow(ATableRow genericTableRow) {
        super(genericTableRow);
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

