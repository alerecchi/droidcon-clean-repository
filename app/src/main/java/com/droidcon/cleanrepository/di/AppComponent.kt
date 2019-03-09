package com.droidcon.cleanrepository.di

import com.droidcon.cleanrepository.JakebookApplication
import com.droidcon.cleanrepository.di.module.*
import com.droidcon.cleanrepository.di.module.fragment.FragmentModule
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
        ApplicationModule::class,
        BindingModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        DataModule::class]
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
