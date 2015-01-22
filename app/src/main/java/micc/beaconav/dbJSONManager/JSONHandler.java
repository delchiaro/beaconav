package micc.beaconav.dbJSONManager;

import java.util.ArrayList;

import micc.beaconav.dbJSONManager.schema.TableRow;

/**
 * Created by nagash on 22/01/15.
 */
public interface JSONHandler
{
    abstract void onJSONDownloadFinished(ArrayList<TableRow> result);
}
