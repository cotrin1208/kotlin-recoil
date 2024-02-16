@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package wrapper.recoil.state

import js.array.ReadonlyArray
import wrapper.recoil.enums.AtomEffectTrigger
import wrapper.recoil.types.*
import kotlin.js.Promise

/**
 * Represents the options for configuring an atom effect.
 *
 * @param T the type of the atom value
 */
external interface AtomEffectOption<T> {
    val node: RecoilState<T>
    val trigger: AtomEffectTrigger
    val setSelf: AtomEffectSelfSetter<T>
    val resetSelf: () -> Unit
    @JsName("onSet")
    val rawOnSet: AtomEffectOnSet<T>
    val getPromise: GetPromise
    val getLoadable: GetLoadable
}

/**
 * Represents the options for an Atom in Recoil.
 *
 * @param T the type of the atom's value
 */
sealed external interface AtomOptions<T>

/**
 * This interface defines the options for creating an atom without a default value.
 *
 * @param T the type of the atom value
 */
external interface AtomOptionsWithoutDefault<T> : AtomOptions<T> {
    var key: String
    var dangerouslyAllowMutability: Boolean?
    var effects: ReadonlyArray<AtomEffect<T>>?
}

/**
 * Represents the options for creating an atom with a default value provided as a [RecoilValue].
 * Inherits from [AtomOptionsWithoutDefault].
 * @param T The type of the atom value.
 */
external interface AtomOptionsWithRecoilValue<T> : AtomOptionsWithoutDefault<T> {
    var default: RecoilValue<T>
}

/**
 * An external interface representing the options for creating an atom with a default value that is a Promise.
 * Inherits the properties from `AtomOptionsWithoutDefault` interface.
 */
external interface AtomOptionsWithPromise<T> : AtomOptionsWithoutDefault<T> {
    var default: Promise<T>
}

/**
 * An external interface representing the options for an atom with a loadable default value.
 *
 * @param T the type of the atom value
 */
external interface AtomOptionsWithLoadable<T> : AtomOptionsWithoutDefault<T> {
    var default: Loadable<T>
}

/**
 * An external interface that represents the options for an atom with a default value.
 * Inherits from the AtomOptionsWithoutDefault interface.
 *
 * @param T the type of the atom value.
 */
external interface AtomOptionsWithDefault<T> : AtomOptionsWithoutDefault<T> {
    var default: T
}

/**
 * Creates a new Recoil state atom with the specified options.
 *
 * @param options the options for the atom
 * @return the created Recoil state atom
 * @throws Throwable if an error occurs during atom creation
 */
external fun <T> atom(options: AtomOptions<T>): RecoilState<T>
