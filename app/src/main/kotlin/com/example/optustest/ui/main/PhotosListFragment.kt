package com.example.optustest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.optustest.MainActivity
import com.example.optustest.R
import com.example.optustest.databinding.PhotosListFragmentBinding
import com.example.optustest.injection.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.Observer
import com.example.optustest.utils.USER_ID


class PhotosListFragment : Fragment() {

    companion object {
        val TAG: String = PhotosListFragment::class.java.simpleName
    }

    private lateinit var binding: PhotosListFragmentBinding
    private lateinit var viewModel: PhotosListViewModel
    private var errorSnackbar: Snackbar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.photos_list_fragment, container, false
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.photoList.layoutManager =
            LinearLayoutManager(activity as MainActivity, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity as MainActivity))
            .get(PhotosListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

        if (arguments!!.containsKey(USER_ID)) {
            var id = arguments!!.getInt(USER_ID)
            viewModel.bind(id)
            activity!!.title = getString(R.string.album_title, id)
        }
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

}