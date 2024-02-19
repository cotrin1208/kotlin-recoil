package util

import enums.LoadableState
import types.Loadable

val Loadable<*>.state: LoadableState
    get() {
        return when (rawState) {
            "hasValue" -> LoadableState.hasValue
            "hasError" -> LoadableState.hasError
            "loading" -> LoadableState.loading
            else -> LoadableState.hasError
        }
    }
