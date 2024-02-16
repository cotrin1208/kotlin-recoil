@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package wrapper.recoil.loadable

import wrapper.recoil.types.Loadable
import kotlin.js.Promise

/**
 * Creates a Loadable instance with the specified value.
 *
 * @param x the value to be wrapped in a Loadable
 * @return a Loadable instance containing the specified value
 * @param T the type of the value
 */
external fun <T> of(x: T): Loadable<T>

/**
 * Creates a loadable value from a promise.
 *
 * @param x the promise to create the loadable value from
 * @return the loadable value created from the promise
 */
external fun <T> of(x: Promise<T>): Loadable<T>

/**
 * Creates a new loadable value of type [T] with the provided initial value [x].
 *
 * @param x the initial value of the loadable value
 * @return a new loadable value of type [T] with the provided initial value [x]
 *
 * @see Loadable
 */
external fun <T> of(x: Loadable<T>): Loadable<T>

/**
 * Represents an error-loadable entity.
 *
 * @param x the error value to load
 * @return an ErrorLoadable instance with the provided error value
 *
 * @see ErrorLoadable
 */
external fun error(x: Any): ErrorLoadable<Any>

/**
 * Represents a method for loading a loadable entity.
 *
 * @return the loading loadable entity
 */
external fun loading(): LoadingLoadable<Any>

/**
 * Checks if the given object is loadable.
 *
 * @param x the object to check
 * @return true if the object is loadable, false otherwise
 */
external fun isLoadable(x: Any): Boolean
