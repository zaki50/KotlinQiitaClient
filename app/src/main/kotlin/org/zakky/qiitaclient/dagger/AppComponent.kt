package org.zakky.qiitaclient.dagger

import dagger.Component
import org.zakky.qiitaclient.ArticleActivity
import org.zakky.qiitaclient.MainActivity
import javax.inject.Singleton

@Component(modules = arrayOf(ClientModule::class, RealmModule::class))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(articleActivity: ArticleActivity)
}