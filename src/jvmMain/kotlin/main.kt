import com.soywiz.klock.DateTime
import com.soywiz.klock.seconds
import com.soywiz.korev.*
import com.soywiz.korev.addEventListener
import com.soywiz.korge.*
import com.soywiz.korge.input.onDown
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.input.onKeyUp
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

import food.Food
import graphics.Graphics
import movement.Movement
import snake.Snake

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
    val food = Food()
    val graphics = Graphics()
    val movement = Movement()
    val snake = Snake()

    launchImmediately {
        text("Hello World", textSize = 12.0) {
            position(200, 256)
        }
    }
}