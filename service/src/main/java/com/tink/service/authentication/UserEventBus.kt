package com.tink.service.authentication

import com.tink.service.authentication.user.User
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserEventBus @Inject constructor() {
    private val publisherSubject = BehaviorSubject.create<User>()

    fun subscribe(onUserReceived: (User) -> Unit): Disposable =
        publisherSubject.subscribe(onUserReceived)

    fun postUser(user: User) = publisherSubject.onNext(user)
}
