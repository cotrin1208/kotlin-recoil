package wrapper.recoil.types

import wrapper.recoil.loadable.BaseLoadable
import wrapper.recoil.state.AtomEffectOption
import wrapper.recoil.state.RecoilState
import wrapper.recoil.state.RecoilValue

typealias AtomEffect<T> = (AtomEffectOption<T>) -> Unit
typealias ResetRecoilState = (RecoilState<out Any>) -> Unit
typealias RefreshRecoilState = (RecoilValue<out Any>) -> Unit
typealias Loadable<T> = BaseLoadable<T>
typealias AtomEffectOnSet<T> = (param: (newValue: T, oldValue: T, isReset: Boolean) -> Unit) -> Unit
