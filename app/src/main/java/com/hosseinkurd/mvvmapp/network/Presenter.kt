package com.hosseinkurd.mvvmapp.network

interface Presenter<V> {

    fun onCallbackAttached(callback: V)

    fun onCallbackDetached()

}