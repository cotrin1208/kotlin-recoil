package types

import loadable.BaseLoadable
import state.AtomEffectOption
import state.RecoilState
import state.RecoilValue

typealias AtomEffect<T> = (AtomEffectOption<T>) -> Unit
typealias ResetRecoilState = (RecoilState<out Any>) -> Unit
typealias RefreshRecoilState = (RecoilValue<out Any>) -> Unit
typealias Loadable<T> = BaseLoadable<T>
typealias AtomEffectOnSet<T> = (param: (newValue: T, oldValue: T, isReset: Boolean) -> Unit) -> Unit
