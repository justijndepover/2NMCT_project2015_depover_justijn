package be.howest.nmct.sportinkortrijk.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by justijndepover on 6/05/15.
 */
public class SportcenterLoader extends AsyncTaskLoader<Cursor> {

    private Cursor mCursor;

    private final String[] mColumnNames = new String[]{
            BaseColumns._ID,
            Contract.DataColumns.COLUMN_BENAMING,
            Contract.DataColumns.COLUMN_INSTANTIE,
            Contract.DataColumns.COLUMN_ADRES,
            Contract.DataColumns.COLUMN_GEMEENTE,
            Contract.DataColumns.COLUMN_SOORT,
            Contract.DataColumns.COLUMN_SPORT,
            Contract.DataColumns.COLUMN_AFMETINGEN,
            Contract.DataColumns.COLUMN_Y,
            Contract.DataColumns.COLUMN_X
    };

    private static Object lock = new Object();


    public SportcenterLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mCursor != null) {
            deliverResult(mCursor);
        }
        if (takeContentChanged() || mCursor == null) {
            forceLoad();
        }
    }

    @Override
    public Cursor loadInBackground() {
        if (mCursor == null) {
            loadCursor();
        }
        return mCursor;
    }

    private void loadCursor() {
        synchronized (lock) {
            if (mCursor != null) return;

            MatrixCursor cursor = new MatrixCursor(mColumnNames);
            URL url = null;
            InputStream input = null;
            JsonReader reader = null;
            try{
                url = new URL("http://data.kortrijk.be/sport/outdoorlocaties.json");
                input = url.openStream();
                reader = new JsonReader(new InputStreamReader(input, "UTF-8"));
                int id = 1;

                reader.beginArray();

                while (reader.hasNext()){

                    String benaming = "";
                    String instantie = "";
                    String adres = "";
                    String gemeente = "";
                    String soort = "";
                    String sport = "";
                    String afmetingen = "";
                    double x = 0.0;
                    double y = 0.0;

                    reader.beginObject();

                    while (reader.hasNext()){
                        String next = reader.nextName();
                        if (next.equals("benaming")){
                            benaming = reader.nextString();
                        }else if(next.equals("instantie")){
                            instantie = reader.nextString();
                        }else if(next.equals("adres")){
                            adres = reader.nextString();
                        }else if(next.equals("gemeente")){
                            gemeente = reader.nextString();
                        }else if(next.equals("soort")){
                            soort = reader.nextString();
                        }else if(next.equals("sport")){
                            sport = reader.nextString();
                        }else if(next.equals("afmetingen")){
                            afmetingen = reader.nextString();
                        }else if(next.equals("x")){
                            x = reader.nextDouble();
                        }else if(next.equals("y")){
                            y = reader.nextDouble();
                        }
                    }

                    MatrixCursor.RowBuilder row = cursor.newRow();
                    row.add(id);
                    row.add(benaming);
                    row.add(instantie);
                    row.add(adres);
                    row.add(gemeente);
                    row.add(soort);
                    row.add(sport);
                    row.add(afmetingen);
                    row.add(x);
                    row.add(y);
                    id++;

                    reader.endObject();

                }

                reader.endArray();

                mCursor = cursor;
            }
            catch (MalformedURLException ex){
                ex.printStackTrace();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }

        }
    }
}
