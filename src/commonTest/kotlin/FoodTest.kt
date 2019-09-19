import com.soywiz.korio.lang.assert
import food.Food
import kotlin.random.*

import kotlin.test.Test
import kotlin.test.assertEquals

class FoodTest {

    private fun getRandomList(random: Random): MutableList<Int> =
        MutableList(10000) { random.nextInt(0, 16) }

    @Test
    fun `Check if getNewFoodLocation returns position  within valid range`() {
        val testFood = Food()
        val testLocation = testFood.getNewFoodLocation()

        assert(testLocation.yPosition in 0..15 && testLocation.xPosition in 0..15)
    }

    @Test
    fun `check if positions returned is evenly distributed`() {
        /* This test is just for fun, I wanted to try my luck at testing randomness. It turns out, that is rather
        difficult, or at least require basic knowledge of math. Instead, I wanted to look into whether the positions
        from the getNewFoodLocation() method actually was equally distributed. To do this, I make a list using another
        random calculation, then add twice as many numbers from the getNewFoodLocation() method. Make a Map of the
        integers to see how many of each was on the original list and then check whether each integer is within 10 %
        of what a equal distribution of integers would be. Made sense, no? Good.

        Comment for Jens, perhaps we could discus testing randomness, I would love to hear your take!
         */
        val testFood = Food()
        val randomList = getRandomList(Random(69))

        (1..10000).forEach {
            val positionsFromFood = testFood.getNewFoodLocation()
            randomList.add(positionsFromFood.xPosition)
            randomList.add(positionsFromFood.yPosition)
        }

        val sortedMapOfRandomValues = mutableMapOf<Int, Int>()
        (0..15).forEach {
            sortedMapOfRandomValues[it] = 0
        }

        randomList.forEach {
            sortedMapOfRandomValues[it] = sortedMapOfRandomValues.getValue(it) + 1
        }

        var isItRandomQuestionMark = true
        val expectedValue = randomList.size / 16
        val allowedUpperDeviation = expectedValue / 100 * 10

        sortedMapOfRandomValues.forEach {
            if (it.value > expectedValue + allowedUpperDeviation || it.value < expectedValue - allowedUpperDeviation) {
                isItRandomQuestionMark = false
            }
        }

        assertEquals(true, isItRandomQuestionMark, "The numbers are not equally distributed?!?!")
    }
}