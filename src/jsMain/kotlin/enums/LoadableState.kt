package enums

/**
 * Represents the state of a loadable entity.
 */
@Suppress("EnumEntryName")
enum class LoadableState {
    hasValue,
    hasError,
    loading;
}
