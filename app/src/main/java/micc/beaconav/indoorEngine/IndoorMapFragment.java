package micc.beaconav.indoorEngine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.estimote.sdk.Beacon;

import java.util.List;

import micc.beaconav.R;
import micc.beaconav.indoorEngine.beaconHelper.BeaconHelper;
import micc.beaconav.indoorEngine.beaconHelper.BeaconProximityListener;
import micc.beaconav.indoorEngine.building.Building;
import micc.beaconav.indoorEngine.building.Floor;
import micc.beaconav.indoorEngine.building.Room;
import micc.beaconav.indoorEngine.building.spot.custom.ArtSpot;
import micc.beaconav.indoorEngine.building.spot.marker.MarkerSpotManager;
import micc.beaconav.indoorEngine.building.spot.path.PathSpotManager;


public class IndoorMapFragment extends Fragment
        implements View.OnTouchListener, BeaconProximityListener
{


    private static int PPM = ProportionsHelper.PPM; // pixel per meter
    // these matrices will be used to move and zoom image
    private Matrix bgMatrix = new Matrix();
    private Matrix bgSavedMatrix = new Matrix();
    private Matrix fgMatrix = new Matrix();
    private Matrix fgSavedMatrix = new Matrix();



    ImageView backgroundImgView;
    ImageView foregroundImgView;
    ImageView navigationImgView;

    private Matrix buildingMatrix = new Matrix();

    IndoorMap indoorMap;
    MarkerSpotManager markerManager;
    PathSpotManager pathSpotManager;


    ViewGroup container = null;
    ArtSpot spot1;
    ArtSpot spot2;
    ArtSpot spot3;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.container = container;
        return inflater.inflate(R.layout.fragment_indoor_map, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(container != null) {

            backgroundImgView = (ImageView) container.findViewById(R.id.backgroundImageView);
            backgroundImgView.setOnTouchListener(this);
            backgroundImgView.setScaleType(ImageView.ScaleType.MATRIX);

            foregroundImgView = (ImageView) container.findViewById(R.id.foregroundImageView);

            navigationImgView = (ImageView) container.findViewById(R.id.navigationLayerImageView);


        }

        // BUILDING DEFINITION
        Building building = new Building(50*PPM, 50*PPM);

        // FLOOR DEFINITION
        Floor floor = new Floor(0);
        building.add(floor);


        // ROOM DEFINITION
        Room corridoio = new Room();
        floor.add(corridoio);

        Room ingresso1 = new Room();
        floor.add(ingresso1);

        Room stanzaFerracani = new Room();
        floor.add(stanzaFerracani);


        spot1 = new ArtSpot(2f,     2f);
        spot2 = new ArtSpot(8f,     28);
        spot3 = new ArtSpot(11f,   27f);



        corridoio.add(spot1);
        stanzaFerracani.add(spot2);
        stanzaFerracani.add(spot3);



        // VertexDefinitions and Spot Definitions
        corridoio.pushWall(new PointF(1f,   1f));
        corridoio.pushWall(new PointF(4.5f, 1f));
        corridoio.pushWall(new PointF(4.5f, 30f));
        corridoio.pushWall(new PointF(1f,   30f));




        ingresso1.pushWall(new PointF(4.5f, 25f));
        ingresso1.pushWall(new PointF(7.5f, 25f));
            ingresso1.pushAperture(new PointF(7.5f, 26f));
            Room.addDoorSpot(ingresso1, 7f, 27, true, stanzaFerracani, 8f, 27, true );
            ingresso1.pushWall(new PointF(7.5f, 28f));

        ingresso1.pushWall(new PointF(7.5f, 30f));
        ingresso1.pushWall(new PointF(4.5f, 30f));
            ingresso1.pushAperture(new PointF(4.5f, 28f));
            Room.addDoorSpot(ingresso1, 4f, 27, true, corridoio, 5f, 27, true );
            ingresso1.pushWall(new PointF(4.5f, 26f));



        stanzaFerracani.pushWall(new PointF(7.5f, 25f));
        stanzaFerracani.pushWall(new PointF(13f,  25f));
        stanzaFerracani.pushWall(new PointF(13f,  30f));
        stanzaFerracani.pushWall(new PointF(7.5f, 30f));



        // DRAWABLES DEFINITION


//        spot1.toggleSelection();


        BeaconHelper beaconHelper = new BeaconHelper(this.getActivity());
        beaconHelper.addProximityListener(this);
        beaconHelper.scanBeacons();
        //beaconHelper.execute();



        // DRAWING:
        Bitmap bmp = generateBackgroundBmp(building);
        //backgroundImgView.setImageDrawable(indoorMap); // disegno background in vettoriale
        // disegno background stampando il vettoriale su un bitmap
        backgroundImgView.setImageBitmap(bmp );


        //indoorMap = new IndoorMap(building);
        markerManager = building.getActiveMarkerManager();
        foregroundImgView.setImageDrawable(markerManager.newWrapperDrawable());
        //foregroundImgView.setOnTouchListener(markerManager);

        pathSpotManager = building.drawBestPath(corridoio.getRoomSpot(), stanzaFerracani.getRoomSpot());
        navigationImgView.setImageDrawable(pathSpotManager.newWrapperDrawable());


        translateAll(200, 200);
        navigationImgView.invalidate();

    }

    @Override
    public void OnBeaconProximity(List<Beacon> proximityBeacons)
    {
        if(BeaconHelper.isInProximity(proximityBeacons.get(0) ))
        {
            //spot2.sele;
            spot2.drawable().invalidateSelf();
            foregroundImgView.invalidate();
        }
    }



    public Bitmap generateBackgroundBmp(Building building) {

        Bitmap frame =  Bitmap.createBitmap((int)building.getWidth(), (int)building.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas frameBuildingCanvas = new Canvas(frame);
        frameBuildingCanvas.setMatrix(this.buildingMatrix);
        building.draw(frameBuildingCanvas);

        return frame;
    }




    private void translateAll(int x, int y) {

        bgMatrix.postTranslate(x, y);
        this.backgroundImgView.setImageMatrix(bgMatrix);
        markerManager.translate(x, y);
        pathSpotManager.translate(x, y);

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
                markerManager.holdScalingFactor();
                pathSpotManager.holdScalingFactor();
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
                    markerManager.holdScalingFactor();
                    pathSpotManager.holdScalingFactor();

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



                        markerManager.translateByRealtimeScaling(newScale);
                        pathSpotManager.translateByRealtimeScaling(newScale);
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




        markerManager.translate(matrixVal[2], matrixVal[5]);
        pathSpotManager.translate(matrixVal[2], matrixVal[5]);
        //markerManager.invalidate();

        //indoorMap.invalidateSelf(); //per disegno mappa indoor in vettoriale

//        this.ball.x = matrixVal[2];
//        this.ball.y = matrixVal[5];
//
//        this.ball2.x = matrixVal[2];
//        this.ball2.y = matrixVal[5];
//        this.drawable.invalidateSelf();
        //this.foregroundImgView.setImageMatrix(fgMatrix);

        markerManager.onTouch(view, event);
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
