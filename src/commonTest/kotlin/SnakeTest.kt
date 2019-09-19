
import com.soywiz.korio.lang.assert
import models.Direction
import models.Position
import models.State

import snake.Snake
import kotlin.test.Test
import kotlin.test.assertEquals


class SnakeTest {

    @Test
    fun `When next state is calculated the last state of snake is removed`() {
        val snake = Snake()
        val initialState = snake.resetInternalState()
        val sizeOfSnake = (initialState.size - 1)
        assert(snake.getNextSnake()[sizeOfSnake] != initialState[sizeOfSnake])
    }


    /*
    @Test
    fun `When direction of the snake is left, the next position will be x - 1`() {
        val snake = Snake()
        val initialState = snake.resetInternalState()

        /*
        Initial state = State(
            xPosition = 8,
            yPosition = 8,
            length = 1,
            nextDirections = mutableListOf(Direction.left))
         */

        initialState[0].nextDirections = mutableListOf(Direction.left)

        assertEquals(expected = State(
            xPosition = 7,
            yPosition = 8,
            length = 1,
            nextDirections = mutableListOf(Direction.left)),

            actual = snake.getNextSnake()[0],

            message = "xPosition is not updated correctly on direction left"
        )
    }

    @Test
    fun `When direction of the snake is up, the next position will be y - 1`() {
        val snake = Snake()
        val initialState = snake.resetInternalState()

        /*
        Initial state = State(
            xPosition = 8,
            yPosition = 8,
            length = 1,
            nextDirections = mutableListOf(Direction.left))
         */

        initialState[0].nextDirections = mutableListOf(Direction.up)

        assertEquals(expected = State(
            xPosition = 8,
            yPosition = 7,
            length = 1,
            nextDirections = mutableListOf(Direction.up)),

            actual = snake.getNextSnake()[0],

            message = "yPosition is not updated correctly on direction up"
        )
    }

    @Test
    fun `When direction of the snake is right, the next position will be x + 1`() {
        val snake = Snake()
        val initialState = snake.resetInternalState()

        /*
        Initial state = State(
            xPosition = 8,
            yPosition = 8,
            length = 1,
            nextDirections = mutableListOf(Direction.left))
         */

        initialState[0].nextDirections = mutableListOf(Direction.right)

        assertEquals(expected = State(
            xPosition = 9,
            yPosition = 8,
            length = 1,
            nextDirections = mutableListOf(Direction.right)),

            actual = snake.getNextSnake()[0],

            message = "xPosition is not updated correctly on direction right"
        )
    }

    @Test
    fun `When direction of the snake is down, the next position will be y + 1`() {
        val snake = Snake()
        val initialState = snake.resetInternalState()

        /*
        Initial state = State(
            xPosition = 8,
            yPosition = 8)
         */

        initialState.nextDirections = mutableListOf(Direction.down)

        assertEquals(expected = Position(
            xPosition = 8,
            yPosition = 9),

            actual = snake.getNextSnake()[0],

            message = "yPosition is not updated correctly on direction down"
        )
    }

    @Test
    fun `Check if last queued direction is correctly returned`() {
        val snake = Snake()
        snake.resetInternalState()

        snake.addDirection(Direction.up)
        snake.addDirection(Direction.left)
        snake.addDirection(Direction.right)
        snake.addDirection(Direction.down)

        assertEquals(Direction.down, snake.getLastQueuedDirection(), "Last added direction was not returned")
    }

     */


    @Test
    fun `Check that snake collision is detected`() {
        val snake = Snake()
        snake.resetInternalState()
        snake.checkFoodCollision(foodPosition = Position(8, 8))
        snake.checkFoodCollision(foodPosition = Position(8, 8))
        snake.checkFoodCollision(foodPosition = Position(8, 8))
        snake.checkFoodCollision(foodPosition = Position(8, 8))
        snake.checkFoodCollision(foodPosition = Position(8, 8))
        snake.checkFoodCollision(foodPosition = Position(8, 8))
        snake.addDirection(Direction.left)
        snake.addDirection(Direction.left)
        snake.addDirection(Direction.up)
        snake.addDirection(Direction.right)
        snake.addDirection(Direction.down)
        snake.getNextSnake()
        snake.getNextSnake()
        snake.getNextSnake()
        snake.getNextSnake()
        snake.getNextSnake()


        assertEquals(true, snake.checkSnakeCollision(), "Snake did not collide")
    }

    @Test
    fun `Check that food collision is detected`() {
        val snake = Snake()
        snake.resetInternalState()

        /*
       Initial state = State(
           xPosition = 8,
           yPosition = 8,
           length = 1,
           nextDirections = mutableListOf(Direction.left))
        */

        snake.getNextSnake()

        assertEquals(true, snake.checkFoodCollision(Position(xPosition = 7, yPosition = 8)),
            "Food was not hit as expected!")
    }
}