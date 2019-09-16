package movement

import models.Direction

class Movement {
    // Comment for Jens, I started by having this routine in the Snake class, but decided to move it here.
    // Should I use make a polymorphic object instead?
    fun checkForValidMove(newDirection: Direction, lastQueuedDirection: Direction): Boolean {
        return when {
            newDirection == Direction.up    && lastQueuedDirection == Direction.down   -> false
            newDirection == Direction.left  && lastQueuedDirection == Direction.right  -> false
            newDirection == Direction.down  && lastQueuedDirection == Direction.up     -> false
            newDirection == Direction.right && lastQueuedDirection == Direction.left   -> false
            // Avoid adding several inputs for same directions
            newDirection                ==     lastQueuedDirection                     -> false
            else -> true
        }
    }
}