package com.example.optustest.ui.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.optustest.R
import com.example.optustest.base.BaseViewModel
import com.example.optustest.model.UserInfo
import com.example.optustest.model.UserInfoDao
import com.example.optustest.network.AppService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserInfoListViewModel(var userInfoDao: UserInfoDao?) : BaseViewModel() {

    @Inject
    lateinit var apis: AppService

    private lateinit var subscription: Disposable

    val userInfoListAdapter: UserInfoListAdapter = UserInfoListAdapter()
    val progress: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadUserInfos() }

/*
    init {
        loadUserInfos()
    }
*/

    fun loadUserInfos() {
        subscription = Observable.fromCallable { userInfoDao?.all }
            .concatMap { dbUserInfoList ->
                if (dbUserInfoList.isEmpty())
                    getUserInfoList().concatMap { apiUserInfoList ->
                        userInfoDao?.insertAll(*apiUserInfoList.toTypedArray())
                        Observable.just(apiUserInfoList)
                    }
                else
                    Observable.just(dbUserInfoList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveUsrInfoListStart() }
            .doOnTerminate { onRetrieveUserInfoListFinish() }
            .subscribe(
                { result -> onRetrieveUserInfoListSuccess(result) },
                { e -> onRetrieveUserInfoListError(e.message) }
            )
    }

    fun getUserInfoList(): Observable<List<UserInfo>> {
        var list = apis.getUserInfo()
        return apis.getUserInfo()
    }

    private fun onRetrieveUsrInfoListStart() {
        progress.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveUserInfoListFinish() {
        progress.value = View.GONE
    }

    private fun onRetrieveUserInfoListSuccess(userInfoList: List<UserInfo>) {
        userInfoListAdapter.updateUserInfoList(userInfoList)
    }

    private fun onRetrieveUserInfoListError(message: String?) {
        errorMessage.value = R.string.userinfo_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}
