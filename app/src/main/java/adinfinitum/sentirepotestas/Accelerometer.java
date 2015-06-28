package adinfinitum.sentirepotestas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class Accelerometer extends ActionBarActivity implements SensorEventListener{
        SensorManager sam;
        TextView SacciloX,SacciloY,SacciloZ;
        private GraphView graph ;
        LineGraphSeries seriesx = new LineGraphSeries<>(new DataPoint[]{});
        LineGraphSeries seriesy = new LineGraphSeries<>(new DataPoint[]{});
        LineGraphSeries seriesz = new LineGraphSeries<>(new DataPoint[]{});
        Integer t = 0;
        float arr[] = {0,0,0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        sam = (SensorManager)getSystemService(Context.SENSOR_SERVICE);


        SacciloX = (TextView)findViewById(R.id.AcceloX);
        SacciloY = (TextView)findViewById(R.id.AcceloY);
        SacciloZ = (TextView)findViewById(R.id.AcceloZ);
        graph = (GraphView)findViewById(R.id.graphX);
        graph.addSeries(seriesx);
        graph.addSeries(seriesy);
        graph.addSeries(seriesz);
        graph.getViewport().setMaxX(30);
        graph.getViewport().setMinX(0);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setBackgroundColor(Color.BLUE);
        seriesx.setColor(Color.CYAN);
        seriesy.setColor(Color.RED);
        seriesz.setColor(Color.GREEN);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-20);
        graph.getViewport().setMaxY(20);
    }

    public void accgoback(View view)
    {
        Intent acc;
        acc = new Intent(Accelerometer.this,Home.class);

        startActivity(acc);
    }
    public void animeview(View view)
    {
        Intent anime = new Intent(Accelerometer.this,AcceloAnime.class);
        startActivity(anime);
    }
  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accelerometer, menu);
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
    public void onSensorChanged(SensorEvent event)
    {
        SacciloX.setText("X : " + event.values[0]);
        SacciloY.setText("Y : " + event.values[1]);
        SacciloZ.setText("Z : " + event.values[2]);
        arr[0] = event.values[0];
        arr[1] = event.values[1];
        arr[2] = event.values[2];
        seriesx.appendData(new DataPoint(t, event.values[0]), true, 10000);
        seriesy.appendData(new DataPoint(t,event.values[1]),true,10000);
        seriesz.appendData(new DataPoint(t,event.values[2]),true,10000);
        t++;
        if(t>=30)
        {
            graph.getViewport().scrollToEnd();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume()
    {
        super.onResume();

        sam.registerListener(this,
                sam.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {

        super.onPause();
        sam.unregisterListener(this);

    }

}
