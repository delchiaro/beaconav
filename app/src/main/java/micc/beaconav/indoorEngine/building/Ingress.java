package micc.beaconav.indoorEngine.building;

import android.graphics.Canvas;

import micc.beaconav.indoorEngine.drawable.Drawable;
import micc.beaconav.indoorEngine.building.spot.DrawableSpot;

/**
 * Created by nagash on 24/01/15.
 */
public class Ingress extends Drawable
{
    private final Spot virtualDoorA;
    private final Spot virtualDoorB;
    private final DrawableSpot physicalDoor;

    private final ConvexArea areaA;
    private final ConvexArea areaB;



    public Ingress(ConvexArea area_A, ConvexArea area_B, Spot virtualDoor_A, Spot virtualDoor_B, DrawableSpot physicalDoor) {
        super(0);
        this.virtualDoorA = virtualDoor_A;
        this.virtualDoorB = virtualDoor_B;
        this.physicalDoor = physicalDoor;
        this.areaA = area_A;
        this.areaB = area_B;

        ConvexArea.linkConvexAreaIngress(area_A, area_B, this);
    }
    public Ingress(ConvexArea area_A, ConvexArea area_B, Spot virtualDoor_A, Spot virtualDoor_B) {
        this(area_A, area_B, virtualDoor_A, virtualDoor_B, null);
    }
    public Ingress(ConvexArea area_A, ConvexArea area_B, Spot virtualDoor) {
        this(area_A, area_B, virtualDoor, virtualDoor, null);
    }
    public Ingress(ConvexArea area_A, ConvexArea area_B, DrawableSpot physicalDoor) {
        this(area_A, area_B, physicalDoor, physicalDoor, physicalDoor);
    }


    public Spot getVirtualDoorA() {
        return virtualDoorA;
    }

    public Spot getVirtualDoorB() {
        return virtualDoorB;
    }

    public DrawableSpot getPhysicalDoor() {
        return physicalDoor;
    }

    public ConvexArea getAreaA() {
        return areaA;
    }

    public ConvexArea getAreaB() {
        return areaB;
    }


    @Override
    protected void _coreDraw(Canvas canvas)
    {
        if(physicalDoor != null){
            physicalDoor.draw(canvas);
        }

    }
}
