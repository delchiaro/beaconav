package micc.beaconav.db.dbHelper.artwork;

import micc.beaconav.db.dbHelper.IArtRow;
import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.FloatField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.StringField;

/**
 * Created by nagash on 22/01/15.
 */
public class ArtworkRow extends TableRow<ArtworkSchema> implements IArtRow
{
    static ArtworkSchema schema = new ArtworkSchema();

    public final StringField ID          = (StringField) field(schema.ID);
    public final StringField title       = (StringField) field(schema.title);
    public final StringField descr       = (StringField) field(schema.descr);
    public final FloatField x            = (FloatField) field(schema.x);
    public final FloatField y            = (FloatField) field(schema.y);


    public ArtworkRow() {
        super(schema);
    }


    @Override
    public String getName() {
        return title.getValue();
    }
    @Override
    public String getDescription() {
        return descr.getValue();
    }

    @Override
    public int getImageId() {
        return 0;
        //TODO: setter oppure meglio, ritornare un bitmap direttamente e fare un downloader
        // del bitmap.
    }
}

