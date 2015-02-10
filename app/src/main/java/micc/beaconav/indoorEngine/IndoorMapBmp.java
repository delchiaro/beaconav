package micc.beaconav.indoorEngine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;

import micc.beaconav.indoorEngine.bmpBuilding.Building;

/**
 * Created by nagash on 09/02/15.
 */
public class IndoorMapBmp {

    private Building building;



    public IndoorMapBmp( Building building) {
        this.building = building;
    }


    public Bitmap drawMapBmp()
    {
        Bitmap tempBmp =  Bitmap.createBitmap((int)building.getWidth(), (int)building.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas tempCanvas = new Canvas(tempBmp);

        // Removing anti aliasing:
        building.draw(tempCanvas);
        return tempBmp;
    }



}
