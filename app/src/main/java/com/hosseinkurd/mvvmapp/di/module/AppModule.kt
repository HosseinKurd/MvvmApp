package com.hosseinkurd.mvvmapp.di.module

import android.app.Application
import android.content.Context
import com.hosseinkurd.mvvmapp.BuildConfig
import com.hosseinkurd.mvvmapp.configs.AppConst
import com.hosseinkurd.mvvmapp.configs.AppDataManager
import com.hosseinkurd.mvvmapp.database.DbRepo
import com.hosseinkurd.mvvmapp.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.objectbox.BoxStore
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideDbRepo(boxStore: BoxStore): DbRepo {
        return DbRepo(boxStore)
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDataManager(context: Context): AppDataManager {
        return AppDataManager(context)
    }

    @Provides
    @Singleton
    internal fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
            builder.addHeader("Content-Type", "Application/json")
            /*builder.addHeader("Accept", "Application/json")
            builder.addHeader("Username", "karfarma_test")
            builder.addHeader("Password", "12345678")*/

            // builder.header("Content-Type", "Application/json")
            // builder.header("Accept", "Application/json")
            builder.header("Username", "karfarma_test")
            builder.header("Password", "12345678")
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
}