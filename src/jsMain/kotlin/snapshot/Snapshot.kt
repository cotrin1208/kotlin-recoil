@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package snapshot

import enums.RecoilStateType
import state.RecoilValue
import types.*
import kotlin.js.Promise

/**
 * Represents information about a component.
 */
external interface ComponentInfo {
    val name: String
}

/**
 * Represents a collection of subscribers for a Recoil value.
 *
 * @param T the type parameter for the Recoil value
 */
external interface Subscribers<T> {
    val nodes: Iterable<RecoilValue<T>>
    val components: Iterable<ComponentInfo>
}

/**
 * Represents the state information of a Recoil state.
 *
 * @param T the type parameter for the Recoil state value
 */
external interface RecoilStateInfo<T> {
    val loadable: Loadable<T>?
    val isActive: Boolean
    val isSet: Boolean
    val isModified: Boolean
    val type: RecoilStateType
    val deps: Iterable<RecoilValue<T>>
    val subscribers: Subscribers<T>
}

/**
 * Represents a snapshot of Recoil state.
 *
 * This interface provides methods for manipulating and accessing Recoil state,
 * including getting loadable values and promises, mapping over the snapshot,
 * retaining the snapshot, and checking if the snapshot is retained.
 */
external interface Snapshot {
    val getLoadable: GetLoadable
    val getPromise: GetPromise

    fun map(cb: (mutableSnapshot: MutableSnapshot) -> Unit): Snapshot
    fun asyncMap(cb: (mutableSnapshot: MutableSnapshot) -> Promise<Unit>): Promise<Snapshot>
    fun retain(): () -> Unit
    fun isRetained(): Boolean
}

/**
 * Represents a mutable snapshot of Recoil state, allowing for updating and resetting Recoil values.
 *
 * @property set A utility class for setting the value of a Recoil value.
 * @property reset A utility class for resetting the value of a Recoil value.
 */
@Suppress("MemberVisibilityCanBePrivate")
external class MutableSnapshot {
    val set: SetRecoilState
    val reset: ResetRecoilState
}
