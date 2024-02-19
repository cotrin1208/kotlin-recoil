@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package enums

sealed external interface LoadableState

@Suppress("NAME_CONTAINS_ILLEGAL_CHARS", "ENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING")
// language=JavaScript
@JsName("""({hasValue: 'hasValue'})""")
external enum class HasValue: LoadableState {
    hasValue;
}

@Suppress("NAME_CONTAINS_ILLEGAL_CHARS", "ENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING")
// language=JavaScript
@JsName("""({loading: 'loading'})""")
external enum class Loading: LoadableState {
    loading;
}

@Suppress("NAME_CONTAINS_ILLEGAL_CHARS", "ENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING")
// language=JavaScript
@JsName("""({hasError: 'hasError'})""")
external enum class HasError: LoadableState {
    hasError;
}
