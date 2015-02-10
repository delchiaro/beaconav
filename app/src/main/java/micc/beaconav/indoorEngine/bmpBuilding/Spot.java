package micc.beaconav.indoorEngine.bmpBuilding;


import android.graphics.PointF;


/**
 * 
 */
public class Spot
{

    protected PointF position;

    //protected IndoorPosition _indoorPosition;
    private SpotManager _containerSpotManager = null;

    public Spot(PointF position_offset_in_floor){
        this.position = position_offset_in_floor;
    }
    public Spot(float x_area_offset, float y_area_offset){
        position = new PointF(x_area_offset, y_area_offset);
    }


    public float getX(){ return position.x; }
    public float getY(){ return position.y; }


   // public Position getPosition(){ return this._indoorPosition.getPosition();}
   // public IndoorPosition getIndoorPosition(){ return _indoorPosition; }



    public Building getBuildingContainer() { return getFloorContainer().getContainerBuilding(); }

    public Floor getFloorContainer() { return getContainerManager().getContainerFloor(); }



    public void setX(float x){
        this.position.x = x;
    }
    public void setY(float y){
        this.position.y = y;
    }







    //gestione associazione bidirezionale SpotManager - Spot

    public final SpotManager getContainerManager(){
        return this._containerSpotManager;
    }
    final Spot setContainerManager(SpotManager manager) {
        this._containerSpotManager = manager;
        return this;
    }
    final void  unsetContainerManager() {
        this._containerSpotManager = null;
    }
    public final void removeFromContainerManager() {
        if(this._containerSpotManager!=null)
            this._containerSpotManager.removeSpot(this);
    }



}