package micc.beaconav.gui.customList;

/**
 * Created by Mr_Holmes on 22/01/15.
 */
public class ArtListItem {

    private String _name;
    private String _description;
    private int _imageId;

    public ArtListItem(int imageId, String name, String description) {
        this._imageId = imageId;
        this._name = name;
        this._description = description;
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
