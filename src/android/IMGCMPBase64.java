package com.imgcmpbase64.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import java.io.ByteArrayOutputStream;

public class IMGCMPBase64 extends CordovaPlugin {
	private CallbackContext callbackContext;
	public IMGCMPBase64() {
	}

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals("convertToWebP")) {
			try{
            	String base64String = args.getString(0); // Get the base64 string here
            	convertToWebP(base64String, callbackContext);
			} catch (Exception e) {
				Log.e("Error", e.toString());
			}
            return true;
        }
        return false;
    }

    private void convertToWebP(final String base64String, final CallbackContext callbackContext) {
        if (base64String != null && !base64String.isEmpty()) {
            cordova.getThreadPool().execute(new Runnable() {
				@Override
				public void run(){
                try {
                    // Decode the base64 string to a byte array
                    byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);

                    // Decode the byte array to a bitmap
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, options);

                    // Convert the bitmap to WebP
                    byte[] webpData = convertBitmapToWebP(bitmap);

                    // Return the converted WebP data as a base64 string
                    String base64Data = Base64.encodeToString(webpData, Base64.DEFAULT);
                    Log.i("base64Data WebP ==> ", base64Data);

                    // Send the result to the JavaScript side
                    JSONObject result = new JSONObject();
                    result.put("data", base64Data);
                    callbackContext.success(result);
                } catch (Exception exception) {
                    Log.e("WebPConvertorPlugin", "Error converting image to WebP", exception);
                    callbackContext.error("Error converting image to WebP: " + exception.getMessage());
                }
				}
            });
        } else {
            Log.e("WebPConvertorPlugin", "Base64 string is null or empty");
            callbackContext.error("Base64 string is null or empty");
        }
    }

    private byte[] convertBitmapToWebP(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Convert the bitmap to WebP format with quality 20
        boolean success = bitmap.compress(Bitmap.CompressFormat.WEBP, 20, outputStream);

        if (success) {
            return outputStream.toByteArray();
        } else {
            throw new RuntimeException("Error converting bitmap to WebP");
        }
    }
}