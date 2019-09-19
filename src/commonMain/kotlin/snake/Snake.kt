package snake

import models.Direction
import models.Direction.*
import models.Position
import models.State

class Snake {
    //Holds the "state" of the snake
    private var stateOfSnake: MutableList<Position> = mutableListOf()

    private var length: Int = 0

    private val nextDirections: MutableList<Direction> = mutableListOf(left)

    fun resetInternalState(): MutableList<Position> {
        stateOfSnake = mutableListOf(
            Position(
                xPosition = 8,
                yPosition = 8
            ),
            Position(
                xPosition = 7,
                yPosition = 8
            ))

        return stateOfSnake
    }

    fun addDirection(inputDirection: Direction) {
        nextDirections.add(inputDirection)
    }

    fun getLastQueuedDirection(): Direction {
        val lengthOfDirectionQueue = nextDirections.size - 1
        return nextDirections[lengthOfDirectionQueue]
    }

    // Comment for Jens, side effects, any ideas on how I can re-structure to avoid the "addLength()" call?
    fun checkFoodCollision(position: Position): Boolean {
        return if (position.xPosition == stateOfSnake[0].xPosition && position.yPosition == stateOfSnake[0].yPosition ) {
            addLength()
            true
        } else false
    }

    private fun addLength() {
        length += 1
    }

    fun checkSnakeCollision(): Boolean {
        for (stateIterator in 1 until stateOfSnake.size - 1) {
            if (stateOfSnake[stateIterator].xPosition == stateOfSnake[0].xPosition &&
                stateOfSnake[stateIterator].yPosition == stateOfSnake[0].yPosition) {
                return true
            }
        }
        return false
    }

    fun getNextSnake(): List<Position> {
        updateDirectionQueue()

        val nextState = nextStatePosition(stateOfSnake[0])

        updateLength()

        stateOfSnake.asReversed().add(nextState)
        return stateOfSnake
    }

    private fun nextStatePosition(currentState: Position): Position {
        var workingXPosition: Int = currentState.xPosition
        var workingYPosition: Int = currentState.yPosition
        when {
            nextDirections[0] == left     -> workingXPosition -= 1
            nextDirections[0] == up       -> workingYPosition -= 1
            nextDirections[0] == right    -> workingXPosition += 1
            nextDirections[0] == down     -> workingYPosition += 1
        }

        when {
            workingXPosition < 0 -> workingXPosition = 15
            workingXPosition > 15 -> workingXPosition = 0
            workingYPosition < 0 -> workingYPosition = 15
            workingYPosition > 15 -> workingYPosition = 0
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