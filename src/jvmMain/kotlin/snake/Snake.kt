package snake

import models.Direction
import models.Direction.*
import models.FoodLocation
import models.State

class Snake {
    //Holds the "state" of the snake
    private var stateOfSnake: MutableList<State> = mutableListOf()

    fun reset(): List<State> {
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
        if (checkForValidMove(inputDirection, stateOfSnake[0].nextDirections[stateOfSnake[0].nextDirections.size - 1])) {
            stateOfSnake[0].nextDirections.add(inputDirection)
        }
    }

    // Comment for Jens, side effects, how could I re-structure to avoid the "addLength()" call?
    fun hitFood(foodLocation: FoodLocation): Boolean {
        return if (foodLocation.xPosition == stateOfSnake[0].xPosition && foodLocation.yPosition == stateOfSnake[0].yPosition ) {
            addLength()
            true
        } else false
    }

    fun checkForCollision(): Boolean {
        for (stateIterator in 1 until stateOfSnake.size - 1) {
            if (stateOfSnake[stateIterator].xPosition == stateOfSnake[0].xPosition &&
                stateOfSnake[stateIterator].yPosition == stateOfSnake[0].yPosition) {
                return true
            }
        }
        return false
    }

    fun nextStates(): List<State> {
        val currentState = stateOfSnake[0]
        val nextState = nextStatePosition(currentState)

        updateLength(nextState)

        // Add the new state at the front
        // Comment for Jens, is there a clearer way to accomplish adding an element to the front of a collection?
        stateOfSnake.asReversed().add(nextState)
        return mutableListOf(nextState).plus(stateOfSnake)
    }

    private fun checkForValidMove(newDirection: Direction, currentDirection: Direction): Boolean {
        return when {
            newDirection == up     && currentDirection == down    -> false
            newDirection == left   && currentDirection == right   -> false
            newDirection == down   && currentDirection == up      -> false
            newDirection == right  && currentDirection == left    -> false
            // Avoid adding several inputs for same directions
            newDirection       ==     currentDirection            -> false
            else -> true
        }
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

        // Comment for Jens, does this make more sense to move to separate routine?
        if (currentState.nextDirections.size > 1) {
            workingPositionState.nextDirections.removeAt(0)
        }

        return workingPositionState
    }

     private fun updateLength(currentState: State) {
        if (stateOfSnake.size > currentState.length) {
            stateOfSnake.removeAt(stateOfSnake.size - 1)
        }
    }

    private fun addLength() {
        stateOfSnake[0].length += 1
    }
}