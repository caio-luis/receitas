package com.caioluis.receitas.notification.di

import android.content.Context
import com.caioluis.receitas.notifications.bridge.NotificationChannelBuilder
import com.caioluis.receitas.notifications.bridge.NotificationDispatcher
import com.caioluis.receitas.notifications.impl.NotificationChannelBuilderImpl
import com.caioluis.receitas.notifications.impl.NotificationDispatcherImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
object NotificationModule {

    @[Provides ActivityScoped]
    fun provideNotificationHelper(
        @ApplicationContext context: Context
    ): NotificationDispatcher = NotificationDispatcherImpl(context)

    @[Provides ActivityScoped]
    fun provideNotificationChannelBuilder(
        @ApplicationContext context: Context
    ): NotificationChannelBuilder = NotificationChannelBuilderImpl(context)
}
