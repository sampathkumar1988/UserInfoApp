package com.example.optustest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.optustest.R
import com.example.optustest.databinding.ItemPhotosBinding
import com.example.optustest.model.Photo

class PhotosListAdapter() : RecyclerView.Adapter<PhotosListAdapter.ViewHolder>() {
    private lateinit var photoList: List<Photo>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotosListAdapter.ViewHolder {
        val binding: ItemPhotosBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_photos,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosListAdapter.ViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount(): Int {
        return if (::photoList.isInitialized) photoList.size else 0
    }

    fun updatePhotosList(photoList: List<Photo>) {
        this.photoList = photoList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PhotosViewModel()


        fun bind(photo: Photo) {
            viewModel.bind(photo)
            binding.viewModel = viewModel
        }
    }
}