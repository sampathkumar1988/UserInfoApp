package com.example.optustest.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.optustest.MainActivity
import com.example.optustest.R


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?: View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        val glideUrl = GlideUrl(
            url, LazyHeaders.Builder()
                .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36"
                )
                .build()
        )
        Glide.with(view.context).load(glideUrl).placeholder(R.mipmap.place_holder).into(view)
    } else {
        view.setImageResource(R.mipmap.ic_launcher)
    }
}

@BindingAdapter("photoUrl", "photoTitle")
fun setOnImageClickEvent(
    view: View, url: String?, title: String?) {
    view.setOnClickListener{

        (view.getParentActivity() as MainActivity).goToPhotoFragment(url, title)
    }
}

@BindingAdapter("onClickUserInfo")
fun setOnUserInfoClickEvent(
    view: View, userId: String) {
    view.setOnClickListener{
        (view.getParentActivity() as MainActivity).goToPhotosListFragment(userId.toInt())
    }
}
