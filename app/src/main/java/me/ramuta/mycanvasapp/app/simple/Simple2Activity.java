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

import java.util.Random;

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

        // red
        Paint redPaint = new Paint();
        //redPaint.setColor(Color.GREEN);
        //int [] mColors = new int[]{0xFFFF0000,0xFFFF00FF,0xFF0000FF,0xFF00FFFF,0xFF00FF00,0xFF000000,0xFFFFFFFF,0xFFFFFF00,0xFFFF0000};
        redPaint.setShader(
                //new LinearGradient(0, 0, 0, 50, Color.RED, Color.GREEN, Shader.TileMode.MIRROR)
                //new SweepGradient(0,0,mColors,null)
                new RadialGradient(0, 0, 20, Color.RED, Color.MAGENTA, Shader.TileMode.MIRROR)
        );
        //canvas.drawRect(50, 50, 200, 200, redPaint);
        canvas.drawCircle(90, 50, 20, redPaint);


        // painting 1
        for(int p=0; p < 20; p++) {
            Paint painting = new Paint();
            painting.setColor(Color.GREEN+(p*5));
            canvas.drawCircle(90+(p*3), 150+(p*2), 5+p, painting);
        }

        int [] mColors = new int[]{0xFFFF0000,0xFFFF00FF,0xFF0000FF,0xFF00FFFF,0xFF00FF00,0xFF000000,0xFFFFFFFF,0xFFFFFF00,0xFFFF0000};
        int p2 = 0;
        for(int color : mColors) {
            Paint painting = new Paint();
            painting.setColor(color);
            Random rand = new Random();
            int n1 = rand.nextInt(50)-50;  // x distance
            int n2 = rand.nextInt(50)-50;  // y distance
            int n3 = rand.nextInt(15)+10;  // radius
            canvas.drawCircle(110+n1, 250+n2, n3, painting);
            p2++;
        }

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
