package com.sh1p1lov.photoviewerexample.recyclerview_adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sh1p1lov.photoviewerexample.databinding.ImageListItemBinding
import com.sh1p1lov.photoviewerexample.model.ImageData

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImagesAdapterViewHolder>() {

    private var images = emptyList<ImageData>()

    companion object {
        private const val SIZE_MULTIPLIER = 0.5f
    }

    class ImagesAdapterViewHolder(
        val binding: ImageListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setImages(images: List<ImageData>) {
        this.images = images
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesAdapterViewHolder {
        val binding = ImageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesAdapterViewHolder, position: Int) {
        Glide.with(holder.binding.root.context)
            .load(images[position].uri)
            .sizeMultiplier(SIZE_MULTIPLIER)
            .dontAnimate()
            .centerCrop()
            .into(holder.binding.image)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}