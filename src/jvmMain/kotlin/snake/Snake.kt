package snake

import models.Direction
import models.Direction.*
import models.State

class Snake {
    //Holds the "state" of the snake, for now has predetermined initial state.
    private var stateOfSnake: MutableList<State> = mutableListOf(State(
        xPosition = 15,
        yPosition = 15,
        length = 1,
        nextDirections = mutableListOf(left)
    ))

    fun nextMove(snakeStates: MutableList<State> = stateOfSnake): State {
        return stateOfSnake[0]
    }

    fun AddDirection(inputDirection: Direction) {
        stateOfSnake[0].nextDirections.add(inputDirection)
    }

    private fun nextStateDirection(state: State): State {
        val nextState = state
        when {
            state.nextDirections[0] == left -> nextState.xPosition -= 1
            state.nextDirections[0] == up -> nextState.xPosition -= 1
            state.nextDirections[0] == right -> nextState.xPosition += 1
            state.nextDirections[0] == down -> nextState.yPosition += 1
        }

        if (nextState.xPosition < 0) {
            nextState.xPosition = 512
        } else if (nextState.yPosition < 0) {
            nextState.yPosition = 512
        }

        if (state.nextDirections.size > 1) {
            nextState.nextDirections.removeAt(0)
        }

        return nextState
    }

    private fun checkLength(currentState: State) {
        if (stateOfSnake.size > currentState.length) {
            stateOfSnake.removeAt(stateOfSnake.size)
        }
    }

    //TODO state, list of objects containing position.
    //TODO Method: Add new state, take input from user, whether the new move will be food/crash into self.

}