package com.example.optustest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.optustest.ui.main.PhotoFragment
import com.example.optustest.ui.main.PhotosListFragment
import com.example.optustest.ui.main.UserInfoFragment
import com.example.optustest.utils.PHOTO_TITLE
import com.example.optustest.utils.PHOTO_URL
import com.example.optustest.utils.USER_ID

class MainActivity : AppCompatActivity() {

    private var restoredFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            goToUserInfoFragment()
        }
    }

    fun goToUserInfoFragment() {
        var userInfoFragment = supportFragmentManager.findFragmentByTag(UserInfoFragment.TAG)
        if (userInfoFragment == null) {
            userInfoFragment = UserInfoFragment()
            replaceAndAddToBackStackFragment(userInfoFragment, UserInfoFragment.TAG)
        }
    }

    fun goToPhotosListFragment(userId: Int) {
        var photosListFragment = supportFragmentManager.findFragmentByTag(PhotosListFragment.TAG)
        if (photosListFragment == null) {
            photosListFragment = PhotosListFragment()
            val args = Bundle()
            args.putInt(USER_ID, userId)
            photosListFragment.arguments = args
            replaceAndAddToBackStackFragment(photosListFragment, PhotosListFragment.TAG)
        }
    }

    fun goToPhotoFragment(url: String?, title: String?) {
        var photoFragment = supportFragmentManager.findFragmentByTag(PhotoFragment.TAG)
        if (photoFragment == null) {
            photoFragment = PhotoFragment()
            val args = Bundle()
            args.putString(PHOTO_TITLE, title)
            args.putString(PHOTO_URL, url)
            photoFragment.arguments = args
            replaceAndAddToBackStackFragment(photoFragment, PhotoFragment.TAG)
        }
    }

    fun replaceAndAddToBackStackFragment(fragment: Fragment, tag: String?) {
        restoredFragment = null
        try {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.animator.slide_in_right, R.animator.slide_out_left,
                    R.animator.slide_in_left, R.animator.slide_out_right
                )
                .replace(R.id.container, fragment, tag)
                .addToBackStack(tag)
                .commit()
        } catch (e: IllegalStateException) {
            restoredFragment = fragment
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        }
    }

    private fun handleRestoredFragment() {
        restoredFragment?.let { replaceAndAddToBackStackFragment(it, it.tag) }
    }

    override fun onResume() {
        super.onResume()
        handleRestoredFragment()
    }
}
