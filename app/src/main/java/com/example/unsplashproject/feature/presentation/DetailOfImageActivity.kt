package com.example.unsplashproject.feature.presentation

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.feature.domain.entity.Image
import kotlinx.android.synthetic.main.activity_detail.*

const val EXTRA_IMAGE ="EXTRA_IMAGE"


class DetailOfImageActivity:AppCompatActivity() {

    companion object {
        const val CHANNEL_ID ="CHANNEL_99"
        const val NOTIFICATION_ID = 999
        fun getStartIntent(context: Context, image: Image): Intent {
            return Intent(context, DetailOfImageActivity::class.java)
                .putExtra(EXTRA_IMAGE, image)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val image: Image = intent.getParcelableExtra(EXTRA_IMAGE)!!
        setContent(image)
        listeners()
    }

    private fun listeners(){
        downloadBtn.setOnClickListener {
            val bitMap = photoImageView.drawable.toBitmap()
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1)
                }
            } else {
                MediaStore.Images.Media.insertImage(contentResolver, bitMap, "unsplash", "desc")
                createNotification()
            }
        }
    }

    private fun createNotification(){
        createNotificationChannel()

        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.type = "image/*"
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_cloud_download_24)
            .setContentTitle("Unsplash")
            .setContentText("Image has been downloaded!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channel_11"
            val descriptionText = "our channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun setContent(image: Image){
        Glide.with(this).load(image.user_photo_url).into(userProfileImage)
        Glide.with(this).load(image.full_url).into(photoImageView)
        userNameTextView.text = image.name
        userLoginTextView.text = image.username
        likesTextView.text = "${image.likes}"
        locationTextView.text = image.location
        descriptionTextView.text = image.description
    }
}