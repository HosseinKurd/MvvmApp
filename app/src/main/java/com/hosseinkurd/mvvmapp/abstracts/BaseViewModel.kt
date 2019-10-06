package com.hosseinkurd.mvvmapp.abstracts

import androidx.lifecycle.ViewModel

import java.lang.ref.WeakReference

abstract class BaseViewModel<N> : ViewModel() {
    protected val TAG = javaClass.simpleName + "_TAG"
    private lateinit var mNavigator: WeakReference<N>

    fun getNavigator(): N? {
        return mNavigator.get()
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }

    /* var navigator: N?
        get() = mNavigator.get()
        set(navigator) {
            this.mNavigator = WeakReference(navigator!!)
        }*/

    override fun onCleared() {
        super.onCleared()
    }

}