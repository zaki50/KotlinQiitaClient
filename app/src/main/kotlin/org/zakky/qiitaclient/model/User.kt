package org.zakky.qiitaclient.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class User(@field:PrimaryKey var id: String? = null,
                var name: String? = null,
                var profileImageUrl: String? = null) : RealmModel