package app.android.movieslist.utils;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * Created by harrisazam on 2/9/2019.
 */

public class ColorOp {

    /**
     * METHOD TO GET COLOR FROM RESOURCES
     */
    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

}
