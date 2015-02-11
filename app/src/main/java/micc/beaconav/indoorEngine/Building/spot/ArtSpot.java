package micc.beaconav.indoorEngine.building.spot;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;

import micc.beaconav.FragmentHelper;
import micc.beaconav.indoorEngine.drawable.DrawableImage;

/**
 * Created by nagash on 09/02/15.
 */
public class ArtSpot extends DrawableSpot
{
    public ArtSpot() { this(0, 0); }
    public ArtSpot(float x, float y) {
        super(x, y);
        if(bluePaint == null)
        {
            bluePaint = new Paint();
            bluePaint.setColor(Color.BLUE);
            bluePaint.setStyle(Paint.Style.STROKE);
            bluePaint.setStrokeWidth(bluePaintStroke);

            whitePaint = new Paint();
            whitePaint.setColor(Color.WHITE);
            whitePaint.setStyle(Paint.Style.FILL);
        }
    }


    private static Paint bluePaint = null;
    private static Paint whitePaint = null;


    private static final float bluePaintStroke = 4;
    private final static int radius_DP = 10;
    private final static int radius = FragmentHelper.dpToPx(radius_DP);


    @Override
    protected Drawable generateDrawable() {
        return new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                canvas.drawCircle(x(), y(), radius, bluePaint);
                canvas.drawCircle(x(), y(), radius, whitePaint);
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
