package be.howest.nmct.sportinkortrijk;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import org.w3c.dom.Text;

import be.howest.nmct.sportinkortrijk.loader.Contract;

/**
 * Created by justijndepover on 7/05/15.
 */
public class DetailsFragment extends Fragment {

    private OnDetailsFragmentListener mListener;

    public interface OnDetailsFragmentListener {
        public void onSelectMap(double x, double y, String benaming);
    }

    private static final String ARG_BENAMING = "benaming argument";
    private static final String ARG_INSTANTIE = "instantie argument";
    private static final String ARG_ADRES = "adres argument";
    private static final String ARG_GEMEENTE = "gemeente argument";
    private static final String ARG_SOORT = "soort argument";
    private static final String ARG_SPORT = "sport argument";
    private static final String ARG_AFMETINGEN = "afmetingen argument";
    private static final String ARG_Y = "y argument";
    private static final String ARG_X = "x argument";
    private static final String ARG_ID = "id";

    TextView tvNaam;
    TextView tvAdres;
    TextView tvGemeente;
    TextView tvSoort;
    TextView tvSport;
    TextView tvAfmetingen;
    Button btnMap;

    public static DetailsFragment newInstance(Cursor cursor){
        DetailsFragment df = new DetailsFragment();

        Bundle Args = new Bundle();
        Args.putString(ARG_BENAMING, cursor.getString(cursor.getColumnIndex(Contract.DataColumns.COLUMN_BENAMING)));
        Args.putString(ARG_INSTANTIE, cursor.getString(cursor.getColumnIndex(Contract.DataColumns.COLUMN_INSTANTIE)));
        Args.putString(ARG_ADRES, cursor.getString(cursor.getColumnIndex(Contract.DataColumns.COLUMN_ADRES)));
        Args.putString(ARG_GEMEENTE, cursor.getString(cursor.getColumnIndex(Contract.DataColumns.COLUMN_GEMEENTE)));
        Args.putString(ARG_SOORT, cursor.getString(cursor.getColumnIndex(Contract.DataColumns.COLUMN_SOORT)));
        Args.putString(ARG_SPORT, cursor.getString(cursor.getColumnIndex(Contract.DataColumns.COLUMN_SPORT)));
        Args.putString(ARG_AFMETINGEN, cursor.getString(cursor.getColumnIndex(Contract.DataColumns.COLUMN_AFMETINGEN)));
        Args.putDouble(ARG_Y, cursor.getDouble(cursor.getColumnIndex(Contract.DataColumns.COLUMN_Y)));
        Args.putDouble(ARG_X, cursor.getDouble(cursor.getColumnIndex(Contract.DataColumns.COLUMN_X)));
        df.setArguments(Args);
        return df;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (DetailsFragment.OnDetailsFragmentListener) activity;
        } catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString() + " must implement onDetailsFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Toast.makeText(getActivity(), getArguments().getString(ARG_BENAMING), Toast.LENGTH_LONG).show();
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        tvNaam = (TextView) v.findViewById(R.id.tvNaam);
        tvAdres = (TextView) v.findViewById(R.id.tvAdres);
        tvGemeente = (TextView) v.findViewById(R.id.tvGemeente);
        tvSoort = (TextView) v.findViewById(R.id.tvSoort);
        tvSport = (TextView) v.findViewById(R.id.tvSport);
        tvAfmetingen = (TextView) v.findViewById(R.id.tvAfmetingen);
        btnMap = (Button) v.findViewById(R.id.btnMap);

        tvNaam.setText(getArguments().getString(ARG_BENAMING));
        tvAdres.setText(getArguments().getString(ARG_ADRES));
        tvGemeente.setText(getArguments().getString(ARG_GEMEENTE));
        tvSoort.setText(getArguments().getString(ARG_SOORT));
        tvSport.setText(getArguments().getString(ARG_SPORT));
        tvAfmetingen.setText(getArguments().getString(ARG_AFMETINGEN));

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.onSelectMap(getArguments().getDouble(ARG_X), getArguments().getDouble(ARG_Y), getArguments().getString(ARG_BENAMING));
            }
        });

        return v;
    }
}
