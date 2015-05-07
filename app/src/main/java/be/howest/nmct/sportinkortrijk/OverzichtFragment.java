package be.howest.nmct.sportinkortrijk;


import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import be.howest.nmct.sportinkortrijk.loader.Contract;
import be.howest.nmct.sportinkortrijk.loader.SportcenterLoader;


/**
 * A simple {@link Fragment} subclass.
 */

public class OverzichtFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private OverzichtAdapter mAdapter;

    private OnOverzichtFragmentListener mListener;

    public OverzichtFragment() {
    }

    public interface OnOverzichtFragmentListener {
        public void onSelectData(Cursor cursor);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_overzicht, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OverzichtFragment.OnOverzichtFragmentListener) activity;
        } catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString() + " must implement OnDataListFragmentListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] columns = new String[]{Contract.DataColumns.COLUMN_BENAMING,
                /*Contract.DataColumns.COLUMN_INSTANTIE,
                Contract.DataColumns.COLUMN_ADRES,
                Contract.DataColumns.COLUMN_GEMEENTE,
                */
                Contract.DataColumns.COLUMN_SOORT,
                Contract.DataColumns.COLUMN_SPORT/*,
                Contract.DataColumns.COLUMN_AFMETINGEN,
                Contract.DataColumns.COLUMN_Y,
                Contract.DataColumns.COLUMN_X*/};
        int[] viewIds = new int[]{R.id.tvBenaming, R.id.tvSoort, R.id.tvSport};

        // Create an empty adapter we will use to display the loaded data.
        mAdapter = new OverzichtFragment.OverzichtAdapter(getActivity(), R.layout.row_sportcenter, null, columns, viewIds, 0);
        setListAdapter(mAdapter);
        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // This is called when a new Loader needs to be created.
        //create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new SportcenterLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Cursor c = (Cursor) mAdapter.getItem(position);
        String selectedData = c.getString(c.getColumnIndex(Contract.DataColumns.COLUMN_INSTANTIE));
        if (mListener != null) mListener.onSelectData(c);
    }

    class OverzichtAdapter extends SimpleCursorAdapter {

        private int layout;

        public OverzichtAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            this.layout = layout;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            View row = inflater.inflate(layout, parent, false);

            //custom code

            return row;
        }
    }
}