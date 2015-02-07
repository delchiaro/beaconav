package micc.beaconav.indoorEngine.building;


import android.graphics.PointF;

/**
 * 
 */
public class Spot implements ISpot
{

    protected PointF areaOffsetPosition;

    //protected IndoorPosition _indoorPosition;
    private ConvexArea _containerConvexArea = null;


    public Spot(PointF position_offset_in_Area){
        this.areaOffsetPosition = position_offset_in_Area;
    }
    public Spot(float x_area_offset, float y_area_offset){
        areaOffsetPosition = new PointF(x_area_offset, y_area_offset);
    }


    public float getX(){ return areaOffsetPosition.x; }
    public float getY(){ return areaOffsetPosition.y; }


   // public Position getPosition(){ return this._indoorPosition.getPosition();}
   // public IndoorPosition getIndoorPosition(){ return _indoorPosition; }



    public Room getRoomContainer(){ return getConvexAreaContainer().getContainerRoom(); }
    public Floor getFloorContainer(){ return getRoomContainer().getContainerFloor(); }
    public Building getBuildingContainer() { return getFloorContainer().getContainerBuilding(); }


    @Override
    public ConvexArea getConvexAreaContainer() {
        return null;
    }

    public void setX(float x){
        this.areaOffsetPosition.x = x;
    }
    public void setY(float y){
        this.areaOffsetPosition.y = y;
    }







    //gestione associazione bidirezionale ConvexArea - Spot

    public final ConvexArea getContainerConvexArea(){
        return this._containerConvexArea;
    }
    final Spot  setContainerConvexArea(ConvexArea convexArea) {
        this._containerConvexArea = convexArea;
        return this;
    }
    final void  unsetContainerConvexArea() {
        this._containerConvexArea = null;
    }
    public final void removeFromContainerConvexArea() {
        if(this._containerConvexArea!=null)
            this._containerConvexArea.removeSpot(this);
    }



}