package com.ngedev.movieexpert.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngedev.movieexpert.core.databinding.RowListMovieBinding
import com.ngedev.movieexpert.core.domain.model.MovieData
import com.ngedev.movieexpert.core.util.Cons

class RecyclerAdapterMovie : RecyclerView.Adapter<RecyclerAdapterMovie.VHShow>() {

    private lateinit var binding: RowListMovieBinding
    private val mListMovie = mutableListOf<MovieData>()
    var onItemClick: ((MovieData) -> Unit)? = null

    fun setData(newListData: List<MovieData>) {
        mListMovie.clear()
        mListMovie.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHShow {
        binding = RowListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHShow(binding)
    }

    override fun onBindViewHolder(holder: VHShow, position: Int) {
        val dataShow = mListMovie[position]
        Glide.with(holder.itemView.context).load(Cons.IMG_BASE_URL + dataShow.poster_path)
            .into(holder.binding.ivMovie)
        holder.binding.tvTitle.text = dataShow.title
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(mListMovie[position])
        }
    }

    override fun getItemCount(): Int = mListMovie.size

    inner class VHShow(b: RowListMovieBinding) : RecyclerView.ViewHolder(b.root) {
        var binding = b
    }
}