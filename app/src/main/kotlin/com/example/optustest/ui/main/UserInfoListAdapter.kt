package com.example.optustest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.optustest.R
import com.example.optustest.databinding.ItemUserInfoBinding
import com.example.optustest.model.UserInfo

class UserInfoListAdapter : RecyclerView.Adapter<UserInfoListAdapter.ViewHolder>() {
    private lateinit var userInfoList: List<UserInfo>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserInfoListAdapter.ViewHolder {
        val binding: ItemUserInfoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user_info,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserInfoListAdapter.ViewHolder, position: Int) {
        holder.bind(userInfoList[position])
    }

    override fun getItemCount(): Int {
        return if (::userInfoList.isInitialized) userInfoList.size else 0
    }

    fun updateUserInfoList(userInfoList: List<UserInfo>) {
        this.userInfoList = userInfoList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemUserInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = UserInfoViewModel()

        fun bind(userInfo: UserInfo) {
            viewModel.bind(userInfo)
            binding.viewModel = viewModel
        }
    }
}