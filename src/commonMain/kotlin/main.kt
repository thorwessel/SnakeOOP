import com.soywiz.klock.seconds
import com.soywiz.korev.*
import com.soywiz.korge.*
import com.soywiz.korge.time.delay
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.vector.rect

import game.Game
import models.GameObjects.*

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
    // Initiate game
    val game = Game()
    game.start()

    // Scale used for graphics
    val scale = 32
    // Speed step, used to add delay between each render
    val delayInterval = 0.3

    // Score
    val score = text("Green 0  - 0 Blue ").apply { this.filtering = false }

    launchImmediately {
        while (true) {
            val snakesAndFood = game.next()

            val newView = container()

            score.text = "Green ${snakesAndFood[Player1]?.size?.minus(2)}" +
                    " - ${snakesAndFood[Player2]?.size?.minus(2)} Blue"

            newView.graphics {
                snakesAndFood[Player1]?.map {
                    fill(Colors.DARKGREEN) {
                        rect(it.xPosition * scale, it.yPosition * scale, scale, scale)
                    }
                }

                snakesAndFood[Player2]?.map {
                    fill(Colors.DARKBLUE) {
                        rect(it.xPosition * scale, it.yPosition * scale, scale, scale)
                    }
                }

                snakesAndFood[Food]?.map {
                    fill(Colors.MAROON) {
                        rect(it.xPosition * scale, it.yPosition * scale, scale, scale)
                    }
                }
            }
            delay(delayInterval.seconds)
            this.removeChild(newView)
        }
    }

    // key listeners
    keys {
        down {
            game.registerInput(this)
        }
    }
}