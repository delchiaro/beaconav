package micc.beaconav.indoorEngine.spot;


import android.graphics.PointF;

import java.util.ArrayList;

import micc.beaconav.indoorEngine.building.Building;
import micc.beaconav.indoorEngine.building.ConvexArea;
import micc.beaconav.indoorEngine.building.Floor;
import micc.beaconav.indoorEngine.building.Room;
import micc.beaconav.localization.IndoorPosition;
import micc.beaconav.localization.Position;

/**
 * 
 */
public class Spot implements ISpot
{

    protected PointF areaOffsetPosition;

    //protected IndoorPosition _indoorPosition;
    private ConvexArea _containerConvexArea;


    public Spot(ConvexArea convexAreaContainer, PointF position_offset_in_Area){
        this.areaOffsetPosition = position_offset_in_Area;
    }
    public Spot(ConvexArea convexAreaContainer, float x_area_offset, float y_area_offset){
        areaOffsetPosition = new PointF(x_area_offset, y_area_offset);
        this._containerConvexArea = convexAreaContainer;
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

}