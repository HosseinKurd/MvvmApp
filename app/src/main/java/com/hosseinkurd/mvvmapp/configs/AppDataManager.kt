package com.hosseinkurd.mvvmapp.configs

import android.content.Context

import com.hosseinkurd.kurdiautils.toolbox.helpers.CacheHelper
import com.hosseinkurd.mvvmapp.R

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(private val mContext: Context) {

    private var cacheHelper: CacheHelper
    var userName: String = ""
        get() {
            if (field.isEmpty()) {
                return cacheHelper.getString(mContext, mContext.getString(R.string.shp_token), "")
            }
            return field
        }
        set(value) {
            cacheHelper.apply(mContext, mContext.getString(R.string.shp_username), value)
            field = value
        }

    val isLoggedIn: Boolean
        get() = !cacheHelper.getString(
            mContext,
            mContext.getString(R.string.shp_token),
            ""
        ).isEmpty()

    init {
        this.cacheHelper = CacheHelper()
    }

    fun setToken(token: String) {
        cacheHelper.apply(mContext, mContext.getString(R.string.shp_token), token)
    }

    fun getToken() {
        cacheHelper.getString(mContext, mContext.getString(R.string.shp_token), "")
    }
}