package com.example.optustest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.optustest.MainActivity
import com.example.optustest.R
import com.example.optustest.databinding.UserinfoListFragmentBinding
import com.example.optustest.injection.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class UserInfoFragment : Fragment() {

    companion object {
        val TAG: String = UserInfoFragment::class.java.simpleName
    }

    private lateinit var binding: UserinfoListFragmentBinding
    private lateinit var viewModel: UserInfoListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.userinfo_list_fragment, container, false
        )
        activity!!.title = getString(R.string.user_info_title)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.userinfoList.layoutManager =
            LinearLayoutManager(activity as MainActivity, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity as MainActivity))
            .get(UserInfoListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
        viewModel.loadUserInfos()
    }

    override fun onResume() {
        super.onResume()
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
