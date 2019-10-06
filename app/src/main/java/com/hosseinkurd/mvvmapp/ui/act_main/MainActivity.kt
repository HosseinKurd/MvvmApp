package com.hosseinkurd.mvvmapp.ui.act_main

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.hosseinkurd.mvvmapp.BR
import com.hosseinkurd.mvvmapp.R
import com.hosseinkurd.mvvmapp.abstracts.BaseActivity
import com.hosseinkurd.mvvmapp.databinding.ActivityMainBinding
import com.hosseinkurd.mvvmapp.network.ApiInterface
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    @Inject
    lateinit var api: ApiInterface

    @Inject
    internal lateinit var mViewModel: MainViewModel

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel
        get() = mViewModel
    override val activityBinding: ActivityMainBinding
        get() = viewDataBinding!!
    override val currentActivity: Activity
        get() = this
    override val apiInterface: ApiInterface
        get() = api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w(TAG, "onCreate.")
        viewModel.setNavigator(this)
        viewModel.start()
    }

    override fun onStop() {
        viewModel.stop()
        super.onStop()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        viewModel.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}