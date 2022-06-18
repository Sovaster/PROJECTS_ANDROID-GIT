package com.example.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetProvider extends AppWidgetProvider {
    private static final String ACTION_SIMPLEAPPWIDGET = "ACTION_BROADCASTWIDGETSAMPLE";
    private static String futureJokeString;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (ACTION_SIMPLEAPPWIDGET.equals(intent.getAction())) {
            new JokeLoad().execute(context);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, WidgetProvider.class);
            intent.setAction(ACTION_SIMPLEAPPWIDGET);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
            views.setOnClickPendingIntent(R.id.btnRequest, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private class JokeLoad extends AsyncTask<Context, Void, Void> {
        private Context context;

        @Override
        protected Void doInBackground(Context... contexts) {
            context = contexts[0];

            String hrefAPI = "https://api.chucknorris.io/jokes/random";
            String jsonString = getJson(hrefAPI);

            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                futureJokeString = jsonObject.getString("value");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            futureJokeString = "";
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (!futureJokeString.equals("")) {
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
                views.setTextViewText(R.id.txtJoke, futureJokeString);

                ComponentName appWidget = new ComponentName(context, WidgetProvider.class);
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                appWidgetManager.updateAppWidget(appWidget, views);
            }
        }
    }

    private String getJson(String href) {
        String data = "";

        try {
            URL url = new URL(href);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
                data = bufferedReader.readLine();
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}