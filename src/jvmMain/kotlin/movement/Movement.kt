package movement

import models.Direction

class Movement {
    // Comment for Jens, I started by having this routine in the Snake class, but decided to move it here.
    // However, now the key listeners in main is more complex, would it make more sense to keep the check for a valid
    // move in the Snake class instead?
    fun checkForValidMove(newDirection: Direction, currentDirection: Direction): Boolean {
        return when {
            newDirection == Direction.up    && currentDirection == Direction.down   -> false
            newDirection == Direction.left  && currentDirection == Direction.right  -> false
            newDirection == Direction.down  && currentDirection == Direction.up     -> false
            newDirection == Direction.right && currentDirection == Direction.left   -> false
            // Avoid adding several inputs for same directions
            newDirection                ==     currentDirection                     -> false
            else -> true
        }
    }
}