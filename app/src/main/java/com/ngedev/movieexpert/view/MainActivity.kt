package com.ngedev.movieexpert.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.ngedev.movieexpert.R
import com.ngedev.movieexpert.core.ui.RecyclerAdapterMovie
import com.ngedev.movieexpert.core.util.Cons
import com.ngedev.movieexpert.databinding.ActivityMainBinding
import com.ngedev.movieexpert.view.vm.VMMovie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mAdapter: RecyclerAdapterMovie

    private val vmMovie: VMMovie by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAdapter = RecyclerAdapterMovie()
        val layoutManager = GridLayoutManager(this, 2)

        binding.rvListMovie.apply {
            this.adapter = mAdapter
            this.layoutManager = layoutManager
        }

        vmMovie.listMovie.observe({ lifecycle }, {
            mAdapter.setData(it)
            binding.pbLoad.visibility = View.GONE
        })

        mAdapter.onItemClick = {
            val i = Intent(applicationContext, DetailActivity::class.java)
            i.putExtra(Cons.MOVIE_DATA, it)
            startActivity(i)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_fav -> {
                val uri = Uri.parse("movieexpert://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}