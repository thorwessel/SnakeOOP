package models

data class State (
    var xPosition: Int,
    var yPosition: Int,
    var nextDirections: Array<Direction>
) {
    //Overriding equals, will not use the function though.
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as State

        if (!nextDirections.contentEquals(other.nextDirections)) return false

        return true
    }

    override fun hashCode(): Int {
        return nextDirections.contentHashCode()
    }
}