@file:Suppress("NOTHING_TO_INLINE")

package wrapper.recoil.types

import wrapper.recoil.state.RecoilState

/**
 * Represents a utility class for setting the value of a Recoil value.
 */
class SetRecoilState
private constructor() {
    inline operator fun <T> invoke(recoilVal: RecoilState<T>, value: T) {
        asDynamic()(recoilVal, value)
    }

    inline operator fun <T> invoke(recoilVal: RecoilState<T>, noinline updater: (T) -> T) {
        asDynamic()(recoilVal, updater)
    }
}
