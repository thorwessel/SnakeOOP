package graphics

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.View
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.vector.rect

import models.Position

class Graphics {
    // Scale used for pixelated graphics
    private val pixelScale = 32

    fun getNextGraphics(snakesList: List<List<Position>>, food: Position): View {
        val newView = Container()

        newView.graphics {
            snakesList[0].map {
                fill(Colors.DARKGREEN) {
                    rect(it.xPosition * pixelScale, it.yPosition * pixelScale, pixelScale, pixelScale)
                }
            }
            snakesList[1].map {
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