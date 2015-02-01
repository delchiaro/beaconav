package micc.beaconav.gui.customList;

import micc.beaconav.R;
import micc.beaconav.db.dbHelper.museum.MuseumRow;

/**
 * Created by Mr_Holmes on 22/01/15.
 */
public class ArtListItem {

    private MuseumRow _row;
    private String _name;
    private String _description;
    private int _imageId = R.drawable.graphic;

    public ArtListItem(MuseumRow row) {
        this._row = row;
        this._name = row.getName();
        this._description = row.getDescr();
    }

    public MuseumRow getRow() {
        return _row;
    }

    public void setRow(MuseumRow row) {
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

    public int getImageId()
    {
        return this._imageId;
    }

    public void setImageId(int id)
    {
        this._imageId = id;
    }
}
