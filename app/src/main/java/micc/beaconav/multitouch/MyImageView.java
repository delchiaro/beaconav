package micc.beaconav.multitouch;

import android.graphics.Paint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Allows to draw rectangle on ImageView.
 *
 * @author Maciej Nux Jaros
 */
public class MyImageView extends ImageView
{
    private Paint currentPaint;
    public boolean drawRect = false;
    public float left = 10;
    public float top = 30;
    public float right = 50;
    public float bottom= 40;

    public MyImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        currentPaint = new Paint();
        currentPaint.setDither(true);
        currentPaint.setColor(0xFF00CC00);  // alpha.r.g.b
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeJoin(Paint.Join.ROUND);
        currentPaint.setStrokeCap(Paint.Cap.ROUND);
        currentPaint.setStrokeWidth(2);


    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

         canvas.drawLine(0, 0, 200, 200, currentPaint);

    }
}