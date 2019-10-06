package com.hosseinkurd.mvvmapp.configs

import android.app.Activity
import android.app.Application
import com.crashlytics.android.Crashlytics
import com.hosseinkurd.mvvmapp.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.calligraphy3.R
import io.github.inflationx.viewpump.ViewPump
import javax.inject.Inject


class App : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        val fabric = Fabric.Builder(this)
            .kits(Crashlytics())
            .debuggable(true)
            .build()
        Fabric.with(fabric)
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        initCustomFont()
    }

    private fun initCustomFont() {
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/VazirRegular.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )
    }
}