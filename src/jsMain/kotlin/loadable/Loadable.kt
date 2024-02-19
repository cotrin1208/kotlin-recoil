@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package loadable

import enums.LoadableState
import types.Loadable
import kotlin.js.Promise

/**
 * Represents a base interface for loadable entities.
 *
 * @param T the type of the entity being loaded
 */
sealed external interface BaseLoadable<out T> {
    @JsName("state")
    val rawState: String
    val getValue: () -> T
    val toPromise: () -> Promise<T>
    val valueOrThrow: () -> T
    val errorOrThrow: () -> Any
    val promiseOrThrow: () -> Promise<T>
    val `is`: (other: Loadable<out Any>) -> Boolean
    val map: dynamic
}

/**
 * Represents a loadable value with state and content.
 *
 * @param T the type of the content
 */
external interface ValueLoadable<out T> : BaseLoadable<T> {
    val contents: T
    val valueMaybe: () -> T
    val errorMaybe: () -> Unit
    val promiseMaybe: () -> Unit
}

/**
 * Represents a loading loadable entity.
 *
 * @param T the type of the entity.
 */
external interface LoadingLoadable<out T> : BaseLoadable<T> {
    val contents: Promise<T>
    val valueMaybe: () -> Unit
    val errorMaybe: () -> Unit
    val promiseMaybe: () -> Promise<T>
}

/**
 * Represents an error-loadable entity.
 *
 * An `ErrorLoadable` is an external interface that extends the `BaseLoadable` interface and represents an entity that can be loaded with an error state. It includes properties and
 * functions to handle the state, contents, and error of the entity.
 *
 * @property state The state of the loadable entity, which can be one of the three states defined in the `LoadableState` enum: `hasValue`, `hasError`, or `loading`.
 * @property contents The contents of the loadable entity. It can be of any type.
 * @property valueMaybe A function that is called when the value of the entity is available.
 * @property errorMaybe A function that is called when an error occurs while loading the entity.
 * @property promiseMaybe A function that is called when the promise associated with the entity is resolved.
 *
 * @see BaseLoadable
 * @see LoadableState
 */
external interface ErrorLoadable<out T> : BaseLoadable<T> {
    val contents: Any
    val valueMaybe: () -> Unit
    val errorMaybe: () -> Any
    val promiseMaybe: () -> Unit
}
