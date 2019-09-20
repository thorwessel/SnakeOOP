package game

import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import models.Position
import food.Food
import models.Direction.*
import models.GameObjects
import models.GameObjects.*
import movement.Movement
import snake.Snake

class Game {

    private val movement = Movement()
    private val food = Food()
    private val snake1 = Snake()
    private val snake2 = Snake()
    private val allSnakes = listOf(snake1, snake2)

    fun start() {
        food.getNewFoodLocation()

        allSnakes.forEach {
            it.resetInternalState()
        }
    }

    fun next(): Map<GameObjects, List<Position>> {
        val foodLocation = food.position

        allSnakes.forEach {
            if (it.checkSelfCollision()) it.resetInternalState()
        }

        val snake1Positions = snake1.getNextSnake()
        val snake2Positions = snake2.getNextSnake()

        if (snake1.checkSnakeCollision(snake2Positions)) snake1.resetInternalState()
        if (snake2.checkSnakeCollision(snake1Positions)) snake2.resetInternalState()

        allSnakes.forEach {
            if (it.checkFoodCollision(foodLocation)) {
                food.getNewFoodLocation()
                it.length += 1
            }
        }

        return mapOf(Player1 to snake1Positions, Player2 to snake2Positions, Food to listOf(foodLocation))
    }

    fun getScoreText(): String {
        return "Green ${snake1.length} - ${snake2.length} Blue "
    }

    fun registerInput(keyEvent: KeyEvent) {
        when {
            keyEvent.key == Key.DOWN && (movement.checkForValidMove(snake1.getLastQueuedDirection(), Down))
            -> snake1.addDirection(Down)
            keyEvent.key == Key.UP && (movement.checkForValidMove(snake1.getLastQueuedDirection(), Up))
            -> snake1.addDirection(Up)
            keyEvent.key == Key.LEFT && (movement.checkForValidMove(snake1.getLastQueuedDirection(), Left))
            -> snake1.addDirection(Left)
            keyEvent.key == Key.RIGHT && (movement.checkForValidMove(snake1.getLastQueuedDirection(), Right))
            -> snake1.addDirection(Right)
            keyEvent.key == Key.S && (movement.checkForValidMove(snake2.getLastQueuedDirection(), Down))
            -> snake2.addDirection(Down)
            keyEvent.key == Key.W && (movement.checkForValidMove(snake2.getLastQueuedDirection(), Up))
            -> snake2.addDirection(Up)
            keyEvent.key == Key.A && (movement.checkForValidMove(snake2.getLastQueuedDirection(), Left))
            -> snake2.addDirection(Left)
            keyEvent.key == Key.D && (movement.checkForValidMove(snake2.getLastQueuedDirection(), Right))
            -> snake2.addDirection(Right)
        }
    }
}