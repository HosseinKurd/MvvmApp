package com.hosseinkurd.mvvmapp.ui.act_main

import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainViewModel(): MainViewModel {
        return MainViewModel()
    }
}
