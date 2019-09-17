import com.soywiz.korio.lang.assert
import food.Food
import kotlin.random.*

import kotlin.test.Test
import kotlin.test.assertEquals

class FoodTest() {

    private fun getRandomList(random: Random): MutableList<Int> =
        MutableList(10000) { random.nextInt(0, 16) }

    @Test
    fun `Check if getNewFoodLocation returns position  within valid range`() {
        val testFood = Food()
        val testLocation = testFood.getNewFoodLocation()

        assert(testLocation.yPosition in 0..15 && testLocation.xPosition in 0..15)
    }

    @Test
    fun `check if positions returned is truly random`() {
        val testFood = Food()
        val randomList = getRandomList(Random(69))

        for (i in 1..10000) {
            val positionsFromFood = testFood.getNewFoodLocation()
            randomList.add(positionsFromFood.xPosition)
            randomList.add(positionsFromFood.yPosition)
        }

        val sortedMapOfRandomValues = mutableMapOf<Int, Int>()
        for (i in 0..15) {
            sortedMapOfRandomValues[i] = 0
        }

        randomList.forEach {
            sortedMapOfRandomValues[it] = sortedMapOfRandomValues.getValue(it) + 1
        }

        println(sortedMapOfRandomValues)

        var isItRandomQuestionmark = true
        val expectedValue = randomList.size / 16
        val allowedUpperDeviation = expectedValue / 100 * 10

        sortedMapOfRandomValues.forEach {
            if (it.key > expectedValue + allowedUpperDeviation || it.key < expectedValue - allowedUpperDeviation) {
                isItRandomQuestionmark = false
            }
        }

        assertEquals(true, isItRandomQuestionmark, "The numbers are not equally distributed?!?!")
    }
}