package com.example.unsplashproject.feature.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.unsplashproject.R
import com.example.unsplashproject.api.LATEST
import com.example.unsplashproject.api.OLDEST
import com.example.unsplashproject.api.POPULAR
import com.example.unsplashproject.api.RELEVANT
import com.example.unsplashproject.feature.domain.entity.Image
import com.example.unsplashproject.feature.presentation.adapter.ImagesAdapter
import com.example.unsplashproject.feature.presentation.adapter.PhotoClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_activity.*
import kotlinx.android.synthetic.main.search_activity.imagesRecyclerView
import kotlinx.android.synthetic.main.search_activity.mainProgressBar
import kotlinx.android.synthetic.main.search_activity.swipeRefresh
import kotlinx.android.synthetic.main.search_activity.toolbar
import org.koin.android.viewmodel.ext.android.viewModel

class SearchImageActivity: AppCompatActivity(), PhotoClickListener {
    private val viewModel: SearchImageViewModel by viewModel()
    private val adapter = ImagesAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        imagesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        imagesRecyclerView.adapter = adapter

        listeners()
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
            menuInflater.inflate(R.menu.search_sort_by, menu)

            setOnMenuItemClickListener {
                viewModel.setSoring(
                    when (it.itemId) {
                        R.id.relevant -> RELEVANT
                        else -> LATEST
                    }
                )
                observeItems()
                true
            }
            show()
        }
    }

    private fun listeners(){
        swipeRefresh.setOnRefreshListener {
            viewModel.updateImages()
            observeItems()
            swipeRefresh.isRefreshing = false
        }

        searchBtn.setOnClickListener {
            viewModel.searchImages(queryEt.text.toString())
            observeItems()
            queryEt.hideKeyboard()
        }
    }

    private fun observe(){
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

    private fun observeItems() {
        viewModel.photos.observe(this, Observer {
            adapter.submitList(it)
        })
    }


    private fun showError(error: String){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    fun EditText.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}