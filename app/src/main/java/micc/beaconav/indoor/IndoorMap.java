package micc.beaconav.indoor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import micc.beaconav.indoor.building.Building;
import micc.beaconav.localization.IndoorPosition;
import micc.beaconav.localization.LocalizationManager;
import micc.beaconav.indoor.spot.Spot;

/**
 * Created by Nagash on 22/12/2014.
 */
public class IndoorMap
{
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
        paint.setColor(Color.RED);

        Bitmap tempBmp =  Bitmap.createBitmap(building.getWidth(), building.getHeight(), Bitmap.Config.RGB_565);
        Canvas tempCanvas = new Canvas(tempBmp);
        building.draw(tempCanvas);

       /* Iterator<Drawable> iter = Drawable.getDrawableQueue().iterator();
        while(iter.hasNext())
        {

            iter.next().draw(tempCanvas);
        }
*/
        return tempBmp;
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
