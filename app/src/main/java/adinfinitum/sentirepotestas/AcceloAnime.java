package adinfinitum.sentirepotestas;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class AcceloAnime extends SurfaceView implements SurfaceHolder.Callback, SensorEventListener {

    SurfaceHolder holder;
    Animegenerator anime;
    public AcceloAnime(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);

        SensorManager manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (anime != null) {
            anime.setTilt(event.values[0], event.values[1]);


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
