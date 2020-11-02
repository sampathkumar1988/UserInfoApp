package com.example.optustest.ui.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.optustest.R
import com.example.optustest.base.BaseViewModel
import com.example.optustest.model.Photo
import com.example.optustest.model.PhotoDao
import com.example.optustest.network.AppService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotosListViewModel(var photoDao: PhotoDao) : BaseViewModel() {

    @Inject
    lateinit var apis: AppService

    private lateinit var subscription: Disposable

    val photosListAdapter: PhotosListAdapter = PhotosListAdapter()
    val progress: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPhotos() }
    var userId: Int = 0

    init {
        loadPhotos()
    }

    fun bind(id: Int) {
        userId = id
    }

    private fun loadPhotos() {
        subscription = Observable.fromCallable { photoDao.all }
            .concatMap { dbPhotoList ->
                if (dbPhotoList.isEmpty())
                    apis.getPhotos().concatMap { apiPhotoList ->
                        photoDao.insertAll(*apiPhotoList.toTypedArray())
                        Observable.just(apiPhotoList)
                    }
                else
                    Observable.just(dbPhotoList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePhotoListStart() }
            .doOnTerminate { onRetrievePhotoListFinish() }
            .subscribe(
                { result -> onRetrievePhotoListSuccess(result) },
                { e -> onRetrievePhotoListError(e.message) }
            )
    }

    private fun onRetrievePhotoListStart() {
        progress.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePhotoListFinish() {
        progress.value = View.GONE
    }

    private fun onRetrievePhotoListSuccess(photoList: List<Photo>) {
        var filterList: MutableList<Photo> = mutableListOf()
        for (i in photoList.indices) {
            if (photoList[i].albumId == userId) {
                filterList.add(photoList[i])
            }
        }
        photosListAdapter.updatePhotosList(filterList)
    }

    private fun onRetrievePhotoListError(message: String?) {
        errorMessage.value = R.string.photos_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}