package be.howest.nmct.sportinkortrijk.loader;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by justijndepover on 6/05/15.
 */
public class Contract {
    public interface DataColumns extends BaseColumns {
        public static final String COLUMN_BENAMING = "benaming";
        public static final String COLUMN_INSTANTIE = "instantie";
        public static final String COLUMN_ADRES = "adres";
        public static final String COLUMN_GEMEENTE = "gemeente";
        public static final String COLUMN_SOORT = "soort";
        public static final String COLUMN_SPORT = "sport";
        public static final String COLUMN_AFMETINGEN = "afmetingen";
        public static final String COLUMN_Y = "y";
        public static final String COLUMN_X = "x";
    }
}
