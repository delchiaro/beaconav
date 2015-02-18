package micc.beaconav.indoorEngine.building.spot.path;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

import java.util.Iterator;
import java.util.List;

import micc.beaconav.indoorEngine.building.spot.draw.DrawableSpotManager;

/**
 * Created by nagash on 14/02/15.
 */
public class PathSpotManager<PS extends PathSpot> extends DrawableSpotManager<PS> {

    public PathSpotManager() {
    }

    public PathSpotManager(List initList) {
        super(initList);
    }


    @Override
    protected  Drawable generateWrapperDrawable()
    {
        return new Drawable() {
            @Override
            public void draw(Canvas canvas)
            {
                Iterator<PS> spotIter = iterator();
                int i = 0;
                while(spotIter.hasNext())
                {
                    PS spot = spotIter.next();
                    if(spot instanceof DoorSpot)
                    {
                        if( ((DoorSpot) spot).isVisibleInDijkstraPath() ) {

                            if (spotIter.hasNext() == false)
                                spot.setStepNumber(-100); // -100 = punto di arrivo
                            else spot.setStepNumber(i); // 0 = punto di partenza
                            spot.drawable().draw(canvas);
                            i++;
                        }
//                        PS nextSpot = spotIter.next();
//                        if(nextSpot instanceof DoorSpot)
//                        {
//                            // disegna Spot --> nextSpot nei rispettivi punti di aggancio della porta
//                        }
//                        else nextSpot.drawable().draw(canvas);
                    }
                    else
                    {
                        if( spotIter.hasNext() == false )
                            spot.setStepNumber(-100); // -100 = punto di arrivo
                        else spot.setStepNumber(i); // 0 = punto di partenza
                        spot.drawable().draw(canvas);


                        i++;
                    }

                }
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter cf) {

            }

            @Override
            public int getOpacity() {
                return 0;
            }
        };
    }
}
