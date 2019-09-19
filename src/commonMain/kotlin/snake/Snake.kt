package snake

import models.Direction
import models.Direction.*
import models.Position
import models.State

class Snake {
    //Holds the "state" of the snake
    private var stateOfSnake: MutableList<State> = mutableListOf()

    fun resetInternalState(): MutableList<State> {
        stateOfSnake = mutableListOf(
            State(
                xPosition = 8,
                yPosition = 8,
                length = 1,
                nextDirections = mutableListOf(left)),
            State(
                xPosition = 7,
                yPosition = 8,
                length = 1,
                nextDirections = mutableListOf(left))
        )

        return stateOfSnake
    }

    fun addDirection(inputDirection: Direction) {
        stateOfSnake[0].nextDirections.add(inputDirection)
    }

    private fun updateDirectionQue(currentState: State): State {
        val workingPositionState = currentState.copy()
        if (currentState.nextDirections.size > 1) {
            workingPositionState.nextDirections.removeAt(0)
        }

        return workingPositionState
    }

    fun getLastQueuedDirection(): Direction {
        val lengthOfDirectionQue = stateOfSnake[0].nextDirections.size - 1
        return stateOfSnake[0].nextDirections[lengthOfDirectionQue]
    }

    // Comment for Jens, side effects, any ideas on how I can re-structure to avoid the "addLength()" call?
    fun checkFoodCollision(position: Position): Boolean {
        return if (position.xPosition == stateOfSnake[0].xPosition && position.yPosition == stateOfSnake[0].yPosition ) {
            addLength()
            true
        } else false
    }

    private fun addLength() {
        stateOfSnake[0].length += 1
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

    fun getNextSnake(): List<State> {
        val currentState = stateOfSnake[0]

        var nextState = updateDirectionQue(currentState)
        nextState = nextStatePosition(nextState)

        updateLength(nextState)

        // Add the new state at the front
        // Comment for Jens, is there a clearer way to accomplish adding an element to the front of a collection?
        stateOfSnake.asReversed().add(nextState)
        return stateOfSnake
    }

    private fun nextStatePosition(currentState: State): State {
        val workingPositionState: State = currentState.copy()
        when {
            currentState.nextDirections[0] == left     -> workingPositionState.xPosition -= 1
            currentState.nextDirections[0] == up       -> workingPositionState.yPosition -= 1
            currentState.nextDirections[0] == right    -> workingPositionState.xPosition += 1
            currentState.nextDirections[0] == down     -> workingPositionState.yPosition += 1
        }

        // Comment for Jens, this seems rather lengthy, I could break this routine up into smaller ones.
        // But the code would still have a lot of "if" statements.
        //TODo make into a when statement instead.
        if (workingPositionState.xPosition < 0) {
            workingPositionState.xPosition = 15
        }
        if (workingPositionState.yPosition < 0) {
            workingPositionState.yPosition = 15
        }
        if (workingPositionState.xPosition > 15) {
            workingPositionState.xPosition = 0
        }
        if (workingPositionState.yPosition > 15) {
            workingPositionState.yPosition = 0
        }

        return workingPositionState
    }

     private fun updateLength(currentState: State) {
        if (stateOfSnake.size > currentState.length) {
            stateOfSnake.removeAt(stateOfSnake.size - 1)
        }
    }
}