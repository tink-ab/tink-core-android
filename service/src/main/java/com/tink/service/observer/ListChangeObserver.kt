package com.tink.service.observer

interface ChangeObserver<T> {
    fun onCreate(items: T)
    fun onRead(items: T)
    fun onUpdate(items: T)
    fun onDelete(items: T)
}

/**
 * Like a [ChangeObserver], but specifically working with lists of the specified type.
 */
@Deprecated(message = "Replace with 'ChangeObserver'", replaceWith = ReplaceWith("ChangeObserver<List<T>>"))
interface ListChangeObserver<T> {
    fun onCreate(items: List<T>)
    fun onRead(items: List<T>)
    fun onUpdate(items: List<T>)
    fun onDelete(items: List<T>)
}