package com.example.optustest

import com.example.optustest.model.UserInfo
import com.example.optustest.model.UserInfoDao
import com.example.optustest.network.AppService
import com.example.optustest.ui.main.UserInfoListViewModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit


@RunWith(MockitoJUnitRunner::class)
class UserInfoListViewModelTest {

    lateinit var viewModel: UserInfoListViewModel

    @Mock
    var userInfoDao: UserInfoDao? = null

    lateinit var service: AppService

    lateinit var userInfoList: List<UserInfo>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        var gson = GsonBuilder().create()
        var listType = object : TypeToken<List<UserInfo>>() {}.type
        service = Mockito.mock<AppService>(AppService::class.java )
        viewModel = UserInfoListViewModel(userInfoDao)
        userInfoList = gson.fromJson(readJsonFile("user_info_sample_response.json"), listType)
    }

    @Test
    fun testShouldReturnExpectedResponseForGetUserInfoList() {
        Mockito.`when`(service!!.getUserInfo()).thenReturn(Observable.just(userInfoList))
        val testObserver = TestObserver<List<UserInfo>>()
        viewModel.getUserInfoList().subscribe(testObserver)
        testObserver.awaitDone(2, TimeUnit.SECONDS)
        testObserver.assertValue(userInfoList)
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.dispose()
    }

    @Test
    fun testShouldReturnErrorForGetUserInfoList() {
        Mockito.`when`(service.getUserInfo()).thenReturn(Observable.error(IllegalStateException()))
        val testObserver = TestObserver<List<UserInfo>>()
        viewModel.getUserInfoList().subscribe(testObserver)
        testObserver.awaitDone(2, TimeUnit.SECONDS)
        testObserver.assertError(IllegalStateException::class.java)
        testObserver.assertNotComplete()
        testObserver.dispose()
    }

    @Throws(IOException::class)
    fun readJsonFile(filename: String): String? {
        val stream = this.javaClass.classLoader?.getResourceAsStream(filename)
        val br = BufferedReader(InputStreamReader(stream))
        val sb = StringBuilder()
        var line = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        return sb.toString()
    }
}
