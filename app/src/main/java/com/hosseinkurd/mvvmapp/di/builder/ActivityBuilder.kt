package com.hosseinkurd.mvvmapp.di.builder

import com.hosseinkurd.mvvmapp.ui.act_main.MainActivity
import com.hosseinkurd.mvvmapp.ui.act_main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}