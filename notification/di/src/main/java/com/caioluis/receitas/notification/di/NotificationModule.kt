package com.caioluis.receitas.notification.di

import android.content.Context
import com.caioluis.receitas.notifications.bridge.NotificationHelper
import com.caioluis.receitas.notifications.impl.NotificationHelperImpl
import dagger.Module
import dagger.Provides

@Module
object NotificationModule {

    @[Provides JvmStatic]
    fun provideNotificationHelper(context: Context): NotificationHelper =
        NotificationHelperImpl(context)
}
