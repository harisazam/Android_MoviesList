package app.android.movieslist.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import app.android.movieslist.R;

/**
 * Created by harrisazam on 2/9/2019.
 */

public class AlertOP {

    /**
     * METHOD TO SHOW ALERT WITH POSITIVE BUTTON DISMISS
     */
    public static void showAlert(Context context, String title, String message) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
            if (title != null)
                builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton(R.string.str_ok, new OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            final AlertDialog dialog = builder.create();

            dialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * METHOD TO SHOW ALERT WITH POSITIVE BUTTON LISTENER/DISMISS
     */
    public static void showAlert(Context context, String title, String message, OnClickListener onClickListener) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
            if (title != null)
                builder.setTitle(title);
            builder.setCancelable(false);
            builder.setMessage(message);
            if (onClickListener != null) {
                builder.setPositiveButton(R.string.str_ok, onClickListener);
            } else {
                builder.setPositiveButton(R.string.str_ok, new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }

            final AlertDialog dialog = builder.create();

            dialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * METHOD TO SHOW ALERT WITH POSITIVE BUTTON LISTENER/DISMISS AND BUTTON TEXT
     */
    public static void showAlert(Context context, String title, String message, String btnTxtP, String btnTxtN, OnClickListener onClickListener) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
            if (title != null)
                builder.setTitle(title);
            builder.setCancelable(false);
            builder.setMessage(message);
            builder.setPositiveButton(btnTxtP, onClickListener);
            builder.setNegativeButton(btnTxtN, new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
        } catch (
                Exception ex) {
            ex.printStackTrace();
        }
    }
}
