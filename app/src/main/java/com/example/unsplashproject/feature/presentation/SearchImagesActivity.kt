package com.example.unsplashproject.feature.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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
import com.example.unsplashproject.api.RELEVANT
import com.example.unsplashproject.feature.domain.entity.Image
import com.example.unsplashproject.feature.presentation.adapter.ImagesAdapter
import com.example.unsplashproject.feature.presentation.adapter.PhotoClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.imagesRecyclerView
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchImagesActivity : AppCompatActivity(), PhotoClickListener{
    private val viewModel: SearchImagesViewModel by viewModel()
    private val adapter = ImagesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(searchToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        imagesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        imagesRecyclerView.adapter = adapter

        listeners()
        observeStates()
    }

    private fun observeStates() {
        viewModel.status.observe(this, Observer {resources->
            searchProgressBar.isVisible = resources is Resource.Loading
            if (resources is Resource.Error){
                showError(resources.message)
            }
        })
        viewModel.detailsActivity.observe(this, Observer {
            if (it != null){
                startActivity(DetailOfImageActivity.getStartIntent(this, it))
            }
        })
    }

    private fun listeners() {
        searchBtn.setOnClickListener {
            viewModel.onSearchClicked(queryEt.text.toString())
            queryEt.hideKeyboard()
            observePhotos()
        }

        searchSwipeRefresh.setOnRefreshListener {
            viewModel.updatePhotos()
            observePhotos()
            searchSwipeRefresh.isRefreshing = false
        }
    }

    private fun observePhotos(){
        viewModel.photos.observe(this, Observer {
            adapter.submitList(it)
        })
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

    private fun showSortingPopupMenu(){
        val view = findViewById<View>(R.id.sort_images) ?: return
        PopupMenu(this, view).run {
            menuInflater.inflate(R.menu.search_sort_by_menu, menu)

            setOnMenuItemClickListener {
                viewModel.setSorting(
                    when (it.itemId) {
                        R.id.latest -> LATEST
                        else -> RELEVANT
                    }
                )
                observePhotos()
                true
            }
            show()
        }
    }

    override fun onClick(photo: Image) {
        viewModel.onPhotoClicked(photo)
    }

    private fun showError(error: String){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    fun EditText.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}