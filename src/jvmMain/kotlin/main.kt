import com.soywiz.klock.DateTime
import com.soywiz.klock.seconds
import com.soywiz.korev.*
import com.soywiz.korev.addEventListener
import com.soywiz.korge.*
import com.soywiz.korge.input.onDown
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.input.onKeyUp
import com.soywiz.korge.scene.sceneContainer
import com.soywiz.korge.time.delay
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.*
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.interpolation.Easing
import com.soywiz.korim.*
import com.soywiz.korma.geom.vector.rect

import food.Food
import graphics.Graphics
import movement.Movement
import snake.Snake
import models.State

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
    val food = Food()
    val graphics = Graphics()
    val movement = Movement()
    val snake = Snake()

    var snakeStates = snake.getInitialState()

    launchImmediately {
        //TODO make loop for the entire game here
        //TODO make a small timer to update everything once every x seconds
        while (true) {
            snakeStates = snake.nextMove(snakeStates.toMutableList())

            val newView = container()

            newView.graphics {
                snakeStates.map {
                    fill(Colors.DARKGREEN) {
                        rect(it.xPosition * 32, it.yPosition * 32, 32, 32)
                    }
                }
            }
            delay(0.5.seconds)

            this.removeChild(newView)

        }
    }

    // key information to help troubleshooting
    var line = 0
    fun textLine(text: String) = text(text).position(0, line++ * 16).apply { this.filtering = false }

    textLine("Events :")
    val keysDownText = textLine("Keys:Down")
    val keysUpText = textLine("Keys:Up")


    keys {
        onKeyDown { keysDownText.text = "Key: ${it.key}" }
        onKeyUp { keysUpText.text = "Key: ${it.key}" }
    }
}