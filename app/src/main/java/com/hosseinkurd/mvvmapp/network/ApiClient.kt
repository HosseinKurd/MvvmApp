package com.hosseinkurd.mvvmapp.network


import com.hosseinkurd.loginsample.database.UserData
import com.hosseinkurd.mvvmapp.BuildConfig
import com.hosseinkurd.mvvmapp.configs.AppConst
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        fun getDefaultClient(): Retrofit {
            val interceptor = Interceptor { chain ->
                val original = chain.request()
                val builder = original.newBuilder()
                // builder.addHeader("Accept", "application/json");
                builder.addHeader("Content-Type", "Application/json")
                builder.addHeader("Username", "karfarma_test")
                builder.addHeader("Password", "12345678")
                // builder.header("X-DEVELOPER", "KURDIA")
                val request = builder.method(original.method(), original.body()).build()
                chain.proceed(request)
            }

            val httpLoggingInterceptor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG) {
                // set your desired log level
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            val client = OkHttpClient.Builder()
                .connectTimeout(AppConst.connectTimeOut, TimeUnit.SECONDS)
                .readTimeout(AppConst.readTimeOut, TimeUnit.SECONDS)
                .writeTimeout(AppConst.writeTimeOut, TimeUnit.SECONDS)
                // .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024))
                .addNetworkInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(AppConst.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        fun getLoggedInClient(token: String): Retrofit {
            val interceptor = Interceptor { chain ->
                val original = chain.request()
                val builder = original.newBuilder()
                // builder.addHeader("Accept", "application/json");
                builder.addHeader("Content-Type", "Application/json")
                builder.addHeader("Username", "karfarma_test")
                builder.addHeader("Password", "12345678")
                // builder.header("X-DEVELOPER", "KURDIA")
                val request = builder.method(original.method(), original.body()).build()
                chain.proceed(request)
            }

            val httpLoggingInterceptor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG) {
                // set your desired log level
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            val client = OkHttpClient.Builder()
                .connectTimeout(AppConst.connectTimeOut, TimeUnit.SECONDS)
                .readTimeout(AppConst.readTimeOut, TimeUnit.SECONDS)
                .writeTimeout(AppConst.writeTimeOut, TimeUnit.SECONDS)
                // .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024))
                .addNetworkInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(AppConst.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
    }
}