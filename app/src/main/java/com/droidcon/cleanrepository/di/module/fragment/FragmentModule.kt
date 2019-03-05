package com.droidcon.cleanrepository.di.module.fragment

import com.droidcon.cleanrepository.di.scope.FragmentScoped
import com.droidcon.cleanrepository.presentation.MenuFragment
import com.droidcon.cleanrepository.presentation.doublesource.DoubleSourceFragment
import com.droidcon.cleanrepository.presentation.paginatedsource.PaginatedSourceFragment
import com.droidcon.cleanrepository.presentation.singlesource.SingleSourceFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun menuFragment(): MenuFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun singleFragment(): SingleSourceFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun doubleFragment(): DoubleSourceFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun paginatedFragment(): PaginatedSourceFragment

}
