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

    public Cursor CurrentCursor;
    OverzichtFragment of;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overzicht);
        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.container, new OverzichtFragment(), "FilterFragment").commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu); //replace with xml from menu

        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        of = (OverzichtFragment) getFragmentManager().findFragmentByTag("FilterFragment");
        try{
            CurrentCursor = of.mAdapter.getCursor();
        }catch(Exception e){

        }


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try{
                    String s = searchView.getQuery().toString();
                    FilterCursorWrapper fcw = new FilterCursorWrapper(CurrentCursor, s, 1);
                    of.mAdapter.changeCursor(fcw);
                }catch(Exception e){

                }
                return true;
            }
        });

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
