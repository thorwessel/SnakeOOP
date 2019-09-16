
import com.soywiz.korio.lang.assert

import snake.Snake




class SnakeTest {

    private val dependecy = "0"

    @kotlin.test.Test
    fun `When next state is calculated the last state of snake is removed`() {
        val snake = Snake()
        snake.reset()
        assert(snake.getNextSnake() != snake.getNextSnake())
    }
}