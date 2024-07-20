package app.alextran.immich

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import android.widget.RemoteViews
import es.antonborri.home_widget.HomeWidgetBackgroundIntent
import es.antonborri.home_widget.HomeWidgetLaunchIntent
import es.antonborri.home_widget.HomeWidgetProvider

class PhotoHomeScreenWidgetProvider : HomeWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray, widgetData: SharedPreferences) {
        appWidgetIds.forEach { widgetId ->
            val views = RemoteViews(context.packageName, R.layout.photo_home_screen_widget).apply {
                // Open App on Widget Click
                val pendingIntent = HomeWidgetLaunchIntent.getActivity(
                        context,
                        MainActivity::class.java)
                setOnClickPendingIntent(R.id.widget_container, pendingIntent)

                val message = widgetData.getString("message", null)
                setTextViewText(R.id.widget_message, message
                        ?: "No Message Set")
                // Show Images saved with `renderFlutterWidget`
                val image = widgetData.getString("dashIcon", null)
                if (image != null) {
                 setImageViewBitmap(R.id.widget_img, BitmapFactory.decodeFile(image))
                 setViewVisibility(R.id.widget_img, View.VISIBLE)
                } else {
                    setViewVisibility(R.id.widget_img, View.GONE)
                }

            }

            appWidgetManager.updateAppWidget(widgetId, views)
        }
    }
}
