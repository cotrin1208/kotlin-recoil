@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package react

import snapshot.Snapshot
import types.RefreshRecoilState
import types.ResetRecoilState
import types.SetRecoilState

/**
 * Represents a callback interface for manipulating and accessing Recoil state.
 *
 * This interface provides methods and properties for setting and resetting Recoil values,
 * refreshing the Recoil state, accessing snapshots of the state, and navigating to a specific snapshot.
 */
external interface CallBackInterface {
    val set: SetRecoilState
    val reset: ResetRecoilState
    val refresh: RefreshRecoilState
    val snapshot: Snapshot
    val gotoSnapshot: (Snapshot) -> Unit
}
