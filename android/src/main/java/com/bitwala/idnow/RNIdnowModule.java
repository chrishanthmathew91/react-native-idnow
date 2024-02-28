
package com.bitwala.idnow;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;

import de.idnow.sdk.IDnowSDK;

public class RNIdnowModule extends ReactContextBaseJavaModule{
    private final ReactApplicationContext reactContext;
    private Promise idnowPromise;
    private static final String TAG = "idnow";

    private final ActivityEventListener idnowActivityEventListener = new BaseActivityEventListener() {
        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
            if (requestCode == IDnowSDK.REQUEST_ID_NOW_SDK) {
                Log.d(TAG, "---onActivityResult for idnow ---");
                if (idnowPromise != null){
                    switch (resultCode) {
                        case IDnowSDK.RESULT_CODE_SUCCESS:
                            Log.d(TAG, "---success ---");
                            idnowPromise.resolve(true);
                            break;
                        case IDnowSDK.RESULT_CODE_CANCEL:
                            Log.d(TAG, "---cancelled ---");
                            idnowPromise.reject("CANCELLED", "Identification canceled");
                            break;
                        case IDnowSDK.RESULT_CODE_FAILED:
                            Log.d(TAG, "---failed ---");
                            idnowPromise.reject("FAILED", "Identification failed");
                            break;
                        default:
                            Log.d(TAG, "---default error block ---");
                            idnowPromise.reject("INTERNAL_ERROR", "Internal error: ");
                    }
                }else{
                    Log.e(TAG, "---promise is null ---");
                }
            }
        }
    };


    public RNIdnowModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        reactContext.addActivityEventListener(idnowActivityEventListener);
    }

    @Override
    public String getName() {
        return "RNIdnow";
    }

    @ReactMethod
    public void startVideoIdent(final ReadableMap options, final Promise promise) {
        Activity currentActivity = getCurrentActivity();
        idnowPromise = promise;

        try {
            IDnowSDK.getInstance().initialize(currentActivity, options.getString("companyId"));
            IDnowSDK.setShowVideoOverviewCheck(options.getBoolean("showVideoOverviewCheck"), reactContext);
            IDnowSDK.setShowErrorSuccessScreen(options.getBoolean("showErrorSuccessScreen"), reactContext);
            IDnowSDK.setTransactionToken(options.getString("transactionToken"));
            Log.d(TAG, "---starting idnow ---");
            IDnowSDK.getInstance().start(IDnowSDK.getTransactionToken());
        } catch (Exception e) {
            promise.reject("ERR_UNEXPECTED_EXCEPTION", e);
        }
    }

}


//      IDnowSDK.setEnvironment(IDnowSDK.Server.LIVE);
//      IDnowSDK.enableLogging();
//      IDnowSDK.setNameForActionBar(null, reactContext);
//      IDnowSDK.setLocale(reactContext, "de");
//      Log.d(TAG, String.format("calledFromIDnowApp = %b", IDnowSDK.calledFromIDnowApp(reactContext)));
//      Log.d(TAG, String.format("getNameForActionBar = %s", IDnowSDK.getNameForActionBar(reactContext)));
//      Log.d(TAG, String.format("isShowVideoOverviewCheck = %b", IDnowSDK.isShowVideoOverviewCheck(reactContext)));
//      Log.d(TAG, String.format("getCompanyId = %s", IDnowSDK.getCompanyId()));
//      Log.d(TAG, String.format("getTransactionToken = %s", IDnowSDK.getTransactionToken()));
//      Log.d(TAG, String.format("getEnvironment = %s", IDnowSDK.getEnvironment()));
//      Log.d(TAG, String.format("isLoggingEnabled = %b", IDnowSDK.isLoggingEnabled()));