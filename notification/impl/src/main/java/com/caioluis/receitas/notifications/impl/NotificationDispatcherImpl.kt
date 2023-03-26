package com.caioluis.receitas.notifications.impl

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.caioluis.receitas.impl.R
import com.caioluis.receitas.notifications.bridge.NotificationDispatcher

const val CHANNEL_ID = "recipes"
const val NOTIFICATION_ID = 1

class NotificationDispatcherImpl(private val context: Context) :
    NotificationDispatcher {

    private val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_baseline_cookie_24)
        .setContentTitle("Vai uma receita?")
        .setContentText("Que tal preparar uma deliciosa receita hoje?")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    override fun dispatchNotification() {
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
    }
}