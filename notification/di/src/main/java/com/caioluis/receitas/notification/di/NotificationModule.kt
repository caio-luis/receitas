package com.caioluis.receitas.notification.di

import android.content.Context
import com.caioluis.receitas.notifications.bridge.NotificationChannelBuilder
import com.caioluis.receitas.notifications.bridge.NotificationDispatcher
import com.caioluis.receitas.notifications.impl.NotificationChannelBuilderImpl
import com.caioluis.receitas.notifications.impl.NotificationDispatcherImpl
import dagger.Module
import dagger.Provides

@Module
object NotificationModule {

    @[Provides JvmStatic]
    fun provideNotificationHelper(context: Context): NotificationDispatcher =
        NotificationDispatcherImpl(context)

    @[Provides JvmStatic]
    fun provideNotificationChannelBuilder(context: Context): NotificationChannelBuilder =
        NotificationChannelBuilderImpl(context)
}
