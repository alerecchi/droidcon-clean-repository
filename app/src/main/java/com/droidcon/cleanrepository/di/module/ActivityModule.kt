package com.droidcon.cleanrepository.di.module

import com.droidcon.cleanrepository.di.module.fragment.FragmentModule
import com.droidcon.cleanrepository.di.scope.ActivityScoped
import com.droidcon.cleanrepository.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun mainActivity(): MainActivity

}
