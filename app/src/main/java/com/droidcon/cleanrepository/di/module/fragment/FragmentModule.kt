package com.droidcon.cleanrepository.di.module.fragment

import com.droidcon.cleanrepository.di.scope.FragmentScoped
import com.droidcon.cleanrepository.presentation.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment

}
