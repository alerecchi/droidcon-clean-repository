package com.droidcon.cleanrepository.di.module

import android.content.Context
import com.droidcon.cleanrepository.JakebookApplication
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: JakebookApplication): Context

}