package snake

import models.Direction
import models.Direction.*
import models.Position
import models.State

class Snake {
    //Holds the "state" of the snake
    private var stateOfSnake: MutableList<Position> = mutableListOf()

    private var length: Int = 2

    private val nextDirections: MutableList<Direction> = mutableListOf(values().toList().shuffled().first())

    fun resetInternalState(): MutableList<Position> {
        stateOfSnake = mutableListOf(
            Position(
                xPosition = (0..15).random(),
                yPosition = (0..15).random()
            ))

        length = 2
        return stateOfSnake
    }

    fun addDirection(inputDirection: Direction) {
        nextDirections.add(inputDirection)
    }

    fun getLastQueuedDirection(): Direction {
        val lengthOfDirectionQueue = nextDirections.size - 1
        return nextDirections[lengthOfDirectionQueue]
    }

    fun checkFoodCollision(foodPosition: Position): Boolean {
        return if (foodPosition == stateOfSnake[0]) {
            addLength()
            true
        } else false
    }

    private fun addLength() {
        length += 1
    }

    fun checkSnakeCollision(): Boolean {
        for (stateIterator in 1 until stateOfSnake.size) {
            if (stateOfSnake[stateIterator].xPosition == stateOfSnake[0].xPosition &&
                stateOfSnake[stateIterator].yPosition == stateOfSnake[0].yPosition) {
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

    private fun nextStatePosition(currentState: Position): Position {
        var workingXPosition: Int = currentState.xPosition
        var workingYPosition: Int = currentState.yPosition
        when {
            nextDirections[0] == left   -> workingXPosition -= 1
            nextDirections[0] == up     -> workingYPosition -= 1
            nextDirections[0] == right  -> workingXPosition += 1
            nextDirections[0] == down   -> workingYPosition += 1
        }

        when {
            workingXPosition < 0        -> workingXPosition = 15
            workingXPosition > 15       -> workingXPosition = 0
            workingYPosition < 0        -> workingYPosition = 15
            workingYPosition > 15       -> workingYPosition = 0
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