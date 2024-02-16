@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package wrapper.recoil.state

/**
 * Represents a Recoil value.
 *
 * @param T the type parameter for the Recoil value
 */
sealed external interface RecoilValue<T>

/**
 * Represents a Recoil state, which is a specific type of Recoil value.
 *
 * @param T the type parameter for the value of the Recoil state.
 */
external class RecoilState<T> : RecoilValue<T>

/**
 * Represents a read-only Recoil value.
 *
 * @param T the type of value contained in the Recoil value.
 */
external class RecoilValueReadOnly<T>: RecoilValue<T>
