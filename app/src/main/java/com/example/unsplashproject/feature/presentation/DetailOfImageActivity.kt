package com.example.unsplashproject.feature.presentation

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.feature.domain.entity.Image
import kotlinx.android.synthetic.main.activity_detail.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import android.Manifest

const val EXTRA_IMAGE ="EXTRA_IMAGE"

class DetailOfImageActivity:AppCompatActivity() {

    companion object {
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
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                MediaStore.Images.Media.insertImage(contentResolver, bitMap, "unsplash", "desc")
                Toast.makeText(this, "Image has been downloaded!", Toast.LENGTH_SHORT).show()
            }
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