package com.caioluis.receitas.notifications.bridge

interface NotificationDispatcher {
    fun dispatchNotification()
}

interface NotificationChannelBuilder {
    fun createNotificationChannel()
}