package micc.beaconav.indoor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import micc.beaconav.R;
import micc.beaconav.indoor.building.Building;

/**
 * Created by Nagash on 22/12/2014.
 */
public class IndoorMap
{
    Building building;
    Bitmap bmp;

    public IndoorMap( Building building )
    {
    }

    Bitmap drawMap()
    {

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        Bitmap tempBmp =  Bitmap.createBitmap(building.getWidth(), building.getHeight(), Bitmap.Config.RGB_565);
        Canvas tempCanvas = new Canvas(tempBmp);
        building.draw(tempCanvas);



        return tempBmp;
    }



}
