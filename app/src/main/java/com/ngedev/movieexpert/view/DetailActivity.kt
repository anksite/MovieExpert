package com.ngedev.movieexpert.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.RequestManager
import com.ngedev.movieexpert.R
import com.ngedev.movieexpert.core.domain.model.MovieData
import com.ngedev.movieexpert.core.util.Cons
import com.ngedev.movieexpert.databinding.ActivityDetailBinding
import com.ngedev.movieexpert.view.vm.VMMovieDetail
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val vmShow: VMMovieDetail by viewModels()
    private lateinit var mMenuFavorite: MenuItem
    private var mIsFavMovie = false

    private var mMovieData: MovieData? = null

    @Inject
    lateinit var mGlide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Movie Detail"

        mMovieData = intent.getParcelableExtra(Cons.MOVIE_DATA)

        mMovieData?.let {
            binding.apply {
                mGlide.load(Cons.IMG_BASE_URL + it.poster_path).into(ivPoster)

                tvTitle.text = it.title
                tvDate.text = if(it.release_date.isNullOrBlank()) "Unknown" else it.release_date
                tvScore.text = it.vote_average
                tvOverview.text = it.overview

                vmShow.checkMovieFavorite(it.title)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        mMenuFavorite = menu?.findItem(R.id.menu_favorite)!!
        obsertIsFaforite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()

            R.id.menu_favorite -> {
                if (mIsFavMovie) {
                    mMovieData?.let { vmShow.deleteFavorite(it) }
                    Toast.makeText(applicationContext, "Remove From Favorite", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    mMovieData?.let { vmShow.insertFavorite(it) }
                    Toast.makeText(applicationContext, "Add to Favorite", Toast.LENGTH_SHORT).show()
                }
                invalidateOptionsMenu()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun obsertIsFaforite() {
        vmShow.isFavorite.observe({ lifecycle }, {
            mIsFavMovie = it
            if (mIsFavMovie) {
                mMenuFavorite.setIcon(R.drawable.i_fav_fill)
            } else {
                mMenuFavorite.setIcon(R.drawable.i_fav)
            }
        })
    }
}