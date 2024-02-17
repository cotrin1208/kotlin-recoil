@file:Suppress("unused")

package util

import js.objects.jso
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.promise
import state.*
import kotlin.js.Promise

inline fun <T, P> transformValueGetter(crossinline get: GetOption.(P) -> T): (P) -> (GetOption) -> T {
    return { param ->
        { option: GetOption ->
            get(option, param)
        }
    }
}

inline fun <T, P> transformValueSetter(crossinline set: SetOption.(T, P) -> Unit): (P) -> (SetOption, T) -> Unit {
    return { param: P ->
        { option: SetOption, newValue: T ->
            set(option, newValue, param)
        }
    }
}

inline fun <T, P> transformPromiseGetter(crossinline get: GetOption.(P) -> Promise<T>): (P) -> (GetOption) -> Promise<T> {
    return { param: P ->
        { option: GetOption ->
            get(option, param)
        }
    }
}

inline fun <T, P> transformSuspendGetter(crossinline get: suspend GetOption.(P) -> T): (P) -> (GetOption) -> Promise<T> {
    return { param: P ->
        { option: GetOption ->
            MainScope().promise {
                get(option, param)
            }
        }
    }
}

fun <T, P> selectorFamily(
    key: String,
    get: GetOption.(P) -> T
): (P) -> RecoilValue<T> {
    return selectorFamily(jso<SelectorFamilyOptionsWithValue<T, P>> {
        this.key = key
        this.get = transformValueGetter(get)
    })
}

fun <T, P> selectorFamily(
    key: String,
    get: GetOption.(P) -> T,
    set: SetOption.(T, P) -> Unit
): (P) -> RecoilValue<T> {
    return selectorFamily(jso<SelectorFamilyOptionsWithValue<T, P>> {
        this.key = key
        this.get = transformValueGetter(get)
        this.set = transformValueSetter(set)
    })
}

fun <T, P> promiseSelectorFamily(
    key: String,
    get: GetOption.(P) -> Promise<T>
): (P) -> RecoilValue<T> {
    return selectorFamily(jso<SelectorFamilyOptionsWithPromise<T, P>> {
        this.key = key
        this.get = transformPromiseGetter(get)
    })
}

fun <T, P> promiseSelectorFamily(
    key: String,
    get: GetOption.(P) -> Promise<T>,
    set: SetOption.(T, P) -> Unit = { _, _ -> }
): (P) -> RecoilValue<T> {
    return selectorFamily(jso<SelectorFamilyOptionsWithPromise<T, P>> {
        this.key = key
        this.get = transformPromiseGetter(get)
        this.set = transformValueSetter(set)
    })
}

fun <T, P> suspendSelectorFamily(
    key: String,
    get: suspend GetOption.(P) -> T
): (P) -> RecoilValue<T> {
    return selectorFamily(jso<SelectorFamilyOptionsWithPromise<T, P>> {
        this.key = key
        this.get = transformSuspendGetter(get)
    })
}

fun <T, P> suspendSelectorFamily(
    key: String,
    get: suspend GetOption.(P) -> T,
    set: SetOption.(T, P) -> Unit
): (P) -> RecoilValue<T> {
    return selectorFamily(jso<SelectorFamilyOptionsWithPromise<T, P>> {
        this.key = key
        this.get = transformSuspendGetter(get)
        this.set = transformValueSetter(set)
    })
}
