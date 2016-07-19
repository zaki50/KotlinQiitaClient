package org.zakky.qiitaclient.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Article(@field:PrimaryKey var id: String? = null,
                   var title: String? = null,
                   var url: String? = null,
                   var user: User? = null) : RealmModel
