package micc.beaconav.db.dbJSONManager;

import micc.beaconav.db.dbJSONManager.tableSchemaManager.ATableRow;

/**
 * Created by nagash on 22/01/15.
 */
public interface JSONHandler
{
    abstract void onJSONDownloadFinished(ATableRow[] result);
}
