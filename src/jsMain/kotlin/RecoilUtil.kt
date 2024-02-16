@file:Suppress("unused")

package wrapper.recoil

import js.objects.jso
import wrapper.recoil.state.*
import wrapper.recoil.types.AtomEffect
import wrapper.recoil.types.Loadable
import kotlin.js.Promise

/**
 * Creates a new Recoil state atom with the specified key and optional effects.
 *
 * @param key the unique identifier for the atom
 * @param effects optional array of effects to associate with the atom
 * @return the created Recoil state atom
 */
fun <T> atom(key: String, effects: Array<AtomEffect<T>>? = null): RecoilState<T> {
    return atom(jso<AtomOptionsWithoutDefault<T>> {
        this.key = key
        this.effects = effects
    })
}

/**
 * Creates a new Recoil state atom with the specified key and default value.
 *
 * @param key the key for the atom
 * @param default the default value for the atom
 * @return the created Recoil state atom
 * @throws Throwable if an error occurs during atom creation
 * @param <T> the type of the atom's value
 */
fun <T> atom(key: String, default: RecoilValue<T>, effects: Array<AtomEffect<T>>? = null): RecoilState<T> {
    return atom(jso<AtomOptionsWithRecoilValue<T>> {
        this.key = key
        this.default = default
        this.effects = effects
    })
}

/**
 * Creates a new Recoil state atom with the specified key and default value.
 *
 * @param key the key for the atom
 * @param default the default value for the atom as a Promise
 * @return the created Recoil state atom
 * @throws Throwable if an error occurs during atom creation
 * @param T the type parameter for the value of the Recoil state
 */
fun <T> atom(key: String, default: Promise<T>, effects: Array<AtomEffect<T>>? = null): RecoilState<T> {
    return atom(jso<AtomOptionsWithPromise<T>> {
        this.key = key
        this.default = default
        this.effects = effects
    })
}

/**
 * Creates a new Recoil state atom with the specified key and default value.
 *
 * @param key the identifier for the atom
 * @param default the default value for the atom
 * @return the created Recoil state atom
 * @throws Throwable if an error occurs during atom creation
 * @param T the type parameter for the value of the Recoil state atom
 */
fun <T> atom(key: String, default: Loadable<T>, effects: Array<AtomEffect<T>>? = null): RecoilState<T> {
    return atom(jso<AtomOptionsWithLoadable<T>> {
        this.key = key
        this.default = default
        this.effects = effects
    })
}

/**
 * Creates a new Recoil state atom with the specified key and default value.
 *
 * @param key the unique key for the atom
 * @param default the default value for the atom
 * @return the created Recoil state atom
 * @throws Throwable if an error occurs during atom creation
 */
fun <T> atom(key: String, default: T, effects: Array<AtomEffect<T>>? = null): RecoilState<T> {
    return atom(jso<AtomOptionsWithDefault<T>> {
        this.key = key
        this.default = default
        this.effects = effects
    })
}

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

/**
 * Retrieves a Recoil state based on the provided options.
 *
 * @param key the key for the selector
 * @param get a function that retrieves the value from the provided GetOption
 * @return the Recoil state with the specified value type
 * @param T the type parameter for the value of the Recoil state
 */
fun <T> selector(
    key: String,
    get: GetOption.() -> T
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithValue<T>> {
        this.key = key
        this.get = get
    })
}

/**
 * Retrieves a Recoil state based on the provided key and get function.
 *
 * @param key the key used to identify the Recoil state
 * @param get a function that takes a GetOption and returns a Promise of type T
 * @return the Recoil state
 * @param T the type parameter for the value of the Recoil state
 */
fun <T> promiseSelector(
    key: String,
    get: GetOption.() -> Promise<T>
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithPromise<T>> {
        this.key = key
        this.get = get
    })
}

/**
 * Retrieves a Recoil state based on the provided key and get function.
 *
 * @param key the unique key for the Recoil state
 * @param get the function that returns the value of the Recoil state
 * @return the Recoil state
 * @param T the type parameter for the value of the Recoil state
 */
fun <T> loadableSelector(
    key: String,
    get: GetOption.() -> Loadable<T>
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithLoadable<T>> {
        this.key = key
        this.get = get
    })
}

/**
 * Retrieves a Recoil state based on the provided key and get function.
 *
 * @param key the key for the Recoil state
 * @param get the function that retrieves the Recoil value based on the GetOption
 * @return the Recoil state
 * @param T the type parameter for the value of the Recoil state
 */
fun <T> recoilValueSelector(
    key: String,
    get: GetOption.() -> RecoilValue<T>
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithRecoilValue<T>> {
        this.key = key
        this.get = get
    })
}

/**
 * Retrieves a Recoil state based on the provided options.
 *
 * @param key the unique key for the Recoil state
 * @param get a function that takes a GetOption as parameter and returns the value of the Recoil state
 * @param set (optional) a function that takes a SetOption and the new value and sets the Recoil state to the new value
 * @return the Recoil state
 * @param T the type parameter for the value of the Recoil state
 */
fun <T> selector(
    key: String,
    get: GetOption.() -> T,
    set: SetOption.(T) -> Unit
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithValue<T>> {
        this.key = key
        this.get = get
        this.set = set
    })
}

/**
 * Retrieves a Recoil state based on the provided options.
 *
 * @param key the key for the selector
 * @param get the function that takes a GetOption and returns a Promise of type T
 * @param set (optional) the function that takes an option and a value and sets the Recoil state
 * @return the Recoil state
 * @param T the type parameter for the value of the Recoil state
 */
fun <T> promiseSelector(
    key: String,
    get: GetOption.() -> Promise<T>,
    set: SetOption.(T) -> Unit
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithPromise<T>> {
        this.key = key
        this.get = get
        this.set = set
    })
}

/**
 * Retrieves a Recoil state based on the provided options.
 *
 * @param key the key of the selector state
 * @param get a function that takes a GetOption and returns a Loadable value
 * @param set an optional function that takes a SetOption and a value of type T, and updates the selector state
 * @return the Recoil state of type T
 * @param T the type parameter for the value of the Recoil state
 */
fun <T> loadableSelector(
    key: String,
    get: GetOption.() -> Loadable<T>,
    set: SetOption.(T) -> Unit
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithLoadable<T>> {
        this.key = key
        this.get = get
        this.set = set
    })
}

/**
 * Retrieves a Recoil state based on the provided options.
 *
 * @param key the key for the Recoil state
 * @param get a function that takes a GetOption parameter and returns a Recoil value
 * @param set an optional function that takes a SetOption parameter and a value, and sets the Recoil state
 * @return the Recoil state
 * @param T the type parameter for the value of the Recoil state
 */
fun <T> recoilValueSelector(
    key: String,
    get: GetOption.() -> RecoilValue<T>,
    set: SetOption.(T) -> Unit
): RecoilState<T> {
    return selector(jso<SelectorOptionsWithRecoilValue<T>> {
        this.key = key
        this.get = get
        this.set = set
    })
}

/**
 * Represents an atom effect.
 *
 * @param T the type of the atom value
 * @param effect the effect to be executed
 * @return the atom effect
 */
fun <T> atomEffect(effect: AtomEffectOption<T>.() -> Unit): AtomEffect<T> {
    return effect
}

fun <T> AtomEffectOption<T>.onSet(callback: AtomEffectOnSetOption<T>.() -> Unit) {
    this.rawOnSet { newValue, oldValue, isReset ->
        callback(object : AtomEffectOnSetOption<T> {
            override val newValue: T = newValue
            override val oldValue: T = oldValue
            override val isReset: Boolean = isReset
        })
    }
}

interface AtomEffectOnSetOption<T> {
    val newValue: T
    val oldValue: T
    val isReset: Boolean
}

