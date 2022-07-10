package com.caioluis.receitas.notifications.impl

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.caioluis.receitas.impl.R
import com.caioluis.receitas.notifications.bridge.NotificationHelper

const val CHANNEL_ID = "recipes"
const val NOTIFICATION_ID = 1

class NotificationHelperImpl(private val context: Context) :
    NotificationHelper {
    init {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        context.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = getString(R.string.channel_name)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance)
                // Register the channel with the system
                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    private val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_baseline_cookie_24)
        .setContentTitle("Vai uma receita?")
        .setContentText("Que tal preparar uma deliciosa receita hoje?")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    override fun dispatchNotification() {
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
    }
}