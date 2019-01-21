package com.example.james.rms.util

import android.app.Activity
import android.support.annotation.IdRes
import android.view.View

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.util
 */
fun <T : View> Activity.bind(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return unsafeLazy { findViewById<T>(idRes) }
}

fun <T : View> View.bind(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return unsafeLazy { findViewById<T>(idRes) }
}

private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)