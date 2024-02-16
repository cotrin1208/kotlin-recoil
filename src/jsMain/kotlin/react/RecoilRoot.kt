@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package wrapper.recoil.react

import react.FC
import react.PropsWithChildren

/**
 * The `RecoilRoot` variable represents the root component of the Recoil state management system.
 *
 * @property RecoilRoot The root component used to declare Recoil atomic values and manage their state.
 */
@JsName("RecoilRoot")
external val RecoilRoot: FC<PropsWithChildren>
