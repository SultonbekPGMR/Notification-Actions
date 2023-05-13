package com.sultonbek1547.notificationfun

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.sultonbek1547.notificationfun.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        receiveInput()


    }

    private fun receiveInput() {
        val KEY_REPLY = "key_reply"
        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput != null) {
            val inputString = remoteInput.getCharSequence(KEY_REPLY).toString()
            binding.textView.text = inputString

            val channelID = "com.sultonbek1547.notificationfun.channel1"
            val notificationId = 25

            val repliedNotification = NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentText("Your reply received $inputString")
                .build()

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(notificationId,repliedNotification)

        }


    }


}