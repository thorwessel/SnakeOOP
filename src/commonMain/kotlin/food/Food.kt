package food

import models.FoodLocation

class Food {
    var foodLocation: FoodLocation = FoodLocation(xPosition = 16, yPosition = 16)

    fun getNewFoodLocation(): FoodLocation {
        val randomXPosition = (0..15).random()
        val randomYPosition = (0..15).random()

        foodLocation = FoodLocation(randomXPosition, randomYPosition)
        return FoodLocation(randomXPosition, randomYPosition)
    }
}