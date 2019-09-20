package game

import models.Position
import food.Food
import models.Direction
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
    private val allSnakes = listOf<Snake>(snake1, snake2)

    var players = 1

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
                it.addLength()
            }
        }

        return mapOf(Player1 to snake1Positions, Player2 to snake2Positions, Food to listOf(foodLocation))
    }

    fun registerInput(direction: Direction, player: GameObjects) {
        when {
            direction == down && player == Player1 && (movement.checkForValidMove(snake1.getLastQueuedDirection(), down))
            -> snake1.addDirection(down)
            direction == up && player == Player1 && (movement.checkForValidMove(snake1.getLastQueuedDirection(), up))
            -> snake1.addDirection(up)
            direction == left && player == Player1 && (movement.checkForValidMove(snake1.getLastQueuedDirection(), left))
            -> snake1.addDirection(left)
            direction == right && player == Player1 && (movement.checkForValidMove(snake1.getLastQueuedDirection(), right))
            -> snake1.addDirection(right)
            direction == down && player == Player2 && (movement.checkForValidMove(snake2.getLastQueuedDirection(), down))
            -> snake2.addDirection(down)
            direction == up && player == Player2 && (movement.checkForValidMove(snake2.getLastQueuedDirection(), up))
            -> snake2.addDirection(up)
            direction == left && player == Player2 && (movement.checkForValidMove(snake2.getLastQueuedDirection(), left))
            -> snake2.addDirection(left)
            direction == right && player == Player2 && (movement.checkForValidMove(snake2.getLastQueuedDirection(), right))
            -> snake2.addDirection(right)
        }
    }
}