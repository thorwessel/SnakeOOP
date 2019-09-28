package game

import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korge.view.View
import food.Food
import graphics.Graphics
import models.Direction.*
import movement.Movement
import snake.Snake

class Game {

    private val movement = Movement()
    private val food = Food()
    private val graphics = Graphics()
    private val allSnakes = listOf(Snake(), Snake())

    fun start() {
        food.getNewFoodLocation()

        allSnakes.forEach {
            it.resetInternalState()
        }
    }

    fun getView(): View {
        val foodLocation = food.position

        val snakePositions = allSnakes.map { it.getNextSnake() }

        snakePositions.forEach { positions ->
            allSnakes.forEach {
                it.checkCollision(positions)
                it.checkSelfCollision()
            }
        }

        allSnakes.forEach {
            if (it.checkFoodCollision(foodLocation)) {
                food.getNewFoodLocation()
                it.addLength()
            }
        }

        return graphics.getNextGraphics(snakePositions, foodLocation)
    }

    fun getScoreText(): String {
        val snake1Score = allSnakes[0].length - 1
        val snake2Score = allSnakes[1].length - 1
        return "Green $snake1Score - $snake2Score Blue"
    }

    fun registerInput(keyEvent: KeyEvent) {
        when {
            keyEvent.key == Key.DOWN &&     (movement.checkForValidMove(allSnakes[0].getLastQueuedDirection(), Down))     -> allSnakes[0].addDirection(Down)
            keyEvent.key == Key.UP &&       (movement.checkForValidMove(allSnakes[0].getLastQueuedDirection(), Up))       -> allSnakes[0].addDirection(Up)
            keyEvent.key == Key.LEFT &&     (movement.checkForValidMove(allSnakes[0].getLastQueuedDirection(), Left))     -> allSnakes[0].addDirection(Left)
            keyEvent.key == Key.RIGHT &&    (movement.checkForValidMove(allSnakes[0].getLastQueuedDirection(), Right))    -> allSnakes[0].addDirection(Right)
            keyEvent.key == Key.S &&        (movement.checkForValidMove(allSnakes[1].getLastQueuedDirection(), Down))     -> allSnakes[1].addDirection(Down)
            keyEvent.key == Key.W &&        (movement.checkForValidMove(allSnakes[1].getLastQueuedDirection(), Up))       -> allSnakes[1].addDirection(Up)
            keyEvent.key == Key.A &&        (movement.checkForValidMove(allSnakes[1].getLastQueuedDirection(), Left))     -> allSnakes[1].addDirection(Left)
            keyEvent.key == Key.D &&        (movement.checkForValidMove(allSnakes[1].getLastQueuedDirection(), Right))    -> allSnakes[1].addDirection(Right)
        }
    }
}