package com.example.optustest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.optustest.MainActivity
import com.example.optustest.R
import com.example.optustest.databinding.PhotoFragmentBinding
import com.example.optustest.injection.ViewModelFactory
import com.example.optustest.utils.PHOTO_TITLE
import com.example.optustest.utils.PHOTO_URL

class PhotoFragment : Fragment() {

    companion object {
        val TAG: String = PhotoFragment::class.java.simpleName
    }

    private lateinit var binding: PhotoFragmentBinding
    private lateinit var viewModel: PhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.photo_fragment, container, false
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity as MainActivity))
            .get(PhotoViewModel::class.java)
        binding.viewModel = viewModel

        if (arguments!!.containsKey(PHOTO_TITLE)) {
            viewModel.bind(arguments!!.getString(PHOTO_TITLE), arguments!!.getString(PHOTO_URL))
        }
    }
}