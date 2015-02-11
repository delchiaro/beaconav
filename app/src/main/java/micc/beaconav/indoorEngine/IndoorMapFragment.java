package micc.beaconav.indoorEngine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import micc.beaconav.R;


public class IndoorMapFragment extends Fragment implements View.OnTouchListener  {

    // these matrices will be used to move and zoom image
    private Matrix bgMatrix = new Matrix();
    private Matrix bgSavedMatrix = new Matrix();
    private Matrix fgMatrix = new Matrix();
    private Matrix fgSavedMatrix = new Matrix();



    ImageView backgroundImgView;
    ImageView foregroundImgView;


    Picture pictureBuilding;
    Picture pictureMarkers;


    private Matrix buildingMatrix = new Matrix();
    private Matrix markersMatrix = new Matrix();





    private class Ball extends Drawable {


        private float old_final_scale_factor = 1;
        private float real_time_scale_factor = 1;

        private float init_x;
        private float init_y;

        private float scaled_init_x;
        private float scaled_init_y;

        public float x = 0;
        public float y = 0;

        public Ball(int x, int y) {
            this.init_x = x;
            this.init_y = y;
            this.scaled_init_x = init_x;
            this.scaled_init_y = init_y;
        }

        private final void setScaledInitCoords(){
            scaled_init_x = init_x * old_final_scale_factor * real_time_scale_factor;
            scaled_init_y = init_x * old_final_scale_factor * real_time_scale_factor;
        }

        /**
         * Questo metodo deve essere richiamato mentre si sta eseguendo il pinch to zoom.
         * Servirá per settare un fattore di scala momentaneo in tempo reale senza sovrascrivere
         * il vecchio valore di scala settato per l'immagine (si andranno a moltiplicare in
         * tempo reale).
         * @param scale
         */
        public void setOnTouchRealTimeScaleFactor(float scale) {
            real_time_scale_factor = scale;
            setScaledInitCoords();

        }

        /**
         * Questo metodo deve essere richiamato appena termina l'esecuzione del pinch to zoom
         * (ovvero quando le dita sono staccate dallo schermo, evento ACTION_UP).
         * Ingloberá il fattore di scala provvisorio nel fattore di scala totale dell'immagine
         * (che adesso è ferma e non necessita di un costante aggiornamento del fattore di scala)
         * e resetterá ad 1 (nullo) il fattore di scala in tempo reale.
         */
        public void setFinalTouchScaleFactor(){
            this.old_final_scale_factor *= real_time_scale_factor;
            this.real_time_scale_factor = 1;
            setScaledInitCoords();
        }





        @Override
        public void draw(Canvas canvas) {
            //fgMatrix.postTranslate(100, 100);
            Paint paintMarkers = new Paint();
            paintMarkers.setColor(Color.RED);
            canvas.drawCircle(scaled_init_x + x, scaled_init_y +y, 25, paintMarkers);
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

    Ball ball = new Ball(100, 100);
    Ball ball2 = new Ball(200, 200);
    Drawable drawable;

    ViewGroup container = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.container = container;
        return inflater.inflate(R.layout.activity_canvas_test, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(container != null) {
            backgroundImgView = (ImageView) container.findViewById(R.id.backgroundImageView);
            backgroundImgView.setOnTouchListener(this);
            backgroundImgView.setScaleType(ImageView.ScaleType.MATRIX);
            foregroundImgView = (ImageView) container.findViewById(R.id.foregroundImageView);
        }
//        foregroundImgView.setOnTouchListener(this);
//        foregroundImgView.setScaleType(ImageView.ScaleType.MATRIX);






        pictureBuilding = new Picture();
        Paint paintWalls = new Paint();
        paintWalls.setColor(Color.BLUE);


        Path path = new Path();
        path.moveTo(1, 1);
        path.lineTo(250, 1);
        path.lineTo(250, 250);
        path.lineTo(1, 250);
        path.lineTo(1, 1);

        Canvas canvasBuilding;
        canvasBuilding = pictureBuilding.beginRecording(320, 320);
        canvasBuilding.drawPath(path, paintWalls);
        //canvasBuilding.restore();
        pictureBuilding.endRecording();


        pictureMarkers = new Picture();
        Paint paintMarkers = new Paint();
        paintMarkers.setColor(Color.RED);

        Canvas canvasMarkers;
        canvasMarkers = pictureMarkers.beginRecording(320, 320);
        canvasMarkers.drawCircle(0, 0, 25, paintMarkers);

        pictureMarkers.endRecording();



        drawable = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                ball2.draw(canvas);

                ball.draw(canvas);
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


        backgroundImgView.setImageBitmap(generateBackgroundBmp());

        // foregroundImgView.setImageBitmap(generateForegroundBmp());
        foregroundImgView.setImageDrawable(drawable);



    }



    public Bitmap generateBackgroundBmp() {

        Bitmap frame =  Bitmap.createBitmap(320, 320, Bitmap.Config.ARGB_8888);

        Canvas frameBuildingCanvas = new Canvas(frame);
        frameBuildingCanvas.setMatrix(this.buildingMatrix);
        pictureBuilding.draw(frameBuildingCanvas);

        return frame;
    }







    // we can be in one of these 3 states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    // remember some things for zooming
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private float[] lastEvent = null;


    private float scaleFactor = 1;


    @Override
    public boolean onTouch(View v, MotionEvent event)
    {

        ImageView view = (ImageView) v;

        switch (event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:
                bgSavedMatrix.set(bgMatrix);
                fgSavedMatrix.set(fgMatrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                lastEvent = null;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    bgSavedMatrix.set(bgMatrix);
                    fgSavedMatrix.set(fgMatrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                //TODO: DISATTIVATA ROTAZIONE, riabilitarla gestendo spostamento marker (come in zoom)
                // d = rotation(event);

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(mode == ZOOM) {
                    ball.setFinalTouchScaleFactor();
                    ball2.setFinalTouchScaleFactor();
                }
                mode = NONE;
                lastEvent = null;


                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG)
                {
                    bgMatrix.set(bgSavedMatrix);
                    fgMatrix.set(fgSavedMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    bgMatrix.postTranslate(dx, dy);
                    fgMatrix.postTranslate(dx, dy);
                }
                else if (mode == ZOOM)
                {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        bgMatrix.set(bgSavedMatrix);
                        fgMatrix.set(fgSavedMatrix);

                        float newScale = (newDist / oldDist);
                        bgMatrix.postScale(newScale, newScale, mid.x, mid.y);
                        // scala la matrice dello sfondo (mappa)


                        ball.setOnTouchRealTimeScaleFactor( newScale );
                        ball2.setOnTouchRealTimeScaleFactor( newScale );
                        // scala la posizione del marker dovuta allo zoom dello sfondo,
                        // in tempo reale senza zoomarlo, in modo che il centro del marker
                        // sia sempre nello stesso punto dello sfondo (mappa) scalato.

                    }
                    if (lastEvent != null && event.getPointerCount() == 3) {
                        newRot = rotation(event);
                        float r = newRot - d;
                        float[] values = new float[9];
                        bgMatrix.getValues(values);
                        float tx = values[2];
                        float ty = values[5];
                        float sx = values[0];
                        float xc = (view.getWidth() / 2) * sx;
                        float yc = (view.getHeight() / 2) * sx;

                        //TODO: DISATTIVATA ROTAZIONE, riabilitarla gestendo spostamento marker (come in zoom)
                        // bgMatrix.postRotate(r, tx + xc, ty + yc);
                    }
                }
                break;
        }

        this.backgroundImgView.setImageMatrix(bgMatrix);

        float[] matrixVal = new float[9];
        bgMatrix.getValues(matrixVal);

        this.ball.x = matrixVal[2];
        this.ball.y = matrixVal[5];

        this.ball2.x = matrixVal[2];
        this.ball2.y = matrixVal[5];
        this.drawable.invalidateSelf();
        this.foregroundImgView.setImageMatrix(fgMatrix);

        return true;
    }

    /**
     * Determine the space between the first two fingers
     */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    /**
     * Calculate the mid point of the first two fingers
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /**
     * Calculate the degree to be rotated by.
     *
     * @param event
     * @return Degrees
     */
    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }



}
