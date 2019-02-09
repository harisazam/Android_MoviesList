package app.android.movieslist.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by harrisazam on 2/9/2019.
 */

public class KeyboardOp {

    /**
     * METHOD TO HIDE KEYBOARD
     */
    public static void hideKeyboard(Context context) {
        try {
            if (context != null) {
                InputMethodManager imm = (InputMethodManager) context
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
                        .getWindowToken(), InputMethodManager.SHOW_FORCED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * METHOD TO SHOW KEYBOARD
     */
    public static void showKeyboard(Context context, EditText et) {
        try {
            et.requestFocus();
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Service.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et, 0);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
