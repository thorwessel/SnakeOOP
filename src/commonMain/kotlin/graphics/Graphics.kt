package graphics

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.scene.SceneContainer
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.View
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.vector.rect
import food.Food
import models.GameObjects
import models.Position

class Graphics() {
    private val pixelScale = 32

    fun getNextGraphics(snake1: List<Position>, snake2: List<Position>, food: Position): View {
        val newView = Container()

        newView.graphics {
            snake1.map {
                fill(Colors.DARKGREEN) {
                    rect(it.xPosition * pixelScale, it.yPosition * pixelScale, pixelScale, pixelScale)
                }
            }
            snake2.map {
                fill(Colors.DARKBLUE) {
                    rect(it.xPosition * pixelScale, it.yPosition * pixelScale, pixelScale, pixelScale)
                }
            }
            fill(Colors.MAROON) {
                rect(food.xPosition * pixelScale, food.yPosition * pixelScale, pixelScale, pixelScale)
            }
        }

        return newView
    }
}