package org.zakky.qiitaclient.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import org.zakky.qiitaclient.QiitaClientApp
import javax.inject.Singleton

@Module
class RealmModule {
    @Provides
    fun provideApplication(): Application = QiitaClientApp.application

    @Provides
    @Singleton
    fun provideRealmConfiguration(app: Application): RealmConfiguration =
            RealmConfiguration.Builder(app).build()

    @Provides
    fun provideRealm(conf: RealmConfiguration): Realm = Realm.getInstance(conf)
}
