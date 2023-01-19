package com.example.fainalprojecct

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

private const val CHANEL_ID ="my_chanel"

class FirebaseServer : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val intent = Intent (this,StudentFamilyActivity::class.java)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random.nextInt()


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChanel(notificationManager)
        }


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivities(this,0, arrayOf(intent),FLAG_ONE_SHOT)
        val notification = NotificationCompat.Builder(this, CHANEL_ID)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["message"])
            .setSmallIcon(R.drawable.ic_baseline_edit_notifications_24)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationID,notification)
    }

    private fun createNotificationChanel(notificationManager: NotificationManager){
        val chanelName = "chanelName"
        val chanel = NotificationChannel(CHANEL_ID, chanelName , IMPORTANCE_HIGH).apply {
            description = "my chanel description"
            enableLights(true)
            lightColor = Color.DKGRAY
        }
        notificationManager.createNotificationChannel(chanel)
    }


}