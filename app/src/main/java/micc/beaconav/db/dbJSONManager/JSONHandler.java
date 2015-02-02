package micc.beaconav.db.dbJSONManager;

import java.util.List;

import micc.beaconav.db.dbJSONManager.tableScheme.TableRow;

/**
 * Created by nagash on 22/01/15.
 */
public interface JSONHandler<TR extends TableRow>
{
    abstract void onJSONDownloadFinished(List<TR> result);
}
