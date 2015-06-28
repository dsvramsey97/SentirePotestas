package adinfinitum.sentirepotestas;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class Animegenerator extends Thread {

    private SurfaceHolder holder;
    private boolean running = true;
    private float x = 100;
    private float y = 100;
    private float vx = 0;
    private float vy = 0;
    private float tiltX = 0;
    private float tiltY = 0;

    public Animegenerator(SurfaceHolder holder) {
        this.holder = holder;
    }

    @Override
    public void run() {
        while(running ) {
            Canvas canvas = null;


            try {
                canvas = holder.lockCanvas();
                synchronized (holder) {
                    // update
                    vx -= tiltX * 0.1;
                    vy += tiltY * 0.1;
                    x += vx;
                    y += vy;
                    if(x + 50 > canvas.getWidth()) {
                        x = canvas.getWidth() - 50;
                        vx *= -0.9;
                    }
                    else if(x - 50 > canvas.getHeight()) {
                        y = canvas.getHeight() - 50;
                        vy *= -0.9;
                    }
                    else if(y - 50 < 0) {
                        y = 50;
                        vy *= -0.9;
                    }

                    // draw
                    canvas.drawColor(Color.BLACK);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    canvas.drawCircle(x, y, 50, paint);
                }
            }
            finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }



    public void setTilt(float tiltX, float tiltY) {
        this.tiltX = tiltX;
        this.tiltY = tiltY;
    }

}
