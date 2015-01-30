package micc.beaconav.db.dbJSONManager;

import java.util.ArrayList;

import micc.beaconav.db.dbJSONManager.schema.TableRow;

/**
 * Created by nagash on 22/01/15.
 */
public interface JSONHandler
{
    abstract void onJSONDownloadFinished(TableRow[] result);
}
