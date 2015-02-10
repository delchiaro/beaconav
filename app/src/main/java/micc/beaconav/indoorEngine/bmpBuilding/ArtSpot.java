package micc.beaconav.indoorEngine.bmpBuilding;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import micc.beaconav.indoorEngine.drawable.DrawableImage;

/**
 * Created by nagash on 09/02/15.
 */
public class ArtSpot extends  DrawableSpot
{

    private static DrawableImage drawable = new DrawableImage(0) {
        private static final int radius = 20;

        @Override
        public float getWidth() {
            return radius*2;
        }

        @Override
        public float getHeight() {
            return radius*2;
        }


        @Override
        protected void _coreDraw(Canvas canvas, PointF position) {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            canvas.drawCircle(position.x, position.y, radius, paint);
        }
    };

    public ArtSpot(PointF position_offset_in_floor) {
        super(position_offset_in_floor, ArtSpot.drawable);
    }
}
