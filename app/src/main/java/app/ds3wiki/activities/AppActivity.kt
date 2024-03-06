package app.ds3wiki.activities

import app.ds3wiki.*

import app.ds3wiki.fragments.InitFragment

import android.os.Bundle
import android.os.PersistableBundle

import android.graphics.BitmapFactory

import android.app.NotificationManager
import android.app.NotificationChannel
import android.app.Notification

import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.commit

/**
 * Dark Souls 3 Wiki Android Application
 *
 * @author AaronGitHubCode
 * @version 0.1
 * @since 0.1
 *
 * */

class AppActivity : AppCompatActivity(R.layout.app_activity_layout) {
    private lateinit var notificationManager: NotificationManager

    fun notify(
        title: String,
        content: String,
        smallIcon: Int,
        largeIcon: Int
    ): Unit = notificationManager.notify(NOTIFICATION_UNIQUE_ID, Notification.Builder(this, NOTIFICATION_ID)
        .setContentTitle(title)
        .setContentText(content)
        .setSmallIcon(smallIcon)
        .setLargeIcon(BitmapFactory.decodeResource(resources, largeIcon))
        .build()
    )

    override fun onCreate(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ): Unit {
        super.onCreate(savedInstanceState, persistentState)

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(
            NotificationChannel(
                NOTIFICATION_ID,
                NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
        )
    }

    override fun onStart(): Unit {
        super.onStart()

        supportFragmentManager.commit {
            replace(R.id.fragment_container, InitFragment(), "init_fragment")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ): Unit {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == NOTIFICATION_REQUEST_CODE) {

        }
    }
}