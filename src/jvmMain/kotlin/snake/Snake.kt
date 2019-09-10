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

    private fun nextState(state: State): State {
        var nextState = state
        if (state.nextDirections[0] == left) {
            nextState.xPosition -= 1
        } else if (state.nextDirections[0] == up) {
            nextState.xPosition -= 1
        } else if (state.nextDirections[0] == right) {
            nextState.xPosition += 1
        } else if (state.nextDirections[0] == down) {
            nextState.yPosition += 1
        }

        return nextState
    }

    //TODO state, list of objects containing position.
    //TODO Method: Add new state, take input from user, whether the new move will be food/crash into self.

}