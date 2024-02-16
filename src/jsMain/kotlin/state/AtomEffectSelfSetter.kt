@file:Suppress("NOTHING_TO_INLINE")

package wrapper.recoil.state

import kotlin.js.Promise

class AtomEffectSelfSetter<T>
private constructor() {
    inline operator fun invoke(value: T) {
        asDynamic()(value)
    }

    inline operator fun invoke(value: Promise<T>) {
        asDynamic()(value)
    }

    inline operator fun invoke(noinline transform: (param: T) -> T) {
        asDynamic()(transform)
    }

    inline operator fun invoke(noinline transform: (param: T) -> Promise<T>) {
        asDynamic()(transform)
    }
}
