package models

data class State (
    var xPosition: Int,
    var yPosition: Int,
    var length: Int,
    var nextDirections: MutableList<Direction>
)