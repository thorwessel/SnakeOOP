import models.Direction
import movement.Movement

import kotlin.test.Test
import kotlin.test.assertEquals


class MovementTest {
    @Test
    fun `check that identical direction is not added to the directions que`() {
        val testMovement = Movement()

        assertEquals(false, testMovement.checkForValidMove(Direction.Down, Direction.Down),
            "Direction down + down returned true")
        assertEquals(false, testMovement.checkForValidMove(Direction.Up, Direction.Up),
            "Direction up + up returned true")
        assertEquals(false, testMovement.checkForValidMove(Direction.Left, Direction.Left),
            "Direction left + left returned true")
        assertEquals(false, testMovement.checkForValidMove(Direction.Right, Direction.Right),
            "Direction right + right returned true")
    }

    @Test
    fun `check that invalid move is not added to the directions que`() {
        // In snake it is not possible to go the opporsite way of the current direction
        val testMovement = Movement()

        assertEquals(false, testMovement.checkForValidMove(Direction.Down, Direction.Up),
            "Direction down + up returned true")
        assertEquals(false, testMovement.checkForValidMove(Direction.Up, Direction.Down),
            "Direction up + down returned true")
        assertEquals(false, testMovement.checkForValidMove(Direction.Left, Direction.Right),
            "Direction left + right returned true")
        assertEquals(false, testMovement.checkForValidMove(Direction.Right, Direction.Left),
            "Direction right + left returned true")
    }

    @Test
    fun `check that valid move is not added to the directions que`() {
        // In snake it is not possible to go the opporsite way of the current direction
        val testMovement = Movement()

        assertEquals(true, testMovement.checkForValidMove(Direction.Down, Direction.Left),
        "Direction down + left returned false")
        assertEquals(true, testMovement.checkForValidMove(Direction.Down, Direction.Right),
            "Direction down + right returned false")

        assertEquals(true, testMovement.checkForValidMove(Direction.Up, Direction.Left),
            "Direction up + left returned false")
        assertEquals(true, testMovement.checkForValidMove(Direction.Up, Direction.Right),
            "Direction up + right returned false")

        assertEquals(true, testMovement.checkForValidMove(Direction.Left, Direction.Up),
            "Direction left + up returned false")
        assertEquals(true, testMovement.checkForValidMove(Direction.Left, Direction.Down),
            "Direction left + down returned false")

        assertEquals(true, testMovement.checkForValidMove(Direction.Right, Direction.Up),
            "Direction right + up returned false")
        assertEquals(true, testMovement.checkForValidMove(Direction.Right, Direction.Down),
            "Direction right + down returned false")

    }
}
