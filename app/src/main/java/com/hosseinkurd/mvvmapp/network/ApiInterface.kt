package com.hosseinkurd.mvvmapp.network

import com.google.gson.JsonObject
import com.hosseinkurd.loginsample.database.UserData
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST("/api/karfarmas/address")
    fun setUser(@FieldMap fields: Map<String, String>): Observable<JsonObject>
    // fun setUser(@FieldMap fields: Map<String, String>): Observable<UserData>

    @GET("/posts")
    fun getUser(): Observable<List<UserData>>
}