package app.android.movieslist.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by harrisazam on 2/9/2019.
 */

public class MessageUtil {

    /**
     * SNACK BAR WITH NO ACTION BUTTON AND DEFAULT BACKGROUND
     **/
    public static void showToast(Context context, String message, boolean isLengthLong) {
        Toast.makeText(context, message, isLengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    /**
     * SNACK BAR WITH NO ACTION BUTTON AND DEFAULT BACKGROUND
     **/
    public static void showSnackbar(Context context, String message, boolean isLengthLong) {
        try {
            Snackbar snack = Snackbar.make(
                    (((Activity) context).findViewById(android.R.id.content)),
                    message + "", isLengthLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT);

            View view = snack.getView();
            TextView tv = (TextView) view
                    .findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            snack.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * SNACK BAR WITH NO ACTION BUTTON AND CUSTOM BACKGROUND
     **/
    public static void showSnackbar(Context context, String message, int bgColor,
                                    boolean isLengthLong) {
        try {
            Snackbar snack = Snackbar.make(
                    (((Activity) context).findViewById(android.R.id.content)),
                    message + "", isLengthLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT);

            View view = snack.getView();
            view.setBackgroundColor(bgColor);
            TextView tv = (TextView) view
                    .findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            snack.show();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    /**
     * SNACK BAR WITH ACTION BUTTON AND CUSTOM BACKGROUND
     **/
    public static void showSnackbar(Context context, String message,
                                    int bgColor, String actionButton, boolean isLengthLong,
                                    OnClickListener actionButtonClickListener) {
        try {
            Snackbar snack = Snackbar.make(
                    (((Activity) context).findViewById(android.R.id.content)),
                    message + "", isLengthLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT);

            snack.setAction(actionButton, actionButtonClickListener);

            View view = snack.getView();
            view.setBackgroundColor(bgColor);
            TextView tv = (TextView) view
                    .findViewById(android.support.design.R.id.snackbar_text);

            TextView tvAction = (TextView) view
                    .findViewById(android.support.design.R.id.snackbar_action);
            tvAction.setTextSize(16);
            tvAction.setTextColor(Color.WHITE);

            tv.setTextColor(Color.WHITE);
            snack.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
