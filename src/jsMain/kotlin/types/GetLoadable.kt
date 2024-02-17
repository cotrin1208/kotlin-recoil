package types

import state.RecoilValue

/**
 * Represents a utility class for obtaining the Loadable value of a RecoilValue.
 *
 * This class provides a single operator function, which can be used to get
 * the Loadable value of a RecoilValue.
 */
class GetLoadable
private constructor() {
    @Suppress("NOTHING_TO_INLINE")
    inline operator fun <S> invoke(recoilValue: RecoilValue<S>): Loadable<S> {
        return asDynamic()(recoilValue).unsafeCast<Loadable<S>>()
    }
}
