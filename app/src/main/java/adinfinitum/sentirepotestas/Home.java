package adinfinitum.sentirepotestas;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;



public class Home extends ActionBarActivity {




    SensorManager s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        s = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }
    public void acceloclick(View view)
    {
        if(s.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null)
        {
            Intent intent;
            intent = new Intent(Home.this, Accelerometer.class);
            startActivity(intent);
        }
        else
        {
            System.exit(1);

        }

    }

    public void sproxyclick(View view)
    {
        if(s.getDefaultSensor(Sensor.TYPE_PROXIMITY)!=null)
        {
            Intent intent;
            intent = new Intent(Home.this, ProximitySentire.class);
            startActivity(intent);
        }
        else
        {

            System.exit(1);


        }
    }
    public void gyronclick(View view)
    {
        if(s.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!=null)
        {
            Intent intent;
            intent = new Intent(Home.this, GyroSentire.class);
            startActivity(intent);
        }
        else
        {
         System.exit(1);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

