
import com.soywiz.korio.lang.assert
import models.Direction
import models.Position

import snake.Snake
import kotlin.test.Test
import kotlin.test.assertEquals


class SnakeTest {

    @Test
    fun `When next state is calculated the last state of snake is removed`() {
        val snake = Snake()
        val initialStates = snake.resetInternalState()
        val sizeOfSnake = (initialStates.size - 1)
        val nextStates = snake.getNextSnake()
        assert(nextStates[sizeOfSnake] != initialStates[sizeOfSnake])
    }

    @Test
    fun `Check that snake self-collision is detected`() {
        val snake = Snake()
        snake.resetInternalState()
        snake.addLength()
        snake.addLength()
        snake.addLength()
        snake.addLength()
        snake.addLength()
        snake.addLength()
        snake.addDirection(Direction.Left)
        snake.getNextSnake()
        snake.addDirection(Direction.Up)
        snake.getNextSnake()
        snake.addDirection(Direction.Right)
        snake.getNextSnake()
        snake.addDirection(Direction.Down)
        snake.getNextSnake()

        assertEquals(true, snake.checkSelfCollision(), "Snake did not collide")
    }

    @Test
    fun `Check that food collision is detected`() {
        val snake = Snake()
        snake.resetInternalState()

        val snakePosition = snake.getNextSnake()

        assertEquals(true, snake.checkFoodCollision(snakePosition[0]),
            "Food was not hit as expected!")
    }
}