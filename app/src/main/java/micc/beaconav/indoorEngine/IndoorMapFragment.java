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
import micc.beaconav.indoorEngine.building.Building;
import micc.beaconav.indoorEngine.building.ConvexArea;
import micc.beaconav.indoorEngine.building.Floor;
import micc.beaconav.indoorEngine.building.Room;
import micc.beaconav.indoorEngine.building.Vertex;
import micc.beaconav.indoorEngine.building.spot.ArtSpot;
import micc.beaconav.indoorEngine.building.spot.DrawableSpot;
import micc.beaconav.indoorEngine.building.spot.DrawableSpotManager;


public class IndoorMapFragment extends Fragment implements View.OnTouchListener  {


    private static int PPM = ProportionsHelper.PPM; // pixel per meter
    // these matrices will be used to move and zoom image
    private Matrix bgMatrix = new Matrix();
    private Matrix bgSavedMatrix = new Matrix();
    private Matrix fgMatrix = new Matrix();
    private Matrix fgSavedMatrix = new Matrix();



    ImageView backgroundImgView;
    ImageView foregroundImgView;


    private Matrix buildingMatrix = new Matrix();

    DrawableSpotManager drawableSpotManager;

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

        // BUILDING DEFINITION
        Building building = new Building(9*PPM, 9*PPM);

        // FLOOR DEFINITION
        Floor floor = new Floor(0);
        building.add(floor);


        // ROOM DEFINITION
        Room room = new Room();
        floor.add(room);
        room.addVertex(new Vertex(1,  1),       0);
        room.addVertex(new Vertex(6,  1),       1);
        room.addVertex(new Vertex(6,  5),       2);
        room.addVertex(new Vertex(5,  5.5f),    3);
        room.addVertex(new Vertex(2,  5.5f),    4);
        room.addVertex(new Vertex(1,  5),       5);

        // CONVEX AREA DEFINITION
        ConvexArea area = new ConvexArea();
        room.add(area);
        area.addRoomVertex(0);
        area.addRoomVertex(1);
        area.addRoomVertex(2);
        area.addRoomVertex(3);
        area.addRoomVertex(4);
        area.addRoomVertex(5);
        area.isConvex(); // dimostrativo: controllo utilizzabile, adesso non lo utilizziamo.

        // DRAWABLES DEFINITION
        drawableSpotManager = new DrawableSpotManager(area);
        ArtSpot spot1 = new ArtSpot(5.5f,   2    );
        ArtSpot spot2 = new ArtSpot(4.4f,   4.9f );
        ArtSpot spot3 = new ArtSpot(2f,     2f   );
        drawableSpotManager.add(spot1);
        drawableSpotManager.add(spot2);
        drawableSpotManager.add(spot3);

        spot1.toggleSelection();





        // DRAWING:
        Bitmap bmp = generateBackgroundBmp(building);
        backgroundImgView.setImageBitmap(bmp );
        foregroundImgView.setImageDrawable(drawableSpotManager.newWrapperDrawable());

    }



    public Bitmap generateBackgroundBmp(Building building) {

        Bitmap frame =  Bitmap.createBitmap((int)building.getWidth(), (int)building.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas frameBuildingCanvas = new Canvas(frame);
        frameBuildingCanvas.setMatrix(this.buildingMatrix);
        building.draw(frameBuildingCanvas);

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

        // TODO: adesso disabilitiamo il gestore di eventi touch quando rileviamo 3 o più tocchi!!
        // in futuro dovremo riabilitarlo. Lo abbiamo disabilitato per evitare un fastidioso bug,
        // cioè che zoomando con 2 dita e poi toccando con un terzo la posizione degli spot
        // veniva modificata erroneamente, come se il terzo dito avesse introdotto un altro zoom
        // che però in realtá non introduce!
        // Poichè non zooma il bg non dovrebbe influenzare nemmeno il foreground, ma invece lo fa,
        // verificare perchè lo fa e fare in modo che non influenzi il foreground come non influenza
        // il background.
        if (lastEvent != null && event.getPointerCount() >= 3)
        {
            if( mode == ZOOM )
            {
                drawableSpotManager.holdScalingFactor();
            }
            mode = NONE;
            lastEvent = null;
        }
        else switch (event.getAction() & MotionEvent.ACTION_MASK)
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
                    //TODO
                    drawableSpotManager.holdScalingFactor();
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



                        drawableSpotManager.translateByRealtimeScaling(newScale);
                        // scala la posizione del marker dovuta allo zoom dello sfondo,
                        // in tempo reale senza zoomarlo, in modo che il centro del marker
                        // sia sempre nello stesso punto dello sfondo (mappa) scalato.

                    }
//
//                    if (lastEvent != null && event.getPointerCount() == 3) {
//                        newRot = rotation(event);
//                        float r = newRot - d;
//                        float[] values = new float[9];
//                        bgMatrix.getValues(values);
//                        float tx = values[2];
//                        float ty = values[5];
//                        float sx = values[0];
//                        float xc = (view.getWidth() / 2) * sx;
//                        float yc = (view.getHeight() / 2) * sx;
//
//                        //TODO: DISATTIVATA ROTAZIONE, riabilitarla gestendo spostamento marker (come in zoom)
//                        // bgMatrix.postRotate(r, tx + xc, ty + yc);
//                    }
                }
                break;
        }

        this.backgroundImgView.setImageMatrix(bgMatrix);

        float[] matrixVal = new float[9];
        bgMatrix.getValues(matrixVal);




        drawableSpotManager.translate(matrixVal[2], matrixVal[5]);
        drawableSpotManager.invalidate();
        drawableSpotManager.storedWrapperDrawable().invalidateSelf();
        drawableSpotManager.get(0).drawable().invalidateSelf();
        //TODO: traslating foreground objects, invalidate foreground object container

//        this.ball.x = matrixVal[2];
//        this.ball.y = matrixVal[5];
//
//        this.ball2.x = matrixVal[2];
//        this.ball2.y = matrixVal[5];
//        this.drawable.invalidateSelf();
        //this.foregroundImgView.setImageMatrix(fgMatrix);

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
