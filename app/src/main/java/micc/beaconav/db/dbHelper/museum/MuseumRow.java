package micc.beaconav.db.dbHelper.museum;

import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.DoubleField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.StringField;
import micc.beaconav.localization.proximity.ProximityObject;

/**
 * Created by nagash on 22/01/15.
 */
public class MuseumRow extends TableRow<MuseumSchema> implements ProximityObject
{
    static MuseumSchema schema = new MuseumSchema();

    StringField ID          = (StringField) field(schema.ID.name());
    StringField name        = (StringField) field(schema.name.name());
    StringField descr       = (StringField) field(schema.descr.name());
    DoubleField latitude    = (DoubleField) field(schema.latitude.name());
    DoubleField longitude   = (DoubleField) field(schema.longitude.name());


    public MuseumRow() {
        super(schema);
    }



    public final String getID(){
         return ID.getValue();
    }
    public final String getName()
    {
        return name.getValue();
    }
    public final String getDescr()
    {
        return descr.getValue();
    }
    public final Double getLatitude() { return latitude.getValue(); }
    public final Double getLongitude()
    {
        return longitude.getValue();
    }
}

