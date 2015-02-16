package micc.beaconav.indoorEngine.building.spot.path;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import micc.beaconav.indoorEngine.dijkstraSolver.DijkstraNodeAdapter;

/**
 * Created by nagash on 13/02/15.
 */
public class DoorSpot extends PathSpot {


    private static final float arrowCosin = 1;
    private static final int arrowColor = Color.RED;
    private static final int arrowWidth = 5;

    private static Paint arrowPaint = null;
    private static void initPaint() {
        arrowPaint = new Paint();
        arrowPaint.setStyle(Paint.Style.STROKE);
        arrowPaint.setColor(arrowColor);
        arrowPaint.setStrokeWidth(arrowWidth);
    }

    private DoorSpot _linkedDoor;

    public DoorSpot(float x, float y, DoorSpot linkedDoor) {
        super(x, y);

        if(linkedDoor != null)
        {
            if (linkedDoor != null && linkedDoor._linkedDoor == null)
            {
                _linkedDoor = linkedDoor;
                linkedDoor._linkedDoor = this;
            }
            else
            {
                //TODO:  exception
            }
        }
        else this._linkedDoor = null;

        if(arrowPaint != null) {
            initPaint();
        }
    }
    public DoorSpot(float x, float y) {
        this(x, y, null);
    }


    @Override
    public List<? extends DijkstraNodeAdapter> getAdjacent() {
        ArrayList<DijkstraNodeAdapter> retList = new ArrayList<>(super.getAdjacent());
        retList.add(_linkedDoor);
        return retList;
    }



    @Override
    protected Drawable generateDrawable() {

        return new Drawable()
        {
            @Override
            public void draw(Canvas canvas)
            {

//                if(_linkedDoor != null &&
//                    stepNumber > 0 &&
//                    _linkedDoor.stepNumber < stepNumber)
//
//                {
//                    Path arrowPath = new Path();
//                    canvas.drawLine(x(), y(), _linkedDoor.x(), _linkedDoor.y(), arrowPaint);
//                }
            }


            @Override
            public void setAlpha(int alpha) {}

            @Override
            public void setColorFilter(ColorFilter cf) {}

            @Override
            public int getOpacity() {
                return 0;
            }
        };
    }
}
