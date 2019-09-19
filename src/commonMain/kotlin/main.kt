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
import movement.Movement
import snake.Snake

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
    // Initiate Movement validator
    val movement = Movement()

    // Create food
    val food = Food()
    food.getNewFoodLocation()

    // Start new snake game
    val snake1 = Snake()
    val snake2 = Snake()
    snake1.resetInternalState()
    snake2.resetInternalState()

    //Scale used for graphics
    val scale = 32
    // Speed step, used to add delay between each render
    val delayInterval = 0.3


    launchImmediately {
        while (true) {
            val foodLocation = food.position

            if (snake1.checkSelfCollision()) snake1.resetInternalState()
            if (snake2.checkSelfCollision()) snake2.resetInternalState()

            val snake1Positions = snake1.getNextSnake()
            val snake2Positions = snake2.getNextSnake()

            if (snake1.checkSnakeCollision(snake2Positions)) snake1.resetInternalState()
            if (snake2.checkSnakeCollision(snake1Positions)) snake2.resetInternalState()

            if (snake1.checkFoodCollision(foodLocation)) food.getNewFoodLocation()
            if (snake2.checkFoodCollision(foodLocation)) food.getNewFoodLocation()

            val newView = container()

            newView.graphics {
                snake1Positions.map {
                    fill(Colors.DARKGREEN) {
                        rect(it.xPosition * scale, it.yPosition * scale, scale, scale)
                    }
                }

                snake2Positions.map {
                    fill(Colors.DARKBLUE) {
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
            if (movement.checkForValidMove(snake1.getLastQueuedDirection(), Direction.down)) {
                snake1.addDirection(Direction.down)
            }
        }
        down(Key.UP) {
            if (movement.checkForValidMove(snake1.getLastQueuedDirection(), Direction.up)) {
                snake1.addDirection(Direction.up)
            }
        }
        down(Key.LEFT) {
            if (movement.checkForValidMove(snake1.getLastQueuedDirection(), Direction.left)) {
                snake1.addDirection(Direction.left)
            }
        }
        down(Key.RIGHT) {
            if (movement.checkForValidMove(snake1.getLastQueuedDirection(), Direction.right)) {
                snake1.addDirection(Direction.right)
            }
        }
        down(Key.S) {
            if (movement.checkForValidMove(snake2.getLastQueuedDirection(), Direction.down)) {
                snake2.addDirection(Direction.down)
            }
        }
        down(Key.W) {
            if (movement.checkForValidMove(snake2.getLastQueuedDirection(), Direction.up)) {
                snake2.addDirection(Direction.up)
            }
        }
        down(Key.A) {
            if (movement.checkForValidMove(snake2.getLastQueuedDirection(), Direction.left)) {
                snake2.addDirection(Direction.left)
            }
        }
        down(Key.D) {
            if (movement.checkForValidMove(snake2.getLastQueuedDirection(), Direction.right)) {
                snake2.addDirection(Direction.right)
            }
        }
    }
}