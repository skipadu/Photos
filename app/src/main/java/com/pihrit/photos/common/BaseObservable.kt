package com.pihrit.photos.common

import java.util.*
import java.util.concurrent.ConcurrentHashMap

abstract class BaseObservable<ListenerType> {

    private val mListeners = Collections.newSetFromMap(ConcurrentHashMap<ListenerType, Boolean>(1))

    protected val listeners: Set<ListenerType>
        get() = Collections.unmodifiableSet(mListeners)

    fun registerListener(listener: ListenerType) {
        mListeners.add(listener)
    }

    fun unregisterListener(listener: ListenerType) {
        mListeners.remove(listener)
    }
}