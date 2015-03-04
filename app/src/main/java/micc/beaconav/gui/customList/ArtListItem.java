package micc.beaconav.gui.customList;

import micc.beaconav.R;
import micc.beaconav.db.dbHelper.IArtRow;
import micc.beaconav.db.dbHelper.museum.MuseumRow;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public class ArtListItem {

    private IArtRow _row;
    private String _name;
    private String _description;

    public ArtListItem(IArtRow row) {
        this._row = row;
        this._name = row.getName();
        this._description = row.getDescription();
    }

    public IArtRow getRow() {
        return _row;
    }


    public void setRow(IArtRow row) {
        this._row = row;
    }

    public String getName()
    {
        return this._name;
    }

    public void setName(String name)
    {
        this._name = name;
    }

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

}
