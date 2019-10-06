package com.hosseinkurd.mvvmapp.ui.act_main

import android.app.Activity
import com.hosseinkurd.mvvmapp.databinding.ActivityMainBinding
import com.hosseinkurd.mvvmapp.network.ApiInterface
import io.reactivex.disposables.Disposable


interface MainNavigator {
    val apiInterface: ApiInterface
    val activityBinding: ActivityMainBinding
    val currentActivity: Activity
}