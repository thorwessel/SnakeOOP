package snake

import models.Direction
import models.Direction.*
import models.Position

class Snake {

    private var stateOfSnake: MutableList<Position> = mutableListOf()

    val length: Int
        get() = stateOfSnake.size

    private val nextDirections: MutableList<Direction> = mutableListOf(values().toList().shuffled().first())

    private val lowerBoardLimit = 0
    private val upperBoardLimit = 15

    fun resetInternalState(): MutableList<Position> {
        stateOfSnake = mutableListOf(
            Position(
                xPosition = (0..15).random(),
                yPosition = (0..15).random()
            ))
        return stateOfSnake
    }

    fun addDirection(inputDirection: Direction) {
        nextDirections.add(inputDirection)
    }

    fun addLength() {
        val lastState = stateOfSnake.last()
        stateOfSnake.add(lastState)
    }

    fun getLastQueuedDirection(): Direction {
        val lengthOfDirectionQueue = nextDirections.size - 1
        return nextDirections[lengthOfDirectionQueue]
    }

    fun checkFoodCollision(foodPosition: Position): Boolean {
        return foodPosition == stateOfSnake[0]
    }

    fun checkSnakeCollision(positions: List<Position>): Boolean {
        positions.forEach {
            if (it == stateOfSnake[0]) {
                return true
            }
        }
        return false
    }

    private fun checkCollision(vararg positions: Position): Boolean = positions.any {
        it == stateOfSnake[0]
    }

    fun checkSelfCollision(): Boolean {
        for (stateIterator in 1 until stateOfSnake.size) {
            if (stateOfSnake[stateIterator] == stateOfSnake[0]) {
                return true
            }
        }
        return false
    }

    fun getNextSnake(): List<Position> {
        updateDirectionQueue()
        val nextPosition = nextStatePosition(stateOfSnake[0])
        stateOfSnake.asReversed().add(nextPosition)
        updateLength()

        return stateOfSnake
    }

    fun grow() {
        val nextPosition = nextStatePosition(stateOfSnake[0])
        stateOfSnake.add(stateOfSnake[stateOfSnake.size - 1])
    }

    private fun nextStatePosition(currentState: Position): Position {
        var workingXPosition: Int = currentState.xPosition
        var workingYPosition: Int = currentState.yPosition
        when {
            nextDirections[0] == Left   -> workingXPosition -= 1
            nextDirections[0] == Up     -> workingYPosition -= 1
            nextDirections[0] == Right  -> workingXPosition += 1
            nextDirections[0] == Down   -> workingYPosition += 1
        }

        when {
            workingXPosition < lowerBoardLimit       -> workingXPosition = upperBoardLimit
            workingXPosition > upperBoardLimit       -> workingXPosition = lowerBoardLimit
            workingYPosition < lowerBoardLimit       -> workingYPosition = upperBoardLimit
            workingYPosition > upperBoardLimit       -> workingYPosition = lowerBoardLimit
        }

        return Position(workingXPosition, workingYPosition)
    }

    private fun updateDirectionQueue() {
        if (nextDirections.size > 1) {
            nextDirections.removeAt(0)
        }
    }

     private fun updateLength() {
        if (stateOfSnake.size > length) {
            stateOfSnake.removeAt(stateOfSnake.size - 1)
        }
    }
}