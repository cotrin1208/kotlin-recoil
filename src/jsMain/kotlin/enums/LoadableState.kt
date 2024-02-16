@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package wrapper.recoil.enums

/**
 * Represents the state of a loadable entity.
 */
@Suppress("NAME_CONTAINS_ILLEGAL_CHARS", "ENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING")
// language=JavaScript
@JsName("""({hasValue: 'hasValue', hasError: 'hasError', loading: 'loading'})""")
external enum class LoadableState {
    hasValue,
    hasError,
    loading;
}
