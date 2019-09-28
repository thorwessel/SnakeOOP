package movement

import models.Direction

class Movement {
    // Comment for Jens, I started by having this routine in the Snake class, but decided to move it here.
    // Should I use make a polymorphic object instead?
    fun checkForValidMove(newDirection: Direction, lastQueuedDirection: Direction): Boolean {
        return when {
            newDirection == Direction.Up    && lastQueuedDirection == Direction.Down   -> false
            newDirection == Direction.Left  && lastQueuedDirection == Direction.Right  -> false
            newDirection == Direction.Down  && lastQueuedDirection == Direction.Up     -> false
            newDirection == Direction.Right && lastQueuedDirection == Direction.Left   -> false
            // Avoid adding several inputs for same directions
            newDirection                ==     lastQueuedDirection                     -> false

            else -> true
        }
    }
}