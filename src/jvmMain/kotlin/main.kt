import com.soywiz.klock.seconds
import com.soywiz.korev.*
import com.soywiz.korge.*
import com.soywiz.korge.time.delay
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.vector.rect

import food.Food
import models.Direction
import snake.Snake

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
    // Create food
    val food = Food()
    food.getNewFoodLocation()

    // Start new snake game
    val snake = Snake()
    snake.reset()

    //Scale used for graphics
    val scale = 32
    // Speed step, used to add delay between each render
    val delayInterval = 0.3


    launchImmediately {
        while (true) {
            val foodLocation = food.foodLocation
            if (snake.checkForCollision()) snake.reset()

            val snakeStates = snake.nextStates()
            if (snake.checkFoodCollision(foodLocation)) food.getNewFoodLocation()

            val newView = container()

            newView.graphics {
                snakeStates.map {
                    fill(Colors.DARKGREEN) {
                        rect(it.xPosition * scale, it.yPosition * scale, scale, scale)
                    }
                }
                fill(Colors.MAROON) {
                    rect(foodLocation.xPosition * scale, foodLocation.yPosition * scale, scale, scale)
                }
            }
            delay(delayInterval.seconds)
            this.removeChild(newView)
        }
    }

    // key listeners
    keys {
        down(Key.DOWN) {
            snake.addDirection(Direction.down)
        }
        down(Key.UP) {
            snake.addDirection(Direction.up)
        }
        down(Key.LEFT) {
            snake.addDirection(Direction.left)
        }
        down(Key.RIGHT) {
            snake.addDirection(Direction.right)
        }
    }
}