package me.ramuta.mycanvasapp.app.simple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by ramuta on 17/11/2014.
 */
public class SimpleView extends View {
    private static final String TAG = "SimpleView";

    public SimpleView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = getWidth();
        int y = getHeight();
        int radius = 10;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);

        paint.setColor(Color.RED);
        canvas.drawCircle(
                x/3, // position x
                y/3, // position y
                radius, // circle size
                paint);
    }
}
