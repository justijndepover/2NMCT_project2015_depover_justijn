package be.howest.nmct.sportinkortrijk;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import be.howest.nmct.sportinkortrijk.loader.Contract;

/**
 * Created by justijndepover on 7/05/15.
 */
public class DetailsFragment extends Fragment {
    private static final String ARG_BENAMING = "benaming argument";

    public static DetailsFragment newInstance(Cursor cursor){
        DetailsFragment df = new DetailsFragment();
        String benaming = cursor.getString(cursor.getColumnIndex(Contract.DataColumns.COLUMN_BENAMING));
        Bundle Args = new Bundle();
        Args.putString(ARG_BENAMING, benaming);
        df.setArguments(Args);
        return df;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), getArguments().getString(ARG_BENAMING), Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), getArguments().getString(ARG_BENAMING), Toast.LENGTH_LONG).show();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
