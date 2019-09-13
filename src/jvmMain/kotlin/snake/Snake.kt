package snake

import models.Direction
import models.Direction.*
import models.FoodLocation
import models.State

class Snake {
    //Holds the "state" of the snake, for now has predetermined initial state.
    private var stateOfSnake: MutableList<State> = mutableListOf(
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

        return emptyList()
    }

    fun addDirection(inputDirection: Direction) {
        if (validMove(inputDirection, stateOfSnake[0].nextDirections[stateOfSnake[0].nextDirections.size - 1])) {
            stateOfSnake[0].nextDirections.add(inputDirection)
        }
    }

    fun hitFood(foodLocation: FoodLocation): Boolean {
        return if (foodLocation.xPosition == stateOfSnake[0].xPosition && foodLocation.yPosition == stateOfSnake[0].yPosition ) {
            addLength()
            true
        } else false
    }

    fun checkCollision(): Boolean {
        for (stateIterator in 1..stateOfSnake.size - 1) {
            if (stateOfSnake[stateIterator].xPosition == stateOfSnake[0].xPosition &&
                stateOfSnake[stateIterator].yPosition == stateOfSnake[0].yPosition) {
                return true
            }
        }
        return false
    }

    fun nextMove(): List<State> {
        val currentState = stateOfSnake[0]
        val nextStateDirection = nextStateDirection(currentState)

        checkLength(nextStateDirection)

        stateOfSnake.asReversed().add(nextStateDirection)
        return mutableListOf(nextStateDirection).plus(stateOfSnake)
    }

    private fun validMove(direction: Direction, currentStateDirection: Direction): Boolean {
        return when {
            direction == up     && currentStateDirection == down    -> false
            direction == left   && currentStateDirection == right   -> false
            direction == down   && currentStateDirection == up      -> false
            direction == right  && currentStateDirection == left    -> false

            direction       ==     currentStateDirection            -> false
            else -> true
        }
    }

    private fun nextStateDirection(state: State): State {
        val nextState: State = state.copy()
        when {
            state.nextDirections[0] == left     -> nextState.xPosition -= 1
            state.nextDirections[0] == up       -> nextState.yPosition -= 1
            state.nextDirections[0] == right    -> nextState.xPosition += 1
            state.nextDirections[0] == down     -> nextState.yPosition += 1
        }

        if (nextState.xPosition < 0) {
            nextState.xPosition = 15
        }
        if (nextState.yPosition < 0) {
            nextState.yPosition = 15
        }
        if (nextState.xPosition > 15) {
            nextState.xPosition = 0
        }
        if (nextState.yPosition > 15) {
            nextState.yPosition = 0
        }

        if (state.nextDirections.size > 1) {
            nextState.nextDirections.removeAt(0)
        }

        return nextState
    }

     private fun checkLength(currentState: State) {
        if (stateOfSnake.size > currentState.length) {
            stateOfSnake.removeAt(stateOfSnake.size - 1)
        }
    }

    private fun addLength() {
        stateOfSnake[0].length += 1
    }
    //TODO state, list of objects containing position.
    //TODO Method: Add new state, take input from user, whether the new move will be food/crash into self.

}