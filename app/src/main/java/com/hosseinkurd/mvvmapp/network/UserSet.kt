package com.hosseinkurd.mvvmapp.network

import io.reactivex.disposables.Disposable
import retrofit2.http.FieldMap

class UserSet : Presenter<UserCallback> {
    override fun onCallbackAttached(callback: UserCallback) {

    }

    override fun onCallbackDetached() {
        subscription.dispose()
    }

    private var callback: UserCallback? = null
    private lateinit var subscription: Disposable

    fun setUser(@FieldMap fields: Map<String, String>) {
        ApiClient.getDefaultClient().create(ApiInterface::class.java).setUser(fields)
    }

}