package es.npatarino.android.gotchallenge.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable?.addTo(compositeDisposable: CompositeDisposable) {
    this?.also { compositeDisposable.add(it) }
}
