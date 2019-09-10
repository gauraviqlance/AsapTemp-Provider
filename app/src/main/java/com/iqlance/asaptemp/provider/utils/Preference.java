package com.iqlance.asaptemp.provider.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.iqlance.asaptemp.provider.signUp.UserModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Preference {
    private Context context;
    private SharedPreferences sharedPreferences;
    //private ImageCacher imageCacheHelper;
    private String KEY_IS_FIRST_TIME_TOUR = "firstTimeTour";

    public Preference(Context context) {
        this.context = context;

        this.sharedPreferences = context.getSharedPreferences("asApTempPrefs", 0);
        //this.imageCacheHelper = new ImageCacher(this.context);
    }

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void hideKeyboardFrom(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert imm != null;
        if (imm.isActive() && activity.getCurrentFocus() != null) {
            if (activity.getCurrentFocus().getWindowToken() == null) return;
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void hideKeyboardView(View view, Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert inputMethodManager != null;
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // for listview height method.
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void getAppKeyHash(Context context) {
        try {
            @SuppressLint("PackageManagerGetSignatures") PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md;

                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //showSignedHashKey(something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {

            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    public void createLoginSession(UserModel entity) {

        String hashField = entity.getUserId();
        int user_id = Integer.parseInt(entity.getUserId());
        if (user_id != 0) {
            sharedPreferences.edit()
                    .putString("asApTemp-user", hashField)
                    .putString("asApTemp-u-token", MD5.getMD5(hashField))
                    .apply();
        }
    }

    public void destroyLoginSession() {
        sharedPreferences.edit()
                .remove("asApTemp-user")
                .remove("asApTemp-u-token")
                .apply();
    }

    public boolean isLoggedIn() {
        return (sharedPreferences.getString("asApTemp-u-token", null) != null);
    }

    public int getActiveLogin() {
        String id = sharedPreferences.getString("asApTemp-user", "");

        int user_id = 0;
        try {
            user_id = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return user_id;
    }

    public void setIsFirstTimeTour(boolean b) {
        sharedPreferences.edit().putBoolean(KEY_IS_FIRST_TIME_TOUR, b).apply();
    }
    public boolean getIsFirstTimeTour(){
        return sharedPreferences.getBoolean(KEY_IS_FIRST_TIME_TOUR, false);
    }

  

}
