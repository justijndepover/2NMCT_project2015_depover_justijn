package be.howest.nmct.sportinkortrijk;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;


public class Overzicht extends Activity implements OverzichtFragment.OnOverzichtFragmentListener, DetailsFragment.OnDetailsFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overzicht);
        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.container, new OverzichtFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overzicht, menu);
        return true;*/
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        //hier komt zoek functie

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectData(Cursor cursor) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetailsFragment detailsFragment = DetailsFragment.newInstance(cursor);
        fragmentTransaction.replace(R.id.container, detailsFragment);
        fragmentTransaction.addToBackStack("detailsFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onSelectMap(double x, double y, String benaming) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapFragment mapFragment = MapFragment.newInstance(y, x, benaming);
        fragmentTransaction.replace(R.id.container, mapFragment);
        fragmentTransaction.addToBackStack("mapFragment");
        fragmentTransaction.commit();
    }
}
