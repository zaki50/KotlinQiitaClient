package org.zakky.qiitaclient

import android.content.Context
import android.support.annotation.IdRes
import android.view.View
import android.widget.Toast
import io.realm.RealmModel
import io.realm.RealmQuery
import kotlin.reflect.KCallable

fun <T : View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun <E: RealmModel> RealmQuery<E>.equalTo(field: KCallable<*>, value: String): RealmQuery<E> {
    return equalTo(field.name, value)
}
