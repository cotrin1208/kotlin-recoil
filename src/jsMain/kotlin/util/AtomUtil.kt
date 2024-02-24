@file:Suppress("unused")

package util

import js.objects.jso
import state.*
import types.AtomEffect
import kotlin.js.Promise

fun <T> atom(
    key: String,
    default: T,
    vararg effects: AtomEffect<T> = emptyArray()
): RecoilState<T> {
    return atom(jso<AtomOptionsWithDefault<T>> {
        this.key = key
        this.default = default
        this.effects = effects
    })
}

fun <T> promiseAtom(
    key: String,
    default: Promise<T>?,
    vararg effects: AtomEffect<T> = emptyArray()
): RecoilState<T> {
    return atom(jso<AtomOptionsWithPromise<T>> {
        this.key = key
        default?.let { this.default = default }
        this.effects = effects
    })
}

fun <T> atomEffect(effect: AtomEffectOption<T>.() -> Unit): AtomEffect<T> {
    return effect
}

fun <T> AtomEffectOption<T>.onSet(callback: AtomEffectOnSetOption<T>.() -> Unit) {
    this.rawOnSet { newValue, oldValue, isReset ->
        callback(object : AtomEffectOnSetOption<T> {
            override val newValue: T = newValue
            override val oldValue: T = oldValue
            override val isReset: Boolean = isReset
        })
    }
}

interface AtomEffectOnSetOption<T> {
    val newValue: T
    val oldValue: T
    val isReset: Boolean
}
