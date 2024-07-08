package com.caioluis.receitas.notifications.impl

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.caioluis.receitas.notification.impl.R
import com.caioluis.receitas.notifications.bridge.NotificationChannelBuilder

class NotificationChannelBuilderImpl(private val context: Context) : NotificationChannelBuilder {
    override fun createNotificationChannel() {
        context.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = getString(R.string.channel_name)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance)

                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}
