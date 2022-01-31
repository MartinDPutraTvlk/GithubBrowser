package com.martinezdputra.githubbrowser.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martinezdputra.githubbrowser.di.annotation.ViewModelKey
import com.martinezdputra.githubbrowser.factory.ViewModelFactory
import com.martinezdputra.githubbrowser.ui.homepage.HomepageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomepageViewModel::class)
    abstract fun bindHomepageViewModel(homepageViewModel: HomepageViewModel): ViewModel
}