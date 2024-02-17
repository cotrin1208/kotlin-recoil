@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package state

import kotlin.js.Promise

external interface SelectorFamilyOptions<T, P> {
    var key: String
    var set: ((P) -> (option: SetOption, newValue: T) -> Unit)?
}

external interface SelectorFamilyOptionsWithValue<T, P>: SelectorFamilyOptions<T, P> {
    var get: (P) -> (option: GetOption) -> T
}

external interface SelectorFamilyOptionsWithPromise<T, P>: SelectorFamilyOptions<T, P> {
    var get: (P) -> (option: GetOption) -> Promise<T>
}

external fun <T, P> selectorFamily(options: SelectorFamilyOptions<T, P>): (P) -> RecoilState<T>
