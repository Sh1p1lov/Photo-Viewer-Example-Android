package com.sh1p1lov.photoviewerexample.fragment.main_fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sh1p1lov.photoviewerexample.R
import com.sh1p1lov.photoviewerexample.databinding.FragmentMainBinding
import com.sh1p1lov.photoviewerexample.model.ImageData
import com.sh1p1lov.photoviewerexample.recyclerview_adapter.ImagesAdapter
import com.sh1p1lov.photoviewerexample.recyclerview_adapter.ItemOffsetDecoration

class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        private const val TAG = "MainFragmentLog"
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val imagesAdapter by lazy { ImagesAdapter() }
    private val vm by lazy { ViewModelProvider(this)[MainFragmentViewModel::class.java] }

    private val openDocumentTreeResult = registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) {
        it?.let { uri ->
            Log.d(TAG, "$uri")
            val documentsTree = DocumentFile.fromTreeUri(requireContext().applicationContext, uri) ?: return@let
            val childDocuments = documentsTree.listFiles().filter { file -> Regex("image/.*").matches(file.type.toString()) }
            val images = childDocuments.map { imageFile -> ImageData(imageFile.uri) }
            vm.setImages(images)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        binding.recyclerView.adapter = imagesAdapter
        binding.recyclerView.addItemDecoration(ItemOffsetDecoration(requireContext(), R.dimen.item_offset))

        vm.imagesLiveData.observe(viewLifecycleOwner) {
            imagesAdapter.setImages(it)
        }

        binding.imgBtnFolder.setOnClickListener {
            openDocumentTreeResult.launch(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}