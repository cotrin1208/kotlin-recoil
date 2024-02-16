@file:Suppress("unused")

package wrapper.recoil.util

import wrapper.recoil.react.rawUseRecoilCallback
import wrapper.recoil.state.CallBackInterface

inline fun <T : Function<*>> useRecoilCallback(
    vararg dependencies: Any? = emptyArray(),
    crossinline callback: CallBackInterface.() -> T
): T {
    val fn = { callbackInterface: CallBackInterface ->
        callbackInterface.callback()
    }
    return rawUseRecoilCallback(fn, dependencies)
}
