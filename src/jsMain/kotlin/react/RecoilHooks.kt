@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package react

import state.RecoilState
import state.RecoilValue
import types.Loadable
import types.LoadableStateInstance
import types.RecoilStateInstance

/**
 * Retrieves the current value and setter function of a Recoil state.
 *
 * @param recoilState The Recoil state to retrieve the value and setter for.
 * @return A [RecoilStateInstance] object representing the current value and setter function.
 */
external fun <T> useRecoilState(recoilState: RecoilState<T>): RecoilStateInstance<T>

/**
 * Retrieves the value from a Recoil value.
 *
 * @param recoilValue the Recoil value to fetch the value from
 * @return the value retrieved from the Recoil value
 * @param T the type of the value stored in the Recoil value
 */
external fun <T> useRecoilValue(recoilValue: RecoilValue<T>): T

/**
 * A hook for setting the value of a Recoil state atom or selector.
 *
 * @param recoilState The Recoil state object.
 * @return A function that can be used to set the value of the Recoil state.
 */
external fun <T> useSetRecoilState(recoilState: RecoilState<T>): (T) -> Unit

/**
 * Executes the given function with access to the Recoil state.
 *
 * @param fn The function to be executed.
 * @param deps The dependencies of the function (optional).
 * @return A function that accepts an array of dependencies and returns the result of the function execution.
 */
@JsName("useRecoilCallback")
external fun <T : Function<*>> rawUseRecoilCallback(
    fn: (CallBackInterface) -> T,
    deps: Dependencies = definedExternally
): T

/**
 * Retrieves the loadable state of a Recoil state value.
 *
 * @param recoilState The Recoil state value to retrieve the loadable state from.
 * @return The loadable state instance of the Recoil state value.
 * @param T The type of the value.
 */
external fun <T> useRecoilStateLoadable(recoilState: RecoilState<T>): LoadableStateInstance<T>

/**
 * Retrieves the loadable value of a Recoil state.
 *
 * @param recoilState the Recoil state whose value is to be retrieved
 * @return the loadable value representing the state value
 * @param T the type of the value
 */
external fun <T> useRecoilValueLoadable(recoilState: RecoilState<T>): Loadable<out T>
