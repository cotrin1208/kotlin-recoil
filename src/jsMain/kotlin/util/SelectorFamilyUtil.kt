@file:Suppress("unused")

package util

import js.objects.jso
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.promise
import state.*
import kotlin.js.Promise

fun <T, P> selectorFamily(
    key: String,
    get: (P) -> GetOption.() -> T
): (P) -> RecoilValue<T> {
    return selectorFamily(jso<SelectorFamilyOptionsWithValue<T, P>> {
        this.key = key
        this.get = get
    })
}

fun <T, P> selectorFamily(
    key: String,
    get: (P) -> GetOption.() -> T,
    set: (P) -> SetOption.(T) -> Unit
): (P) -> RecoilValue<T> {
    return selectorFamily(jso<SelectorFamilyOptionsWithValue<T, P>> {
        this.key = key
        this.get = get
        this.set = set
    })
}

fun <T, P> promiseSelectorFamily(
    key: String,
    get: (P) -> GetOption.() -> Promise<T>
): (P) -> RecoilValue<T> {
    return selectorFamily(jso<SelectorFamilyOptionsWithPromise<T, P>> {
        this.key = key
        this.get = get
    })
}

fun <T, P> promiseSelectorFamily(
    key: String,
    get: (P) -> GetOption.() -> Promise<T>,
    set: (P) -> SetOption.(T) -> Unit
): (P) -> RecoilValue<T> {
    return selectorFamily(jso<SelectorFamilyOptionsWithPromise<T, P>> {
        this.key = key
        this.get = get
        this.set = set
    })
}

fun <T, P> suspendSelectorFamily(
    key: String,
    get: (P) -> (suspend GetOption.() -> T)
): (P) -> RecoilValue<T> {
    return selectorFamily(jso<SelectorFamilyOptionsWithPromise<T, P>> {
        this.key = key
        this.get = { param: P ->
            { option: GetOption ->
                MainScope().promise {
                    get(param)(option)
                }
            }
        }
    })
}

fun <T, P> suspendSelectorFamily(
    key: String,
    get: (P) -> (suspend GetOption.() -> T),
    set: (P) -> (suspend SetOption.(T) -> Unit)
): (P) -> RecoilValue<T> {
    return selectorFamily(jso<SelectorFamilyOptionsWithPromise<T, P>> {
        this.key = key
        this.get = { param: P ->
            { option: GetOption ->
                MainScope().promise {
                    get(param)(option)
                }
            }
        }
        this.set = { param: P ->
            { option: SetOption, newValue: T ->
                MainScope().promise {
                    set(param)(option, newValue)
                }
            }
        }
    })
}
