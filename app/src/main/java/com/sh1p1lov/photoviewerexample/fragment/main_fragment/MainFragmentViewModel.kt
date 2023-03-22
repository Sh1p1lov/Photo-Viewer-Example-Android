package com.sh1p1lov.photoviewerexample.fragment.main_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sh1p1lov.photoviewerexample.model.ImageData

class MainFragmentViewModel : ViewModel() {

    private val mutableImagesLiveData = MutableLiveData<List<ImageData>>(emptyList())
    val imagesLiveData: LiveData<List<ImageData>> = mutableImagesLiveData

    fun setImages(images: List<ImageData>) {
        mutableImagesLiveData.value = images
    }
}