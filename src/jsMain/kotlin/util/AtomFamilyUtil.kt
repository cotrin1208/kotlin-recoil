@file:Suppress("unused")

package util

import js.objects.jso
import state.AtomFamilyOptionsWithDefault
import state.AtomFamilyOptionsWithoutDefault
import state.RecoilState
import state.atomFamily

/**
 * Creates an atom family with the given key.
 *
 * @param key The key used to identify the atom family.
 * @return A function that accepts a parameter of type `P` and returns a `RecoilState<T>`.
 * @param <T> The type parameter for the value of the Recoil state returned by the atom family.
 * @param <P> The type parameter for the parameter accepted by the atom family function.
 */
fun <T, P> atomFamily(key: String): (param: P) -> RecoilState<T> {
    return atomFamily(jso<AtomFamilyOptionsWithoutDefault<T, P>> {
        this.key = key
    })
}

/**
 * Creates an atom family with the given key and default value.
 *
 * @param key The key for the atom family.
 * @param default The default value for the atoms created by the atom family.
 * @return A function that accepts a parameter of type `P` and returns a `RecoilState<T>`.
 * @param <T> The type of value that the atom family holds.
 * @param <P> The type of parameter that the atom family function takes.
 */
fun <T, P> atomFamily(key: String, default: T): (param: P) -> RecoilState<T> {
    return atomFamily(jso<AtomFamilyOptionsWithDefault<T, P>> {
        this.key = key
        this.default = default
    })
}
