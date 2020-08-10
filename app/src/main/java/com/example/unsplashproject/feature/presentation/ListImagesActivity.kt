package com.example.unsplashproject.feature.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.unsplashproject.R
import com.example.unsplashproject.api.LATEST
import com.example.unsplashproject.api.OLDEST
import com.example.unsplashproject.api.POPULAR
import com.example.unsplashproject.feature.domain.entity.Image
import com.example.unsplashproject.feature.presentation.adapter.ImagesAdapter
import com.example.unsplashproject.feature.presentation.adapter.PhotoClickListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListImagesActivity : AppCompatActivity(),PhotoClickListener {
    private val viewModel: ListImagesViewModel by viewModel()
    private val adapter = ImagesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        imagesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        imagesRecyclerView.adapter = adapter

        swipeRefresh.setOnRefreshListener {
            viewModel.updatePhotos()
            swipeRefresh.isRefreshing = false
        }

        observe()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.sort_images){
            showSortingPopupMenu()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(photo: Image) {
        viewModel.onPhotoClicked(photo)
    }

    private fun showSortingPopupMenu(){
        val view = findViewById<View>(R.id.sort_images) ?: return
        PopupMenu(this, view).run {
            menuInflater.inflate(R.menu.sort_by_menu, menu)

            setOnMenuItemClickListener {
                viewModel.setSorting(
                    when (it.itemId) {
                        R.id.latest -> LATEST
                        R.id.oldest -> OLDEST
                        else -> POPULAR
                    }
                )
                true
            }
            show()
        }
    }

    private fun observe(){
        viewModel.photos.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.status.observe(this, Observer {resources->
            mainProgressBar.isVisible = resources is Resource.Loading
            if (resources is Resource.Error){
                showError(resources.message)
            }
        })
        viewModel.nextActivity.observe(this, Observer {
            if (it != null){
                startActivity(DetailOfImageActivity.getStartIntent(this, it))
            }
        })
    }

    private fun showError(error: String){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }


}