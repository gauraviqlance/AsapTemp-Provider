package com.iqlance.asaptemp.provider.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import java.util.concurrent.atomic.AtomicBoolean;

public class AsapTempApplication extends Application implements Application.ActivityLifecycleCallbacks {

    public static final boolean GPS_CHIP_DEBUG = false;
    private static final boolean USE_MOCK_LOCATION = false;
    public static String TAG = "AsapTempApplication";
    public static Context appContext;
    public static Context currentContext;
    public static boolean isUIAvailable = false;
    public static boolean isServerAvailable = false;
    public static boolean isBackground = false;
    public static boolean isActivated = false;
    public static boolean isInitialized = false;
    public static boolean isReady = false;
    public static boolean isConnectedToInternet = false;
    public static boolean isConnectedToServer = false;
    public static boolean isGooglePlayServicesAvailable = false;
    public static boolean isFirstTime = false;
    public static boolean isAPICallFromHome = false;
    public static boolean isPlanPurchased = false;
    private static AsapTempApplication instance;
    public AtomicBoolean isMapAvailable = new AtomicBoolean(true);
    public String currentCountryCode = null;
    private int activityReferences = 0;
    private boolean isActivityChangingConfigurations = false;
    private AtomicBoolean hasCheckedLocation = new AtomicBoolean(false);
    private Location lastLocation = null;


    public AsapTempApplication() {
        appContext = this;
        ShowLogToast.ShowLog(TAG, "AsapTempApplication");
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static synchronized AsapTempApplication sharedInstance() {
        if (instance == null) instance = new AsapTempApplication();
        return instance;
    }

    public boolean usingMockLocations() {
        return USE_MOCK_LOCATION;
    }

    @Override
    public void onCreate() {
        ShowLogToast.ShowLog(TAG, "onCreate");
        super.onCreate();
        //Fabric.with(this, new Crashlytics());
        instance = this;
        appContext = getApplicationContext();
        currentContext = appContext;
        //SharedPreferenceManager.init(appContext);
        registerActivityLifecycleCallbacks(this);
        if (!isInitialized) initialize();

//        LocalBroadcastManager.getInstance(appContext).registerReceiver(onlineEventReceiver, new IntentFilter(NOTIF_ONLINE));
//        LocalBroadcastManager.getInstance(appContext).registerReceiver(offlineEventReceiver, new IntentFilter(NOTIF_OFFLINE));

    }

    @Override
    public void onTerminate() {
        currentContext = appContext;
        ShowLogToast.ShowLog(TAG, "TERMINATE_APP in onTerminate");
        clean();
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        clean();
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {

        if (level >= TRIM_MEMORY_UI_HIDDEN) {
            this.onBackground();
        } else {
            clean();
        }

        super.onTrimMemory(level);
    }

    public void onForeground() {
        isBackground = false;
    }


    private void prepareForBackground() {
        ShowLogToast.ShowLog(TAG, "prepareForBackground");
    }
    //endregion

    public void onBackground() {
        ShowLogToast.ShowLog(TAG, "Entering background");

        clean();

        prepareForBackground();


        isActivated = false;
        isReady = false;
        isBackground = true;
    }

    public void onUIAvailable() {
        ShowLogToast.ShowLog(TAG, "Application UI is available");
//        AppHelper.sendBroadcast("NOTIF_APP_UI_AVAILABLE", null);

        isUIAvailable = true;

        ShowLogToast.ShowLog(TAG, "Check to see if Google Play Services are available");
        //isGooglePlayServicesAvailable = AppHelper.checkIfGooglePlayServicesAvailable();
        ShowLogToast.ShowLog(TAG, "googlePlayServicesAvailable=" + isGooglePlayServicesAvailable);

        ShowLogToast.ShowLog(TAG, "Initialize");
//        if (!isInitialized) initialize();

        ShowLogToast.ShowLog(TAG, "Application is launched");
//        AppHelper.sendBroadcast("NOTIF_APP_LAUNCHED", null);
    }

    //region ACTIVITY LIFECYCLE
    @Override
    public void onActivityCreated(final Activity activity, final Bundle savedInstanceState) {
        ShowLogToast.ShowLog(TAG, "########## onActivityCreated; activity=" + activity);
        if (activity == null) return;

        currentContext = activity;

        // if activity getting created for first time, we must have UI now
        if (!isUIAvailable) {
            onUIAvailable();
        }
    }

    @Override
    public void onActivityDestroyed(final Activity activity) {
        ShowLogToast.ShowLog(TAG, "########## onActivityDestroyed; activity=" + activity);
        if (activity == null) return;
    }

    @Override
    public void onActivityStarted(final Activity activity) {
        ShowLogToast.ShowLog(TAG, "########## onActivityStarted; activity=" + activity);

        this.onForeground();
    }

    @Override
    public void onActivityStopped(final Activity activity) {
        ShowLogToast.ShowLog(TAG, "########## onActivityStopped; activity=" + activity);
        this.onBackground();

    }

    @Override
    public void onActivityResumed(final Activity activity) {
        ShowLogToast.ShowLog(TAG, "########## onActivityResumed; activity=" + activity);
        if (activity == null) return;
        currentContext = activity;
    }

    @Override
    public void onActivityPaused(final Activity activity) {
        ShowLogToast.ShowLog(TAG, "########## onActivityPaused; activity=" + activity);
        if (activity == null) return;
    }

    @Override
    public void onActivitySaveInstanceState(final Activity activity, final Bundle outState) {
        ShowLogToast.ShowLog(TAG, "########## onActivitySaveInstanceState; activity=" + activity);
        if (activity == null) return;
    }

    private void clean() {

    }

    private void initialize() {
        ShowLogToast.ShowLog(TAG, "Initialize reachability manager");
        isInitialized = true;
    }


}