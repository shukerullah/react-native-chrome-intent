package com.shukerullah.intent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.facebook.react.bridge.*;

public class AndroidIntentModule extends ReactContextBaseJavaModule {

    public final String REACT_CLASS = "AndroidIntent";

    private final ReactApplicationContext reactContext;

    public AndroidIntentModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void openIntent(String dataUri, final Promise promise) {
        Intent sendIntent;
        PackageManager packageManager = this.reactContext.getPackageManager();

        try {
            sendIntent = Intent.parseUri(dataUri, Intent.URI_INTENT_SCHEME);
            sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // try to find activity that can handle the chrome intent
            ResolveInfo info = packageManager.resolveActivity(sendIntent, 0);

            // if activity is found, meaning not null
            if (info != null) {
                this.reactContext.startActivity(sendIntent);
                promise.resolve(true);
                return;
            }

            // if activity not found, load fallback URL from chrome intent
            String fallbackUrl = sendIntent.getStringExtra("browser_fallback_url");
            if(fallbackUrl != null) {
                Intent fallbackUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fallbackUrl));
                this.reactContext.startActivity(fallbackUrlIntent);
                promise.resolve(true);
                return;
            }

            promise.resolve(false);
        } catch (Exception e) {
            e.printStackTrace();
            promise.resolve(false);
        }
    }
}