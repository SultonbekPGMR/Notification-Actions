package com.sultonbek1547.notificationfun

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.sultonbek1547.notificationfun.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val channelID = "com.sultonbek1547.notificationfun.channel1"
    private var notificationManager: NotificationManager? = null
    private val KEY_REPLY = "key_reply"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID, "DemoChannel", "this is a demo")

        binding.btnShow.setOnClickListener {
            displayNotification()
        }

    }

    private fun displayNotification() {
        val notificationId = 25
        val tapResultIntent = Intent(this, DetailsActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)


        // reply action
        val remoteInput: RemoteInput = RemoteInput.Builder(KEY_REPLY).run {
            setLabel("Insert your Name here")
            build()
        }

        val replyAction = NotificationCompat.Action.Builder(0,"REPLY",pendingIntent).addRemoteInput(remoteInput).build()

        // action button 1
        val intent2 = Intent(this, DetailsActivity::class.java)
        val pendingIntent2 =
            PendingIntent.getActivity(this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT)

        val action2 = NotificationCompat.Action.Builder(0, "Details", pendingIntent2).build()


        // action button 2
        val intent3 = Intent(this, SettingsActivity::class.java)
        val pendingIntent3 =
            PendingIntent.getActivity(this, 0, intent3, PendingIntent.FLAG_UPDATE_CURRENT)

        val action3 = NotificationCompat.Action.Builder(0, "Settings", pendingIntent3).build()


        val notification = NotificationCompat.Builder(this, channelID).setContentTitle("Demo Title")
            .setContentText("This is Demo Notification").setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(action2)
            .addAction(action3)
            .addAction(replyAction)
            .build()
        notificationManager?.notify(notificationId, notification)

    }


    private fun createNotificationChannel(id: String, name: String, channelIdDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelIdDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }


}
