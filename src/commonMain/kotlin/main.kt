import com.soywiz.klock.seconds
import com.soywiz.korev.*
import com.soywiz.korge.*
import com.soywiz.korge.time.delay
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.launchImmediately

import game.Game

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
    // Initiate game
    val game = Game()
    game.start()

    // Speed step, used to add delay between each render
    val delayInterval = 0.3

    // Score
    val score = text("Green 0  - 0 Blue").apply { this.filtering = false }

    launchImmediately {
        while (true) {
            val nextView = game.getView()
            this.addChild(nextView)
            score.text = game.getScoreText()

            delay(delayInterval.seconds)
            this.removeChild(nextView)
        }
    }

    // key listeners
    keys {
        down {
            game.registerInput(this)
        }
    }
}