package micc.beaconav.indoorEngine;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import micc.beaconav.R;
import micc.beaconav.db.dbHelper.DbManager;
import micc.beaconav.db.dbHelper.room.VertexRow;
import micc.beaconav.db.dbHelper.room.VertexSchema;
import micc.beaconav.db.dbJSONManager.JSONDownloader;
import micc.beaconav.db.dbJSONManager.JSONHandler;

import micc.beaconav.indoorEngine.building.building.spot.ArtSpot;
import micc.beaconav.indoorEngine.bmpBuilding.Building;
import micc.beaconav.indoorEngine.bmpBuilding.Floor;
import micc.beaconav.indoorEngine.bmpBuilding.Room;


public class IndoorMapFragment extends Fragment implements OnTouchListener, JSONHandler<VertexRow>
{

    // these matrices will be used to move and zoom image
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
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

    JSONDownloader<VertexRow, VertexSchema> vertexDownloader;
    Room roomToDisplay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_canvas_test, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        vertexDownloader = DbManager.getVertexFromRoomDownloader(0);

        vertexDownloader.addHandler(this);
        vertexDownloader.startDownload();

    }



    private void generateFrame()
    {
        ImageView imgView = (ImageView) getView().findViewById(R.id.imageView);
        imgView.setOnTouchListener(this);


        Bitmap indoorMapTest = BitmapFactory.decodeResource(getResources(), R.drawable.indoor_map_test);

        Building building = new Building(2800,2800);
        Floor floor0 = new Floor(indoorMapTest);
        building.addFloor(floor0, 0);

        ArtSpot spot1 = new ArtSpot(new PointF(2000, 2000));
        floor0.drawableSpotManager.addSpot(spot1);




        IndoorMapBmp indoorMap = new IndoorMapBmp(building);
        Bitmap frameBmp = indoorMap.drawMapBmp();
        //imgView.setImageDrawable(new BitmapDrawable(getResources(), frameBmp));
        imgView.setImageBitmap(frameBmp);

    }



    public void priorityTest()
    {

        class Test{
            int value;
            Integer value2;

            public Test()
            {
                setValue(0);
            }
            public Test(int value)
            {
                setValue(value);
            }

            public int getValue(){ return this.value; }
            public int getValueRef(){ return this.value2; }
            public void setValue(int val){ this.value = val; this.value2 = val; }

        }

        class ComparatorTest implements Comparator<Test> {
            @Override
            public int compare(Test lhs, Test rhs)
            {
                if( (lhs).getValue() > rhs.getValue() )
                {
                    return 1;
                }
                else if( (lhs).getValue() < rhs.getValue() )
                {
                    return -1;
                }
                else return 0;
            }

        };
        ComparatorTest comparator = new ComparatorTest();

        PriorityQueue<Test> queue = new PriorityQueue<Test>(1, comparator );

        Test t1 = new Test(10);
        Test t2 = new Test(20);

        queue.add(t1);
        queue.add(t2);

        Iterator<Test> iter = queue.iterator();
        while(iter.hasNext())
        {
            int val = iter.next().getValue();
            int bb = val;
        }
        t2.setValue(0);
        queue.remove(t2);
        queue.add(t2);

        iter = queue.iterator();
        while(iter.hasNext())
        {
            int val = iter.next().getValue();
            int bb = val;
        }

    }

    public boolean onTouch(View v, MotionEvent event)
    {

        ImageView view = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                lastEvent = null;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                lastEvent = null;
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG)
                {
                    matrix.set(savedMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    matrix.postTranslate(dx, dy);
                }
                else if (mode == ZOOM)
                {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = (newDist / oldDist);
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                    if (lastEvent != null && event.getPointerCount() == 3) {
                        newRot = rotation(event);
                        float r = newRot - d;
                        float[] values = new float[9];
                        matrix.getValues(values);
                        float tx = values[2];
                        float ty = values[5];
                        float sx = values[0];
                        float xc = (view.getWidth() / 2) * sx;
                        float yc = (view.getHeight() / 2) * sx;
                        matrix.postRotate(r, tx + xc, ty + yc);
                    }
                }
                break;
        }

        view.setImageMatrix(matrix);
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



    @Override
    public void onJSONDownloadFinished(VertexRow[] result) {
        //roomToDisplay = RoomGenerator.generateRoomFromVertices(result);
        generateFrame();
    }
}
