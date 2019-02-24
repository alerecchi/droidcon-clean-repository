package com.droidcon.cleanrepository.di

import com.droidcon.cleanrepository.JakebookApplication
import com.droidcon.cleanrepository.di.module.ActivityModule
import com.droidcon.cleanrepository.di.module.DataSourceModule
import com.droidcon.cleanrepository.di.module.NetworkModule
import com.droidcon.cleanrepository.di.module.fragment.FragmentModule
import com.droidcon.cleanrepository.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        DataSourceModule::class]
)
interface AppComponent : AndroidInjector<JakebookApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: JakebookApplication): Builder

        fun build(): AppComponent
    }

    override fun inject(application: JakebookApplication)

}
