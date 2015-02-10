package micc.beaconav.indoorEngine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;

import micc.beaconav.indoorEngine.vectorBuilding.Building;
import micc.beaconav.indoorEngine.vectorBuilding.ConvexArea;
import micc.beaconav.indoorEngine.vectorBuilding.Floor;
import micc.beaconav.indoorEngine.vectorBuilding.Room;
import micc.beaconav.localization.IndoorPosition;
import micc.beaconav.localization.LocalizationManager;
import micc.beaconav.indoorEngine.vectorBuilding.Spot;

/**
 * Created by Nagash on 22/12/2014.
 */
public class IndoorMap
{
    private static int PPM = 300; // Pixel Per Meter

    private Building building;
    private LocalizationSpotManager _localizationSpot;


    public IndoorMap( Building building )
    {
        this.building = building;
    }


    public Bitmap drawMapBmp()
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);

        Bitmap tempBmp =  Bitmap.createBitmap((int)building.getWidth(), (int)building.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas tempCanvas = new Canvas(tempBmp);

        // Removing anti aliasing:
        final DrawFilter filter = new PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG, 0);
        tempCanvas.setDrawFilter(filter);


        building.draw(tempCanvas);

       /* Iterator<Drawable> iter = Drawable.getDrawableQueue().iterator();
        while(iter.hasNext())
        {

            iter.next().draw(tempCanvas);
        }
*/
        return tempBmp;
    }



    class DijkstraSolver {



    }

    class DijkstraArea {
        ConvexArea area;
        float pesoMinimo = Float.MAX_VALUE;
        DijkstraArea predecessor = null;

        public DijkstraArea(ConvexArea area) {
            this.area = area;
        }


    }


    public void drawPath(Spot myPosition, Spot destination)
    {
        if(destination.getBuildingContainer() == this.building)
        {
            Floor destinationFloor = destination.getFloorContainer();
            Room destinationRoom = destination.getRoomContainer();



        }

    }










    private class LocalizationSpotManager
    {
        Spot _goodPosition; // will load blue icon
        Spot _badPosition; // will load gray icon, indicates that you can not be localized :(
        LocalizationManager _locManager;

        LocalizationSpotManager(LocalizationManager locManager, Bitmap goodPositionBmp, Bitmap badPositionBmp)
        {
            _locManager = locManager;
           // _goodPosition = new Spot(goodPositionBmp, null);
            //_badPosition =  new Spot(badPositionBmp, null);
    }

        public Spot getUpdatedSpot()
        {
            _locManager.update();
            IndoorPosition newPosition = _locManager.getPosition();
            int precision = _locManager.getLocalizationPrecision();
            if(precision > 0)
            {
              //  _goodPosition.upadtePosition(newPosition);
                return _goodPosition;
            }
            else
            {
             //   _badPosition.upadtePosition(newPosition);
                return _badPosition;
            }
        }

        public void update(){
            _locManager.update();
        }
    }



}
