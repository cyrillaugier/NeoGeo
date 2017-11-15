package fr.minint.ppol.dostl.neogeo;

import android.Manifest;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {

    public static final String CONTEXT = NetWorkTracker.class.getSimpleName();

    Button btnGetLoc;
    ImageView charlie;
    ImageView charlieArrested;
    TextView helloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetLoc = (Button) findViewById(R.id.btnGetLoc);

        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

        btnGetLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NetWorkTracker nwt = new NetWorkTracker(getApplicationContext());

                Date timeFix = new Date(nwt.getLocation().getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                String datePerso = sdf.format(timeFix);

                Log.d(CONTEXT,"getTime() : long = "+nwt.getLocation().getTime()+
                        "\n\"Soit en language humain\" : Date = "+timeFix+
                        "\n\"En français\" : String = "+datePerso
                );

                Log.d(CONTEXT,"*\n******* contenu de ma Location *********\n*");

                Log.d(CONTEXT,"\ngetProvider() : String = "+nwt.getLocation().getProvider()+
                        "\ngetTime() : long = "+nwt.getLocation().getTime()+
                        "\ngetLatitude() : double = "+nwt.getLocation().getLatitude()+
                        "\ngetLongitude() : double = "+nwt.getLocation().getLongitude()+
                        "\ngetAltitude() : double = "+nwt.getLocation().getAltitude()+
                        "\ngetAccuracy() : float = "+nwt.getLocation().getAccuracy()+
                        "\ngetSpeed() : float = "+nwt.getLocation().getSpeed()+
                        "\ngetBearing() : float = "+nwt.getLocation().getBearing()+
                        "\ngetElapsedRealtimeNanos() : long = "+nwt.getLocation().getElapsedRealtimeNanos()+
                        "\ngetExtras() : Bundle = "+nwt.getLocation().getExtras()
/*
                        //used API level is 23. For these calls, we need to upgrade to API level 26
                        +"\n"+nwt.getLocation().getBearingAccuracyDegrees()+
                        "\n"+nwt.getLocation().getVerticalAccuracyMeters()+
                        "\n"+nwt.getLocation().getSpeedAccuracyMetersPerSecond()
*/
                );

                Log.d(CONTEXT,"*\n******* contenu de mon Bundle *********\n*");

                Bundle bundle = nwt.getLocation().getExtras();
                for(String key : bundle.keySet()){
                    Log.d(CONTEXT, ""+ key +" => " + bundle.get(key) + ";");
                }

                Location l = nwt.getLocation();
                if(l != null){
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();

                    Toast.makeText(getApplicationContext(),"Latitude : "+lat+"\nLongitude : "+lon,Toast.LENGTH_LONG).show();

                    charlie = (ImageView)findViewById(R.id.CHARLIE_ARRESTED);
                    charlie.setVisibility(View.VISIBLE);
                    charlieArrested = (ImageView)findViewById(R.id.CHARLIE);
                    charlieArrested.setVisibility(View.INVISIBLE);
                    helloWorld = (TextView)findViewById(R.id.title);
                    helloWorld.setVisibility(View.INVISIBLE);

                }
            }
        });
    }
}
