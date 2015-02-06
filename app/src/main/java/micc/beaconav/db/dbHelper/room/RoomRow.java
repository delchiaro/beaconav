package micc.beaconav.db.dbHelper.room;

import micc.beaconav.db.dbHelper.IArtRow;
import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.DoubleField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.FloatField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.IntegerField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.StringField;
import micc.beaconav.localization.proximity.ProximityObject;

/**
 * Created by nagash on 22/01/15.
 */
public class RoomRow extends TableRow<RoomSchema>
{
    static RoomSchema schema = new RoomSchema();

    StringField ID          = (StringField) field(schema.ID.name());
    StringField name        = (StringField) field(schema.name.name());
    StringField descr       = (StringField) field(schema.descr.name());
    FloatField  y           = (FloatField)  field(schema.x.name());
    FloatField  x           = (FloatField)  field(schema.y.name());
    IntegerField floorIndex = (IntegerField) field(schema.floorIndex.name());


    public RoomRow() {
        super(schema);
        schema.ID.newField();
        schema.name.newField();
        schema.descr.newField();
        FloatField x = schema.x.newField();

    }



    public final String getID(){
         return ID.getValue();
    }
    public final String getName() {
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

