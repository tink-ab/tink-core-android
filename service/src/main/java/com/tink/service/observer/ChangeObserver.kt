package com.tink.service.observer

interface ChangeObserver<T> {
    fun onCreate(items: T)
    fun onRead(items: T)
    fun onUpdate(items: T)
    fun onDelete(items: T)
}
