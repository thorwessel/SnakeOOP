package food

import models.FoodLocation

class Food {
    var foodLocation: FoodLocation = FoodLocation(xPosition = 16, yPosition = 16)

    //TODO location of food in a variable?
    fun getNewFoodLocation(): FoodLocation {
        val randomXPosition = (0..15).random()
        val randomYPosition = (0..15).random()

        foodLocation = FoodLocation(randomXPosition, randomYPosition)
        return FoodLocation(randomXPosition, randomYPosition)
    }
    //TODO Alternative, Method: will the snake eat food?
    //TODO Method: Spawn new food
    //TODO Method:
}