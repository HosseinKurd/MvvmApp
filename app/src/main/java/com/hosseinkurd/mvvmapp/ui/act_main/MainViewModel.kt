package com.hosseinkurd.mvvmapp.ui.act_main

import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.JsonObject
import com.hosseinkurd.mvvmapp.abstracts.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.HashMap

class MainViewModel : BaseViewModel<MainNavigator>() {

    private lateinit var subscription: Disposable
    private var compositeDisposable: CompositeDisposable? = null

    private val REQUEST_CODE_ASK_PERMISSIONS = 101

    internal fun start() {
        compositeDisposable = CompositeDisposable()
        investigatePermission()
        getNavigator()!!.activityBinding.imgBack.setOnClickListener({ getNavigator()!!.currentActivity.onBackPressed() })
        getNavigator()!!.activityBinding.btnSubmit.setOnClickListener({ checkInfo() })
    }

    fun stop() {
        compositeDisposable?.dispose()
        compositeDisposable = null
    }

    private fun checkInfo() {
        val fields: HashMap<String, String> = HashMap()
        fields.put("address", "Tehran, Iran")
        fields.put("first_name", "Hossein")
        fields.put("last_name", "Kurd")
        fields.put("coordinate_mobile", "09100614485")
        fields.put("coordinate_mobile", "0218786523")
        fields.put("lat", "37.421552")
        fields.put("lng", "-122.095095")
        fields.put("region", "1")
        fields.put("gender", "Male")
        Log.w(TAG, "OkHttp_TAG $fields")
        setUser(fields)
    }

    internal fun investigatePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    getNavigator()!!.currentActivity,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    getNavigator()!!.currentActivity,
                    arrayOf(
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), REQUEST_CODE_ASK_PERMISSIONS
                )
            }
        } else {
            Log.e(
                TAG,
                "Check WRITE EXTERNAL STORAGE Permission: No need to check permissions. SDK version <= LOLLIPOP."
            )
        }
    }

    internal fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d(
            TAG,
            "onRequestPermissionsResult: $requestCode ${Arrays.toString(permissions)} ${Arrays.toString(
                grantResults
            )}"
        )
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            for (i in permissions.indices) {
                when (permissions[i]) {
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE -> if (grantResults.size > i && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Log.d(
                            TAG,
                            "onRequestPermissionsResult --> WRITE_EXTERNAL_STORAGE PERMISSION_GRANTED"
                        )
                    }
                    android.Manifest.permission.ACCESS_COARSE_LOCATION -> if (grantResults.size > i && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        //                            Log.d(TAG, "onRequestPermissionsResult --> ACCESS_COARSE_LOCATION PERMISSION_GRANTED");
                    }
                }
            }
        }
    }

    fun setUser(fields: Map<String, String>) {
        subscription = getNavigator()!!.apiInterface.setUser(fields)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveDataStart() }
            .doOnTerminate { onRetrieveDataFinish() }
            .subscribe(this::handleResponse, this::handleError)
        compositeDisposable?.add(subscription)
    }

    private fun handleResponse(jsonObject: JsonObject) {
        Log.w(TAG, "OkHttp_TAG handleResponse : $jsonObject")
    }

    private fun handleError(error: Throwable) {
        Log.e(TAG, "OkHttp_TAG handleError: ${error.localizedMessage}")
        Log.e(TAG, "OkHttp_TAG handleError: ${error.message}", error)
    }

    private fun onRetrieveDataStart() {
        Log.w(TAG, "OkHttp_TAG onRetrieveDataStart")
    }

    private fun onRetrieveDataFinish() {
        Log.w(TAG, "OkHttp_TAG onRetrieveDataFinish")
    }

}