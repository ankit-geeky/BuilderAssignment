package com.builder.assignment.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class NetworkUtils {
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static void showNoInternetToast(Context context) {
        Toast.makeText(context, "Can't connect to internet", Toast.LENGTH_LONG).show();
    }
}
