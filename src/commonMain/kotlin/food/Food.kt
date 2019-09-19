package food

import models.Position

class Food {
    var position: Position = Position(xPosition = 16, yPosition = 16)

    fun getNewFoodLocation(): Position {
        val randomXPosition = (0..15).random()
        val randomYPosition = (0..15).random()

        position = Position(randomXPosition, randomYPosition)
        return Position(randomXPosition, randomYPosition)
    }
}