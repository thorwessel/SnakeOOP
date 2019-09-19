import com.soywiz.klock.seconds
import com.soywiz.korev.*
import com.soywiz.korge.*
import com.soywiz.korge.time.delay
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korma.geom.vector.rect

import game.Game
import models.Direction
import models.GameObjects.*

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
    // Initiate game
    val game = Game()
    game.start()

    //Scale used for graphics
    val scale = 32
    // Speed step, used to add delay between each render
    val delayInterval = 0.3

    launchImmediately {
        while (true) {
            val currentObjectsToBeRendered = game.next()

            val newView = container()

            newView.graphics {
                currentObjectsToBeRendered[Player1]?.map {
                    fill(Colors.DARKGREEN) {
                        rect(it.xPosition * scale, it.yPosition * scale, scale, scale)
                    }
                }

                currentObjectsToBeRendered[Player2]?.map {
                    fill(Colors.DARKBLUE) {
                        rect(it.xPosition * scale, it.yPosition * scale, scale, scale)
                    }
                }

                currentObjectsToBeRendered[Food]?.map {
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
        down(Key.DOWN) {
            game.registerInput(Direction.down, Player1)
        }
        down(Key.UP) {
            game.registerInput(Direction.up, Player1)
        }
        down(Key.LEFT) {
            game.registerInput(Direction.left, Player1)
        }
        down(Key.RIGHT) {
            game.registerInput(Direction.right, Player1)
        }
        down(Key.S) {
            game.registerInput(Direction.down, Player2)
        }
        down(Key.W) {
            game.registerInput(Direction.up, Player2)
        }
        down(Key.A) {
            game.registerInput(Direction.left, Player2)
        }
        down(Key.D) {
            game.registerInput(Direction.right, Player2)
        }
    }
}