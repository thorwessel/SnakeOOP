package snake

import models.Direction
import models.Direction.*
import models.State

class Snake {
    //Holds the "state" of the snake, for now has predetermined initial state.
    private var stateOfSnake: MutableList<State> = mutableListOf(
        State(
        xPosition = 8,
        yPosition = 8,
        length = 3,
        nextDirections = mutableListOf(left)
    ), State(
        xPosition = 8,
        yPosition = 9,
        length = 2,
        nextDirections = mutableListOf(up)
    ), State(
        xPosition = 8,
        yPosition = 10,
        length = 3,
        nextDirections = mutableListOf(up)
    ), State(
        xPosition = 8,
        yPosition = 11,
        length = 4,
        nextDirections = mutableListOf(left)
    ))

    fun getInitialState(): List<State> {
        return stateOfSnake
    }

    fun addDirection(inputDirection: Direction) {
        if (validMove(inputDirection, stateOfSnake[0].nextDirections[stateOfSnake[0].nextDirections.size])) {
            stateOfSnake[0].nextDirections.add(inputDirection)
        }
    }

    fun nextMove(snakeStates: MutableList<State> = stateOfSnake): List<State> {
        val currentState = snakeStates[0]
        val nextStateDirection = nextStateDirection(currentState)
        checkLength(snakeStates[0])
        stateOfSnake.asReversed().add(nextStateDirection)
        return mutableListOf(nextStateDirection).plus(stateOfSnake)
    }

    private fun validMove(direction: Direction, currentStateDirection: Direction): Boolean {
        return when {
            direction == up     && currentStateDirection == down    -> false
            direction == left   && currentStateDirection == right   -> false
            direction == down   && currentStateDirection == up      -> false
            direction == right  && currentStateDirection == left    -> false
            else -> true
        }
    }

    private fun nextStateDirection(state: State): State {
        val nextState: State = state.copy()
        when {
            state.nextDirections[0] == left -> nextState.xPosition -= 1
            state.nextDirections[0] == up -> nextState.yPosition -= 1
            state.nextDirections[0] == right -> nextState.xPosition += 1
            state.nextDirections[0] == down -> nextState.yPosition += 1
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

    //TODO state, list of objects containing position.
    //TODO Method: Add new state, take input from user, whether the new move will be food/crash into self.

}