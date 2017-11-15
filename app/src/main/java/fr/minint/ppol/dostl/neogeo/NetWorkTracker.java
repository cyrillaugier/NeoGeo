package fr.minint.ppol.dostl.neogeo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Cyril Laugier on 26/10/17.
 */

public class NetWorkTracker implements LocationListener {

    Context context;
    List<Location> locations;

    public NetWorkTracker(Context c) {
        context = c;
    }


    public Location getLocation() {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Permission not granted", Toast.LENGTH_LONG).show();
            return null;
        }

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        if (isNetworkEnabled) {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5 * 1000, 0, this);
            Location l = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//            for (Location location : locations
//                    ) {
//                locations.add(location);
//                Log.d("getLocation()", ""+location.getTime()+"\n"+location.getLatitude()+"\n"+location.getLongitude());
//            }
            return l;
        } else {
            Toast.makeText(context, "Please enable Position + Network", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
