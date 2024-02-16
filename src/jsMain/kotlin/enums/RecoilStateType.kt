@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package wrapper.recoil.enums

@Suppress("NAME_CONTAINS_ILLEGAL_CHARS", "ENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING")
// language=JavaScript
@JsName("""({atom: 'atom', selector: 'selector'})""")
external enum class RecoilStateType {
    atom,
    selector
}
