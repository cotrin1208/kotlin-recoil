@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package wrapper.recoil.state

import wrapper.recoil.types.GetRecoilValue
import wrapper.recoil.types.Loadable
import wrapper.recoil.types.ResetRecoilState
import wrapper.recoil.types.SetRecoilState
import kotlin.js.Promise

/**
 * Represents an external interface for the GetOption class.
 */
external interface GetOption {
    val get: GetRecoilValue
}

/**
 * Represents a set of options for managing Recoil values.
 */
external interface SetOption {
    val set: SetRecoilState
    val get: GetRecoilValue
    val reset: ResetRecoilState
}

/**
 * Represents options for a selector function.
 *
 * @param T the type parameter for the value of the selector function.
 */
external interface SelectorOptions<T> {
    var key: String
    var set: (option: SetOption, newValue: T) -> Unit
}

/**
 * SelectorOptionsWithPromise is an external interface that extends SelectorOptions.
 * It represents the options for a selector with a Promise return value.
 *
 * @param T the type parameter for the value of the selector option.
 *
 * @property get a function that takes a GetOption and returns a Promise of type T.
 *
 * @see SelectorOptions
 * @see GetOption
 */
external interface SelectorOptionsWithPromise<T> : SelectorOptions<T> {
    var get: (option: GetOption) -> Promise<T>
}

/**
 * Represents the options for a selector with a Recoil value.
 *
 * @param T the type parameter for the Recoil value.
 */
external interface SelectorOptionsWithRecoilValue<T> : SelectorOptions<T> {
    var get: (option: GetOption) -> RecoilValue<T>
}

/**
 * A selector option that extends [SelectorOptions].
 *
 * @param T the type of the value
 */
external interface SelectorOptionsWithLoadable<T> : SelectorOptions<T> {
    var get: (option: GetOption) -> Loadable<T>
}

/**
 * Represents a SelectorOptionsWithValue interface which extends the SelectorOptions interface.
 *
 * @param T the type parameter for the value of the SelectorOptionsWithValue.
 */
external interface SelectorOptionsWithValue<T> : SelectorOptions<T> {
    var get: (option: GetOption) -> T
}

/**
 * Retrieves a Recoil state based on the provided options.
 *
 * @param options the options for the selector
 * @return the Recoil state
 * @param T the type parameter for the value of the Recoil state
 */
external fun <T> selector(options: SelectorOptions<T>): RecoilState<T>
