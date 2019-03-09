package com.droidcon.cleanrepository.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droidcon.cleanrepository.di.provider.ViewModelFactory
import com.droidcon.cleanrepository.di.scope.ViewModelKey
import com.droidcon.cleanrepository.presentation.doublesource.DoubleSourceFeedViewModel
import com.droidcon.cleanrepository.presentation.paginatedsource.PaginatedViewModel
import com.droidcon.cleanrepository.presentation.singlesource.SingleSourceFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SingleSourceFeedViewModel::class)
    abstract fun bindsSingleViewModel(singleSourceFeedViewModel: SingleSourceFeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DoubleSourceFeedViewModel::class)
    abstract fun bindsDoubleViewModel(doubleViewModel: DoubleSourceFeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaginatedViewModel::class)
    abstract fun bindsPaginatedViewModel(paginatedViewModel: PaginatedViewModel): ViewModel

}