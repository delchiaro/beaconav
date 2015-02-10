package micc.beaconav.db.dbHelper.museum;

import micc.beaconav.db.dbHelper.IArtRow;
import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.DoubleField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.FloatField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.StringField;
import micc.beaconav.localization.proximity.ProximityObject;

/**
 * Created by nagash on 22/01/15.
 */
public class MuseumRow extends TableRow<MuseumSchema> implements ProximityObject, IArtRow
{
    static MuseumSchema schema = new MuseumSchema();

    public final StringField ID          = (StringField) field(schema.ID);
    public final StringField name        = (StringField) field(schema.name);
    public final StringField descr       = (StringField) field(schema.descr);
    public final DoubleField latitude    = (DoubleField) field(schema.latitude);
    public final DoubleField longitude   = (DoubleField) field(schema.longitude);
    public final FloatField width        = (FloatField)  field(schema.width);
    public final FloatField height       = (FloatField)  field(schema.height);


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





    @Override
    public String getDescription() {
        return getDescr();
    }

    @Override
    public int getImageId() {
        return 0;
        //TODO: setter oppure meglio, ritornare un bitmap direttamente e fare un downloader
        // del bitmap.
    }
}

