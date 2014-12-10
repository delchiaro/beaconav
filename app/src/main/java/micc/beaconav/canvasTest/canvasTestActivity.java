package micc.beaconav.canvasTest;

import android.graphics.RectF;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import micc.beaconav.R;

public class canvasTestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ZoomView(this));

    }

//
//    public class MyView extends View {
//        public MyView(Context context)
//        {
//            super(context);
//            // TODO Auto-generated constructor stub
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas)
//        {
//            super.onDraw(canvas);
//            int x = getWidth();
//            int y = getHeight();
//            int radius;
//            radius = 100;
//            Paint paint = new Paint();
//            paint.setStyle(Paint.Style.FILL);
//            paint.setColor(Color.WHITE);
//            canvas.drawPaint(paint);
//            // Use Color.parseColor to define HTML colors
//            paint.setColor(Color.parseColor("#CD5C5C"));
//            canvas.drawCircle(x / 2, y / 2, radius, paint);
//
//            paint.setStrokeWidth(10);
//            canvas.drawLine(0, 0, 100, 100, paint);
//            canvas.save();
//            canvas.translate(2, 2);
//            canvas.scale(0.02f, 0.02f);
//            canvas.restore();
//        }
//
//
//    }


}
