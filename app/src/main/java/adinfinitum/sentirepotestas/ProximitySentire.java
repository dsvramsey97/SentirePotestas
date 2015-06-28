package adinfinitum.sentirepotestas;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class ProximitySentire extends ActionBarActivity implements SensorEventListener{
    SensorManager spm;
    TextView Sproxim;

    ImageView proxanime;
    LinearLayout.LayoutParams layoutParams;
    LinearLayout.LayoutParams layoutParamszoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sentire);
        spm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        spm.registerListener(this,
                spm.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_NORMAL);
        proxanime = (ImageView) findViewById(R.id.imageView);
        Sproxim = (TextView)findViewById(R.id.proxtext);
        layoutParams = new LinearLayout.LayoutParams(10, 10);
       layoutParamszoom = new LinearLayout.LayoutParams(50,50);
    }
    public void proxygoback(View view)
    {
        Intent prox;
        prox = new Intent(ProximitySentire.this,Home.class);

        startActivity(prox);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_proximity_sentire, menu);
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
        Sproxim.setText("Distance : " + event.values[0]);
         if(event.values[0]==0)
         {
          proxanime.setLayoutParams(layoutParams);
         }
        else
         {
             proxanime.setLayoutParams(layoutParamszoom);
         }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
