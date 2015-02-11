package micc.beaconav.indoorEngine.building.spot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;

import micc.beaconav.FragmentHelper;

/**
 * Created by nagash on 09/02/15.
 */
public class ArtSpot extends DrawableSpot
{
    private boolean selected = false;

    public ArtSpot() { this(0, 0); }
    public ArtSpot(float x, float y) {
        super(x, y);
        if(borderPaint == null)
        {
            borderPaint = new Paint();
            borderPaint.setColor(Color.BLUE);
            borderPaint.setStyle(Paint.Style.STROKE);
            borderPaint.setStrokeWidth(FragmentHelper.dpToPx(4));

            fillPaint = new Paint();
            fillPaint.setColor(Color.WHITE);
            fillPaint.setStyle(Paint.Style.FILL);


            borderPaintSelected = new Paint();
            borderPaintSelected.setColor(Color.BLUE);
            borderPaintSelected.setStyle(Paint.Style.STROKE);
            borderPaintSelected.setStrokeWidth(FragmentHelper.dpToPx(7));

            borderPaintSelected2 = new Paint();
            borderPaintSelected2.setColor(Color.WHITE);
            borderPaintSelected2.setStyle(Paint.Style.STROKE);
            borderPaintSelected2.setStrokeWidth(FragmentHelper.dpToPx(4));

            fillPaintSelected = new Paint();
            fillPaintSelected.setColor(Color.BLUE);
            fillPaintSelected.setStyle(Paint.Style.FILL);
//
//            selectedPicture = new Picture();
//            Canvas selectedArtSpotCanvas = selectedPicture.beginRecording(900,900);
//            selectedArtSpotCanvas.drawCircle(x_for_drawing(), y_for_drawing(), radius, borderPaintSelected);
//            selectedArtSpotCanvas.drawCircle(x_for_drawing(), y_for_drawing(), radius, borderPaintSelected2);
//            selectedArtSpotCanvas.drawCircle(x_for_drawing(), y_for_drawing(), radius, fillPaintSelected);
//            selectedPicture.endRecording();
//            selectedPictureDrawable = new PictureDrawable(selectedPicture);
        }
    }


    private static Paint borderPaint = null;
    private static Paint fillPaint = null;

    private static Paint borderPaintSelected = null;
    private static Paint borderPaintSelected2 = null;
    private static Paint fillPaintSelected = null;
//    Picture selectedPicture = null;
//    PictureDrawable selectedPictureDrawable = null;

    public boolean toggleSelection() {
        selected = !selected;
        return selected;
    }
    private static final float bluePaintStroke = 4;
    private final static int radius_DP = 10;
    private final static int radius = FragmentHelper.dpToPx(radius_DP);


    @Override
    protected Drawable generateDrawable() {
        return new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                if(selected){
                    canvas.drawCircle(x_for_drawing(), y_for_drawing(), radius, borderPaintSelected);
                    canvas.drawCircle(x_for_drawing(), y_for_drawing(), radius, borderPaintSelected2);
                    canvas.drawCircle(x_for_drawing(), y_for_drawing(), radius, fillPaintSelected);
                }
                else
                {
                    canvas.drawCircle(x_for_drawing(), y_for_drawing(), radius, borderPaint);
                    canvas.drawCircle(x_for_drawing(), y_for_drawing(), radius, fillPaint);
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
