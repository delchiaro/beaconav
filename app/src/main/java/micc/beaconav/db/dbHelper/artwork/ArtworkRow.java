package micc.beaconav.db.dbHelper.artwork;

import android.content.Context;

import micc.beaconav.FragmentHelper;
import micc.beaconav.R;
import micc.beaconav.db.dbHelper.IArtRow;
import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.DoubleField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.FloatField;
import micc.beaconav.db.dbJSONManager.tableScheme.columnSchema.basicTypes.StringField;
import micc.beaconav.indoorEngine.drawable.Drawable;
import micc.beaconav.localization.proximity.ProximityObject;

/**
 * Created by nagash on 22/01/15.
 */
public class ArtworkRow extends TableRow<ArtworkSchema> implements IArtRow
{
    static ArtworkSchema schema = new ArtworkSchema();
    private Context context;

    public final StringField ID          = (StringField) field(schema.ID);
    public final StringField title       = (StringField) field(schema.title);
    public final StringField descr       = (StringField) field(schema.descr);
    public final FloatField x            = (FloatField) field(schema.x);
    public final FloatField y            = (FloatField) field(schema.y);


    public ArtworkRow() {
        super(schema);
        context = FragmentHelper.instance().getMainActivity();
    }


    @Override
    public String getName() {
        return title.getValue();
    }
    @Override
    public String getDescription() {
        return descr.getValue();
    }

    public String getArtistName()
    {
        return "Nome Artista";
    }

    @Override
    public int getImageId() {

        return context.getResources().getIdentifier("raphael_madonna_goldfinch", "drawable", context.getPackageName());
        //qui va presa la stringa per l'id di ogni opera
    }

}

