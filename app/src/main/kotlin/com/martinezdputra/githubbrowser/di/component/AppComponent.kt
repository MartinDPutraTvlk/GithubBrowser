package com.martinezdputra.githubbrowser.di.component

import android.app.Application
import android.content.Context
import com.martinezdputra.githubbrowser.di.annotation.ApplicationScope
import com.martinezdputra.githubbrowser.di.module.ApiModule
import com.martinezdputra.githubbrowser.di.module.AppModule
import com.martinezdputra.githubbrowser.di.module.RepositoryModule
import com.martinezdputra.githubbrowser.di.module.ViewModelModule
import com.martinezdputra.githubbrowser.ui.homepage.HomepageActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    AppModule::class,
    ViewModelModule::class,
    ApiModule::class,
    RepositoryModule::class
])
@ApplicationScope
interface AppComponent {

    fun getApplicationContext(): Context

    fun getApplication(): Application

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun inject(homepageActivity: HomepageActivity)
}