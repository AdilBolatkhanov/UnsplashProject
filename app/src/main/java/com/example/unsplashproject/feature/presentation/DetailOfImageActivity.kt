package com.example.unsplashproject.feature.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.feature.domain.entity.Image
import kotlinx.android.synthetic.main.activity_detail.*

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