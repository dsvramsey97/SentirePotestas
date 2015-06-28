package adinfinitum.sentirepotestas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class GyroSentire extends ActionBarActivity implements SensorEventListener{

    SensorManager sgm;
    TextView gyrox,gyroy,gyroz;
    private GraphView gyrograph ;
    LineGraphSeries seriesx = new LineGraphSeries<>(new DataPoint[]{});
    LineGraphSeries seriesy = new LineGraphSeries<>(new DataPoint[]{});
    LineGraphSeries seriesz = new LineGraphSeries<>(new DataPoint[]{});
    Integer t = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro_sentire);
        sgm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);


        gyrox = (TextView)findViewById(R.id.GyroX);
        gyroy = (TextView)findViewById(R.id.GyroY);
        gyroz = (TextView)findViewById(R.id.GyroZ);
        gyrograph = (GraphView)findViewById(R.id.graphX);
        gyrograph.addSeries(seriesx);
        gyrograph.addSeries(seriesy);
        gyrograph.addSeries(seriesz);
        gyrograph.getViewport().setMaxX(30);
        gyrograph.getViewport().setMinX(0);
        gyrograph.getViewport().setScrollable(true);
        gyrograph.getViewport().setXAxisBoundsManual(true);
        gyrograph.getViewport().setBackgroundColor(Color.BLUE);
        seriesx.setColor(Color.CYAN);
        seriesy.setColor(Color.RED);
        seriesz.setColor(Color.GREEN);
        gyrograph.getViewport().setYAxisBoundsManual(true);
        gyrograph.getViewport().setMinY(-20);
        gyrograph.getViewport().setMaxY(20);
    }

    public void gyrogoback(View view)
    {
        Intent guy;
        guy = new Intent(GyroSentire.this,Home.class);

        startActivity(guy);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gyro_sentire, menu);
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        gyrox.setText("X : " + event.values[0]);
        gyroy.setText("Y : " + event.values[1]);
        gyroz.setText("Z : " + event.values[2]);

        seriesx.appendData(new DataPoint(t,event.values[0]),true,10000);
        seriesy.appendData(new DataPoint(t,event.values[1]),true,10000);
        seriesz.appendData(new DataPoint(t,event.values[2]),true,10000);
        t++;
        if(t>=30)
        {
            gyrograph.getViewport().scrollToEnd();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onResume()
    {
        super.onResume();

        sgm.registerListener(this,
                sgm.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {

        super.onPause();
        sgm.unregisterListener(this);

    }
}
