package com.caioluis.receitas

import android.app.Application
import com.caioluis.receitas.notifications.bridge.NotificationChannelBuilder
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
open class RecipesApplication : Application() {

    @Inject
    lateinit var notificationChannelBuilder: NotificationChannelBuilder

    override fun onCreate() {
        super.onCreate()
        notificationChannelBuilder.createNotificationChannel()
    }
}