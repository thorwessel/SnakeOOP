import models.Direction
import movement.Movement

import kotlin.test.Test
import kotlin.test.assertEquals


class MovementTest {
    @Test
    fun `check that identical direction is not added to the directions que`() {
        val testMovement = Movement()

        assertEquals(false, testMovement.checkForValidMove(Direction.down, Direction.down),
            "Direction down + down returned true")
        assertEquals(false, testMovement.checkForValidMove(Direction.up, Direction.up),
            "Direction up + up returned true")
        assertEquals(false, testMovement.checkForValidMove(Direction.left, Direction.left),
            "Direction left + left returned true")
        assertEquals(false, testMovement.checkForValidMove(Direction.right, Direction.right),
            "Direction right + right returned true")
    }

    @Test
    fun `check that invalid move is not added to the directions que`() {
        // In snake it is not possible to go the opporsite way of the current direction
        val testMovement = Movement()

        assertEquals(false, testMovement.checkForValidMove(Direction.down, Direction.up),
            "Direction down + up returned true")
        assertEquals(false, testMovement.checkForValidMove(Direction.up, Direction.down),
            "Direction up + down returned true")
        assertEquals(false, testMovement.checkForValidMove(Direction.left, Direction.right),
            "Direction left + right returned true")
        assertEquals(false, testMovement.checkForValidMove(Direction.right, Direction.left),
            "Direction right + left returned true")
    }

    @Test
    fun `check that valid move is not added to the directions que`() {
        // In snake it is not possible to go the opporsite way of the current direction
        val testMovement = Movement()

        assertEquals(true, testMovement.checkForValidMove(Direction.down, Direction.left),
        "Direction down + left returned false")
        assertEquals(true, testMovement.checkForValidMove(Direction.down, Direction.right),
            "Direction down + right returned false")

        assertEquals(true, testMovement.checkForValidMove(Direction.up, Direction.left),
            "Direction up + left returned false")
        assertEquals(true, testMovement.checkForValidMove(Direction.up, Direction.right),
            "Direction up + right returned false")

        assertEquals(true, testMovement.checkForValidMove(Direction.left, Direction.up),
            "Direction left + up returned false")
        assertEquals(true, testMovement.checkForValidMove(Direction.left, Direction.down),
            "Direction left + down returned false")

        assertEquals(true, testMovement.checkForValidMove(Direction.right, Direction.up),
            "Direction right + up returned false")
        assertEquals(true, testMovement.checkForValidMove(Direction.right, Direction.down),
            "Direction right + down returned false")

    }
}
