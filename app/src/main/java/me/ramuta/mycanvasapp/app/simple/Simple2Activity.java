package me.ramuta.mycanvasapp.app.simple;

import android.app.Activity;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import me.ramuta.mycanvasapp.app.R;

public class Simple2Activity extends Activity {
    private static final String TAG = "Simple2Activity";
    private LinearLayout linlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        linlay = (LinearLayout) findViewById(R.id.simpleCanvas);

        // canvas with white background
        Bitmap bg = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bg);

        // apartment image
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap flat = BitmapFactory.decodeResource(getResources(), R.drawable.flat, options);
        Bitmap rFlat = Bitmap.createScaledBitmap(flat, 460, 700, false);
        canvas.drawBitmap(rFlat, 10f, 10f, null);
        //canvas.drawBitmap(flat, 10f, 10f, null);

        // rectangle
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawRect(50, 50, 200, 200, paint);

        // draw everything into linear layout
        linlay.setBackground(new BitmapDrawable(getResources(), bg));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
