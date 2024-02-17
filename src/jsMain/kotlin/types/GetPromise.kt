package types

import state.RecoilValue
import kotlin.js.Promise

/**
 * Represents a helper class for obtaining promises from Recoil values.
 */
class GetPromise
private constructor() {
    @Suppress("NOTHING_TO_INLINE")
    inline operator fun <S> invoke(recoilValue: RecoilValue<S>): Promise<S> {
        return asDynamic()(recoilValue).unsafeCast<Promise<S>>()
    }
}
