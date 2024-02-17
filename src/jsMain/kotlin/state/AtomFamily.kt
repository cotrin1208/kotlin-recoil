@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package state

/**
 * Represents the options for an atom family.
 *
 * @param T The type of the value that the atom family holds.
 * @param P The type of the parameter that the atom family function takes.
 */
sealed external interface AtomFamilyOptions<T, P>

/**
 * Represents a set of options for creating an AtomFamily without a default value.
 *
 * @param T the type of the value stored in the AtomFamily
 * @param P the type of the parameter used to create an atom instance
 */
external interface AtomFamilyOptionsWithoutDefault<T, P> : AtomFamilyOptions<T, P> {
    var key: String
}

/**
 * Represents the options for an AtomFamily with a default value.
 *
 * @param T The type of the atom's value.
 * @param P The type of the parameter for the atom.
 */
external interface AtomFamilyOptionsWithDefault<T, P> : AtomFamilyOptionsWithoutDefault<T, P> {
    var default: T
}

/**
 * Creates an atom family with the given options.
 *
 * @param options The options for creating the atom family.
 * @return A function that accepts a parameter of type `P` and returns a `RecoilState<T>`.
 */
external fun <T, P> atomFamily(options: AtomFamilyOptions<T, P>): (param: P) -> RecoilState<T>
