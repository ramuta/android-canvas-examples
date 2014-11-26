package me.ramuta.mycanvasapp.app.triangles;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.Random;

import me.ramuta.mycanvasapp.app.R;

public class TriangleActivity extends Activity {

    private LinearLayout linlay;
    private Bitmap background;
    private Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangle);

        linlay = (LinearLayout) findViewById(R.id.simpleCanvasTriangle);

        // Canvas
        background = Bitmap.createBitmap(250, 400, Bitmap.Config.ARGB_4444);
        canvas = new Canvas(background);
        canvas.drawBitmap(getMapBitmap(R.drawable.flat), 10f, 10f, null);

        // draw colors on canvas
        int [] mColors = new int[]{
                0x80FF0000, 0x80FF00FF,
                0x8000FFFF, 0x8000FF00,
                0x80000000,
                0x80FFFF00, 0x80FF0000};

        Paint painting = new Paint();
        painting.setColor(mColors[1]);

        painting.setStrokeWidth(4);
        painting.setStyle(Paint.Style.FILL);
        painting.setAntiAlias(true);

        Point a = new Point(0, 0);
        Point b = new Point(0, 10);
        Point c = new Point(9, 5);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();

        canvas.drawPath(path, painting);

        Path path1 = getTriangle(200, 120, 100);
        canvas.drawPath(path1, painting);

        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(mColors[3]);
        Point start = new Point(100, 100);
        Path path5 = getEquilateralTriangle(start, 20, Direction.WEST);
        canvas.drawPath(path5, p);

        //Bitmap tri = BitmapFactory.decodeResource(this.getResources(), R.drawable.triangle);
        //canvas.drawBitmap(tri, 10f, 10f, null);

        // draw everything into linear layout
        linlay.setBackground(new BitmapDrawable(getResources(), background));
    }

    public static Path getEquilateralTriangle(Point p1, int width, Direction direction) {
        Point p2 = null, p3 = null;

        if (direction == Direction.NORTH) {
            p2 = new Point(p1.x + width, p1.y);
            p3 = new Point(p1.x + (width / 2), p1.y - width);
        }
        else if (direction == Direction.SOUTH) {
            p2 = new Point(p1.x + width,p1.y);
            p3 = new Point(p1.x + (width / 2), p1.y + width);
        }
        else if (direction == Direction.EAST) {
            p2 = new Point(p1.x, p1.y + width);
            p3 = new Point(p1.x - width, p1.y + (width / 2));
        }
        else if (direction == Direction.WEST) {
            p2 = new Point(p1.x, p1.y + width);
            p3 = new Point(p1.x + width, p1.y + (width / 2));
        }

        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);

        return path;
    }

    public enum Direction {
        NORTH, SOUTH, EAST, WEST;
    }

    private Path getTriangle(int offset, int width, int height) {
        Path triangle = new Path();
        triangle.moveTo(0, offset);
        triangle.lineTo(width, offset - (height / 2));
        triangle.lineTo(width, offset + (height / 2));
        triangle.close();
        return triangle;
    }

    private Bitmap getMapBitmap(int mapResource) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap flat = BitmapFactory.decodeResource(getResources(), mapResource, options);
        return Bitmap.createScaledBitmap(flat, 230, 390, false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_triangle, menu);
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
