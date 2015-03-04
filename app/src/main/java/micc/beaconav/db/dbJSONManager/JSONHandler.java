package micc.beaconav.db.dbJSONManager;

import java.util.List;

import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public interface JSONHandler<TR extends TableRow>
{
    abstract void onJSONDownloadFinished(TR[] result);
}
