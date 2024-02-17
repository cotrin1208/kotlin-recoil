@file:Suppress("unused")

package util

import react.rawUseRecoilCallback
import react.CallBackInterface

inline fun <T : Function<*>> useRecoilCallback(
    vararg dependencies: Any? = emptyArray(),
    crossinline callback: CallBackInterface.() -> T
): T {
    val fn = { callbackInterface: CallBackInterface ->
        callbackInterface.callback()
    }
    return rawUseRecoilCallback(fn, dependencies)
}
