@file:JsModule("recoil")
@file:JsNonModule
@file:Suppress("unused")

package wrapper.recoil.enums

/**
 * External enum class representing the trigger for an atom effect.
 *
 * @property get The trigger for a "get" operation.
 * @property set The trigger for a "set" operation.
 */
@Suppress("NAME_CONTAINS_ILLEGAL_CHARS", "ENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING")
// language=JavaScript
@JsName("""({get: 'get', set: 'set'})""")
external enum class AtomEffectTrigger {
    get,
    set
}
