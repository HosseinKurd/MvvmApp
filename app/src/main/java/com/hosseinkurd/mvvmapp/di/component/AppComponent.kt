package com.hosseinkurd.mvvmapp.di.component

import android.app.Application
import com.hosseinkurd.mvvmapp.configs.App
import com.hosseinkurd.mvvmapp.di.builder.ActivityBuilder
import com.hosseinkurd.mvvmapp.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {        @BindsInstance
    fun application(application: Application): Builder

        fun build(): AppComponent

    }
}