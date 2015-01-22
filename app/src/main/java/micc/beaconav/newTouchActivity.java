package micc.beaconav;

/**
 * Created by Nagash on 26/12/2014.
 */

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import micc.beaconav.indoor.building.Building;
import micc.beaconav.indoor.building.Floor;
import micc.beaconav.indoor.localization.Position;


public class newTouchActivity extends Activity implements OnTouchListener
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

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);



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
        queue.add(t2);
        iter = queue.iterator();
        while(iter.hasNext())
        {
            int val = iter.next().getValue();
            int bb = val;
        }


//
//        Canvas canvas = new Canvas();
//
//        DrawableManager manager = new DrawableManager();
//        Drawable dw1 = new DrawableBitmap(null, new Position(1,1), 10);
//        Drawable dw2 = new DrawableBitmap(null, new Position(2,2), 20);
//
//        manager.add(dw1);
//        manager.add(dw2);
//
//
//
//        manager.drawAll(canvas);
//
//        dw2.setZIndex(30);
//
//        manager.drawAll(canvas);
//
//




        Building building = new Building(500,500);
        Floor floor1 = new Floor(null, new Position(10,10));
        Floor floor2 = new Floor(null, new Position(20,20));

        building.addFloor(floor1, 1);
        building.addFloor(floor2, 1);


        /*
        setContentView(R.layout.activity_canvas_test);
        ImageView view = (ImageView) findViewById(R.id.imageView);
        view.setOnTouchListener(this);

        ImageView imgView = (ImageView) findViewById(R.id.imageView);
        imgView.setOnTouchListener(this);

        Building building = new Building(500, 220);
        Bitmap floor0 = BitmapFactory.decodeResource(getResources(), R.drawable.indoor_map);
        building.addFloor(floor0, new Position(0,0));
        IndoorMap indoorMap = new IndoorMap(building);

        Bitmap frameBmp = indoorMap.drawMapBmp();

        imgView.setImageDrawable(new BitmapDrawable(getResources(), frameBmp));
        */

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
}