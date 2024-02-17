@file:Suppress("NOTHING_TO_INLINE")

package types

import kotlin.reflect.KProperty

/**
 * Represents a function that sets the value of a Recoil state.
 * This class is generic to support different data types.
 * Usage:
 * ```
 * val setter = RecoilStateSetter<T>()
 * setter(value)*/
class RecoilStateSetter<T>
private constructor() {
    inline operator fun invoke(value: T) {
        asDynamic()(value)
    }

    inline operator fun invoke(noinline transform: (T) -> T) {
        asDynamic()(transform)
    }
}

/**
 * Represents an instance of a Recoil state.
 *
 * @param T the type parameter for the value of the Recoil state.
 */
class RecoilStateInstance<T>
private constructor() {
    inline operator fun component1(): T = asDynamic()[0] as T
    inline operator fun component2(): RecoilStateSetter<T> = asDynamic()[1].unsafeCast<RecoilStateSetter<T>>()

    inline operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): T {
        return asDynamic()[0] as T
    }

    inline operator fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: T
    ) {
        asDynamic()[1](value)
    }
}

/**
 * Represents an instance of a loadable state for a Recoil value.
 *
 * @param T The type of the value.
 */
class LoadableStateInstance<T>
private constructor() {
    inline operator fun component1(): Loadable<T> = asDynamic()[0].unsafeCast<Loadable<T>>()
    inline operator fun component2(): RecoilStateSetter<T> = asDynamic()[1].unsafeCast<RecoilStateSetter<T>>()

    inline operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): Loadable<T> {
        return asDynamic()[0].unsafeCast<Loadable<T>>()
    }

    inline operator fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: T
    ) {
        asDynamic()[1](value)
    }
}
