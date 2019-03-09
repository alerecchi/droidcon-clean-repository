package com.droidcon.cleanrepository.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droidcon.cleanrepository.di.provider.ViewModelFactory
import com.droidcon.cleanrepository.di.qualifier.ViewModelKey
import com.droidcon.cleanrepository.presentation.doublesource.DoubleFeedViewModel
import com.droidcon.cleanrepository.presentation.pagedsource.PagedViewModel
import com.droidcon.cleanrepository.presentation.singlesource.SingleFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SingleFeedViewModel::class)
    abstract fun bindsSingleViewModel(singleViewModel: SingleFeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DoubleFeedViewModel::class)
    abstract fun bindsDoubleViewModel(doubleViewModel: DoubleFeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PagedViewModel::class)
    abstract fun bindspagedViewModel(pagedViewModel: PagedViewModel): ViewModel

}