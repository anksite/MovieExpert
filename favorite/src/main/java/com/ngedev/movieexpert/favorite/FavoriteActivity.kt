package com.ngedev.movieexpert.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.ngedev.movieexpert.core.ui.RecyclerAdapterMovie
import com.ngedev.movieexpert.core.util.Cons
import com.ngedev.movieexpert.di.FavoriteModuleDependencies
import com.ngedev.movieexpert.favorite.databinding.ActivityFavoriteBinding
import com.ngedev.movieexpert.view.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val mAdapter = RecyclerAdapterMovie()

    @Inject
    lateinit var factory: ViewModelFactory

    private val vmFavorite: VMFavorite by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = GridLayoutManager(this, 2)

        binding.rvListMovie.apply {
            this.adapter = mAdapter
            this.layoutManager = layoutManager
        }

        vmFavorite.listFavorite.observe({ lifecycle }, {
            mAdapter.setData(it)
            binding.pbLoad.visibility = View.GONE
        })

        mAdapter.onItemClick = {
            val i = Intent(applicationContext, DetailActivity::class.java)
            i.putExtra(Cons.MOVIE_DATA, it)
            startActivity(i)
        }
    }
}