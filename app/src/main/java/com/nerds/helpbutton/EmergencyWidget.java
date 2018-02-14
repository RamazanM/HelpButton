package com.nerds.helpbutton;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class EmergencyWidget extends AppWidgetProvider {

    private static final String ClickDetect="EmergencyClicked";


     void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.emergency_widget);
        views.setOnClickPendingIntent(R.id.emergencyButton,getPendingSelfIntent(context,ClickDetect));
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (ClickDetect.equals(intent.getAction())) {
            final Actions actions=new Actions(context);
            actions.sendMessage("05368925985","Acil yardımına ihtiyacım var bul beni.");

           // actions.makeCall("05368925985");
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}

