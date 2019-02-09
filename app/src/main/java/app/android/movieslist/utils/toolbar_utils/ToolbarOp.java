package app.android.movieslist.utils.toolbar_utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import app.android.movieslist.App;
import app.android.movieslist.Constants;
import app.android.movieslist.R;
import app.android.movieslist.activities.MainActivity;
import app.android.movieslist.utils.ColorOp;
import app.android.movieslist.utils.custom_views.CustomTextView;
import app.android.movieslist.utils.fragment_utils.FragmentOp;

public class ToolbarOp {

    public static enum Theme {
        Dark, Light;
    }

    /**
     * METHOD TO HIDE TOOL BAR
     */
    public static void hideToolbar(final View view, final Context context, final boolean isKeepHeight) {
        if (view != null) {
            view.post(new Runnable() {

                @Override
                public void run() {
                    try {
                        final AppCompatActivity activity = (AppCompatActivity) context;
                        Toolbar tb = (Toolbar) activity
                                .findViewById(R.id.toolbar);
                        if (!isKeepHeight) {
                            activity.setSupportActionBar(tb);
                            tb.removeAllViews();
                        }
                        tb.setVisibility(View.GONE);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
            });
        }
    }

    /**
     * METHOD TO SHOW TOOL BAR
     */
    public static void showToolbar(final View view, final Context context) {
        if (view != null) {
            view.post(new Runnable() {

                @Override
                public void run() {
                    try {
                        final AppCompatActivity activity = (AppCompatActivity) context;
                        Toolbar tb = (Toolbar) activity
                                .findViewById(R.id.toolbar);
                        tb.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                    }
                }
            });
        }
    }

    public static void refreshToolbar(final View view, final Context context, final String title, final String subTitle,
                                      final Theme theme, final int navResId, final View.OnClickListener navIconListener) {
        if (view != null) {
            view.post(new Runnable() {

                @Override
                public void run() {
                    try {
                        final AppCompatActivity activity = (AppCompatActivity) context;
                        Toolbar tb = (Toolbar) activity
                                .findViewById(R.id.toolbar);
                        activity.setSupportActionBar(tb);
                        tb.removeAllViews();
                        if (title != null) {
                            addTitle(activity, null, tb, context, title, theme);
                        }
                        getNavIcon(activity, tb, navResId, navIconListener);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static void addTitle(AppCompatActivity activity, View vForLightTheme, Toolbar tb, Context context, String title, Theme theme) {
        CustomTextView tvTitle = new CustomTextView(context);
        tvTitle.setSingleLine(true);
        tvTitle.setGravity(Gravity.CENTER);

        tvTitle.setEllipsize(TextUtils.TruncateAt.END);
        tb.addView(tvTitle);
        tvTitle.setCustomFont(context, Constants.DEFAULT_FONT_NAME);

        tb.setVisibility(View.VISIBLE);

        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                20);
        tvTitle.setText(title);

        if (theme == Theme.Dark) {
            tvTitle.setTextColor(ColorOp.getColor(context,
                    R.color.colorWhite));

        } else {
            tb.setBackgroundResource(R.color.colorWhite);
            tvTitle.setTextColor(ColorOp.getColor(context,
                    R.color.colorBlack));
        }
    }

    private static void getNavIcon(final AppCompatActivity activity,
                                   Toolbar tb, final int navResId,
                                   final View.OnClickListener navIconListener) {

        int count = activity.getSupportFragmentManager()
                .getBackStackEntryCount();

        if (activity instanceof MainActivity) {
            if (navResId == 0) {
                if (count == 0) {
                    tb.setNavigationIcon(R.drawable.ic_menu);
                    tb.setNavigationOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            ((MainActivity) activity).drawerAction(true);
                        }
                    });
                } else {
                    Drawable drawable = App.context.getResources().getDrawable(R.drawable.ic_back_arraow);
//                    drawable.setAutoMirrored(true);
                    tb.setNavigationIcon(drawable);
                    if (navIconListener == null) {
                        tb.setNavigationOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
//                                ((MainActivity) activity).goBackFragment(1);
                                FragmentOp.gotoBackFragment(activity, 1);
                            }
                        });
                    } else
                        tb.setNavigationOnClickListener(navIconListener);
                }
            } else {
                tb.setNavigationIcon(navResId);
                if (navIconListener == null) {
                    if (count == 0) {
                        tb.setNavigationOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                ((MainActivity) activity).drawerAction(true);
                            }
                        });
                    } else {
                        tb.setNavigationOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
//                                ((MainActivity) activity).goBackFragment(1);
                                FragmentOp.gotoBackFragment(activity, 1);
                            }
                        });
                    }
                } else
                    tb.setNavigationOnClickListener(navIconListener);
            }
        }
    }


}
