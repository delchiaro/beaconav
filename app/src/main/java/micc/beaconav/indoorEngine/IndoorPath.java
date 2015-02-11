package micc.beaconav.indoorEngine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.*;

import micc.beaconav.indoorEngine.drawable.Drawable;
import micc.beaconav.indoorEngine.building.spot.Spot;

/**
 * 
 */
public class IndoorPath extends Drawable
{

	List<Spot> spotList;
    Paint paint;

	public IndoorPath(Paint paint, long zIndex)
    {
        super(zIndex);
        this.paint = paint;
        spotList = new ArrayList();
	}


    public IndoorPath(long zIndex)
    {
        super(zIndex);
        this.paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        spotList = new ArrayList();
    }

    public IndoorPath(IndoorPath copy){
        this(copy.paint, copy.getZIndex()+1);//TODO: REVIEW this +1 ...
        this.spotList = (List<Spot>) copy.clone();
    }

    @Override
    public IndoorPath clone(){
        return new IndoorPath(this);
    }

    public void addSpot(Spot nextSpotInPath){
        this.spotList.add(nextSpotInPath);
    }







    @Override
    protected void _coreDraw(Canvas canvas, PointF padding) {
        if( ! spotList.isEmpty() )
        {
            Iterator<Spot> spotIter = spotList.iterator();
            Spot sp1 = spotIter.next();
            Spot sp2 = null;
            while (spotIter.hasNext())
            {
                sp2 = spotIter.next();
                canvas.drawLine(sp1.x(), sp1.y(), sp2.x(), sp2.y(), this.paint);
                sp1 = sp2;
            }
        }
    }


}