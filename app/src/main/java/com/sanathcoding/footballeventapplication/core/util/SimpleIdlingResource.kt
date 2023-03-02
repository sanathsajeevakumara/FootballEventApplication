package com.sanathcoding.footballeventapplication.core.util

import androidx.annotation.Nullable
import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

class SimpleIdlingResource : IdlingResource {
    @Nullable
    @Volatile
    private var mCallback: IdlingResource.ResourceCallback? = null

    private val mIsIdleNow = AtomicBoolean(true)

    init {
        setIdleState(isIdleNow)
    }

    override fun getName(): String {
        return this.javaClass.name
    }

    override fun isIdleNow(): Boolean {
        return mIsIdleNow.get()
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        mCallback = callback
    }

    private fun setIdleState(isIdleNow: Boolean) {
        mIsIdleNow.set(isIdleNow)
        if (isIdleNow && mCallback != null) {
            mCallback!!.onTransitionToIdle()
        }
    }
}