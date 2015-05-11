package be.howest.nmct.sportinkortrijk;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by justijndepover on 7/05/15.
 */
public class MapFragment extends Fragment {
    private static final String ARG_Y = "y argument";
    private static final String ARG_X = "x argument";
    private static final String ARG_BENAMING = "benaming argument";

    MapView mMapView;
    private GoogleMap googleMap;

    public static MapFragment newInstance(double x, double y, String benaming){
        MapFragment mf = new MapFragment();
        Bundle args = new Bundle();
        args.putDouble(ARG_X, x);
        args.putDouble(ARG_Y, y);
        args.putString(ARG_BENAMING, benaming);
        mf.setArguments(args);
        return mf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try{
            MapsInitializer.initialize(getActivity().getApplicationContext());
        }catch (Exception e){
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(getArguments().getDouble(ARG_X),getArguments().getDouble(ARG_Y))).title("" + getArguments().getString(ARG_BENAMING));

        googleMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(getArguments().getDouble(ARG_X),getArguments().getDouble(ARG_Y))).zoom(16).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        return v;
    }

    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
