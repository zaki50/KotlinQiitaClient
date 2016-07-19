package org.zakky.qiitaclient

import android.app.Application
import org.zakky.qiitaclient.dagger.AppComponent
import org.zakky.qiitaclient.dagger.DaggerAppComponent

class QiitaClientApp : Application() {
    companion object {
        lateinit var application: QiitaClientApp
    }

    val component: AppComponent by lazy {
        DaggerAppComponent.create()
    }

    override fun onCreate() {
        super.onCreate()
        QiitaClientApp.application = this
    }
}