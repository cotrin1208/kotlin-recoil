@file:Suppress("unused")

package util

import js.objects.jso
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.promise
import state.*
import types.Loadable
import kotlin.js.Promise

fun <T> selector(
    key: String,
    get: GetOption.() -> T
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithValue<T>> {
        this.key = key
        this.get = get
    })
}

fun <T> selector(
    key: String,
    get: GetOption.() -> T,
    set: SetOption.(T) -> Unit
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithValue<T>> {
        this.key = key
        this.get = get
        this.set = set
    })
}

fun <T> promiseSelector(
    key: String,
    get: GetOption.() -> Promise<T>
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithPromise<T>> {
        this.key = key
        this.get = get
    })
}

fun <T> loadableSelector(
    key: String,
    get: GetOption.() -> Loadable<T>
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithLoadable<T>> {
        this.key = key
        this.get = get
    })
}

fun <T> recoilValueSelector(
    key: String,
    get: GetOption.() -> RecoilValue<T>
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithRecoilValue<T>> {
        this.key = key
        this.get = get
    })
}

fun <T> suspendSelector(
    key: String,
    get: suspend GetOption.() -> T
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithPromise<T>> {
        this.key = key
        this.get = { option: GetOption ->
            MainScope().promise {
                get(option)
            }
        }
    })
}
