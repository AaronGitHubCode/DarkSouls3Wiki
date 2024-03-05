package app.ds3wiki.activities

import app.ds3wiki.R

import app.ds3wiki.listeners.IAudioPlayer
import app.ds3wiki.listeners.INotification

import app.ds3wiki.fragments.HomeFragment

import app.ds3wiki.characters.CharacterDatabase
import app.ds3wiki.characters.characterDatabase

import android.os.Bundle

import android.app.Notification

import android.media.MediaPlayer

import android.view.Menu
import android.view.MenuItem

import android.graphics.BitmapFactory

import androidx.drawerlayout.widget.DrawerLayout

import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit

import androidx.lifecycle.lifecycleScope

import androidx.room.Room

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class MainAppActivity : AbstractBaseActivity(R.layout.main_activity_layout), IAudioPlayer, INotification {
    private var notificationPermission = false
    private var readExternalStoragePermission = false
    private var writeExternalStoragePermission = false

    private var musicPlayer = MediaPlayer()
    private var sfxPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                characterDatabase = Room.databaseBuilder(
                    applicationContext,
                    CharacterDatabase::class.java,
                    "characters_database"
                ).build()
            }
        }

        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        if (item.itemId == R.id.aside_menu)
            drawerLayout.openDrawer(androidx.core.view.GravityCompat.START)

        return true
    }

    override fun onStart(): Unit {
        super.onStart()

        supportFragmentManager.commit {
            replace(R.id.fragment_container, HomeFragment(), "home_fragment")
            addToBackStack("home_fragment")
        }
    }

    override fun onPause(): Unit {
        super.onPause()

        musicPlayer.stop()
        sfxPlayer.stop()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ): Unit {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == NOTIFICATION_REQUEST_CODE) {
            for (i in permissions.indices) {
                when (permissions[i]) {
                    android.Manifest.permission.POST_NOTIFICATIONS -> {
                        if (grantResults[i] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                            notificationPermission = true
                        }
                    }
                    android.Manifest.permission.READ_EXTERNAL_STORAGE -> {
                        if (grantResults[i] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                            readExternalStoragePermission = true
                        }
                    }
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                        if (grantResults[i] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                            writeExternalStoragePermission = true
                        }
                    }
                }
            }
        }
    }

    override fun onPlayResource(resource: Int): Unit {
        if (sfxPlayer.isPlaying)
            sfxPlayer.stop()

        sfxPlayer = MediaPlayer.create(this, resource)
        sfxPlayer.start()
    }

    override fun onNotify(
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
}