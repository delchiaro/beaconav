package micc.beaconav;

/**
 * Created by Nagash on 26/12/2014.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import micc.beaconav.db.dbHelper.DbManager;
import micc.beaconav.db.dbHelper.room.RoomGenerator;
import micc.beaconav.db.dbHelper.room.VertexRow;
import micc.beaconav.db.dbHelper.room.VertexSchema;
import micc.beaconav.db.dbJSONManager.JSONDownloader;
import micc.beaconav.db.dbJSONManager.JSONHandler;
import micc.beaconav.indoorEngine.IndoorMap;
import micc.beaconav.indoorEngine.building.Building;
import micc.beaconav.indoorEngine.building.Floor;
import micc.beaconav.indoorEngine.building.Room;
import micc.beaconav.localization.Position;


public class newTouchActivity extends Activity implements OnTouchListener, JSONHandler<VertexRow>
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

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        vertexDownloader = DbManager.getVertexFromRoomDownloader(0);
        vertexDownloader.addHandler(this);
        vertexDownloader.startDownload();

    }



    private void generateFrame()
    {
        setContentView(R.layout.activity_canvas_test);
        ImageView imgView = (ImageView) findViewById(R.id.imageView);
        imgView.setOnTouchListener(this);


        Building building = new Building(500,500);
        Floor floor0 = new Floor();
        Floor floor1 = new Floor();

        floor0.addRoom("MICC Room", roomToDisplay);
        building.addFloor(floor0, 0);
        building.addFloor(floor1, 1);


        IndoorMap indoorMap = new IndoorMap(building);
        Bitmap frameBmp = indoorMap.drawMapBmp();
        imgView.setImageDrawable(new BitmapDrawable(getResources(), frameBmp));

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
        roomToDisplay = RoomGenerator.generateRoomFromVertices(result);
        generateFrame();
    }
}