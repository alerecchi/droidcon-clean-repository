package com.droidcon.cleanrepository.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droidcon.cleanrepository.di.provider.ViewModelFactory
import com.droidcon.cleanrepository.di.qualifier.ViewModelKey
import com.droidcon.cleanrepository.presentation.doublesource.DoubleViewModel
import com.droidcon.cleanrepository.presentation.pagedsource.PagedViewModel
import com.droidcon.cleanrepository.presentation.singlesource.SingleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SingleViewModel::class)
    abstract fun bindsSingleViewModel(singleViewModel: SingleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DoubleViewModel::class)
    abstract fun bindsDoubleViewModel(doubleViewModel: DoubleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PagedViewModel::class)
    abstract fun bindspagedViewModel(pagedViewModel: PagedViewModel): ViewModel

}