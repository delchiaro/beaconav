package micc.beaconav.gui.customList;

/**
 * Created by Mr_Holmes on 22/01/15.
 */
public class ArtListItem {

    private String _description;
    private int _imageId;

    public ArtListItem(int _imageId, String _description) {
        this._imageId = _imageId;
        this._description = _description;
    }

    public String getDescription()
    {
        return this._description;
    }

    public void setDescription(String des)
    {
        this._description = des;
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
