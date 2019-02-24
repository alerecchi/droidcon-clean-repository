package com.droidcon.cleanrepository

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.droidcon.cleanrepository.di.AppComponent
import com.droidcon.cleanrepository.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class JakebookApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun activityInjector() = activityInjector

}
