package wrapper.recoil.types

import wrapper.recoil.state.RecoilValue

/**
 * Represents a utility class for getting the value of a Recoil value.
 */
class GetRecoilValue
private constructor() {
    @Suppress("NOTHING_TO_INLINE")
    inline operator fun <T> invoke(recoilValue: RecoilValue<T>): T {
        return asDynamic()(recoilValue).unsafeCast<T>()
    }
}
