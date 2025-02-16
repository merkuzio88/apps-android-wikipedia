package org.wikipedia.homeworks.homework06

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AnyOf

enum class Color { RED, BLUE, GREEN, YELLOW, BLACK, WHITE }

data class Shape(
    val sideLength: Float,
    val sidesCount: Int,
    val color: Color
)

// Матчер для проверки длины в диапазоне
class LengthRangeMatcher(private val min: Float, private val max: Float) : TypeSafeMatcher<Shape>() {
    override fun describeTo(description: Description) {
        description.appendText("длина стороны должна быть от $min до $max")
    }

    override fun matchesSafely(item: Shape): Boolean {
        return item.sideLength in min..max
    }
}

// Матчер для проверки количества углов
class AnglesCountMatcher(private val angles: Int) : TypeSafeMatcher<Shape>() {
    init {
        require(angles == 0 || angles >= 3) { "Углов должно быть 0 или больше 3" }
    }

    override fun describeTo(description: Description) {
        description.appendText("должно быть $angles углов")
    }

    override fun matchesSafely(item: Shape): Boolean {
        if (item.sidesCount <= 2) return angles == 0
        return item.sidesCount == angles
    }
}

// Матчер для проверки чётности сторон
class SideMatcher(private val even: Boolean) : TypeSafeMatcher<Shape>() {
    override fun describeTo(description: Description) {
        val type = if (even) "чётное" else "нечётное"
        description.appendText("количество сторон должно быть $type")
    }

    override fun matchesSafely(item: Shape): Boolean {
        return item.sidesCount % 2 == 0 == even
    }
}

// Матчер для проверки цвета
class ColorMatcher(val color: Color) : TypeSafeMatcher<Shape>() {
    override fun describeTo(description: Description) {
        description.appendText("цвет должен быть $color")
    }

    override fun matchesSafely(item: Shape): Boolean {
        return item.color == color
    }
}

// Матчер для проверки положительности длины
class LengthCheckMatcher(private val positive: Boolean) : TypeSafeMatcher<Shape>() {
    override fun describeTo(description: Description) {
        val text = if(positive) "положительной" else "отрицательной"
        description.appendText("длина должна быть $text")
    }

    override fun matchesSafely(item: Shape): Boolean {
        return item.sideLength > 0 == positive
    }
}

// Матчер для проверки положительности количества сторон
class SidesCheckMatcher(private val positive: Boolean = true) : TypeSafeMatcher<Shape>() {
    override fun describeTo(description: Description) {
        val text = if(positive) "положительным" else "отрицательным"
        description.appendText("количество сторон должно быть $text")
    }

    override fun matchesSafely(item: Shape): Boolean {
        return item.sidesCount > 0 == positive
    }
}

class MatcherBuilder {
    private val listOfMatchers = mutableListOf<Matcher<Shape>>()

    operator fun invoke(function: MatcherBuilder.() -> Unit) {
        function()
    }

    fun buildAllMatchers(): Matcher<Shape> = allOf(listOfMatchers)
    fun buildAnyMatchers(): AnyOf<Shape> = anyOf(listOfMatchers)
    fun clearMatchers() = listOfMatchers.clear()

    fun lengthRangeCheck(from: Float, to: Float) {
        listOfMatchers.add(LengthRangeMatcher(from, to))
    }

    fun cornersCheck(expectedCorners: Int) {
        listOfMatchers.add(AnglesCountMatcher(expectedCorners))
    }

    fun sideMatcher(even: Boolean = true) {
        listOfMatchers.add(SideMatcher(even))
    }

    fun shapesColor(color: Color) {
        listOfMatchers.add(ColorMatcher(color))
    }

    fun isPositiveLength(positive: Boolean = true) {
        listOfMatchers.add(LengthCheckMatcher(positive))
    }

    fun isPositiveSide(positive: Boolean = true) {
        listOfMatchers.add(SidesCheckMatcher(positive))
    }
}

fun main() {
    val shapes = listOf(
        Shape(10f, 3, Color.RED), Shape(5f, 4, Color.BLUE), Shape(7f, 2, Color.GREEN),
        Shape(0.5f, 1, Color.YELLOW), Shape(-3f, 5, Color.BLACK), Shape(8f, -2, Color.WHITE),
        Shape(12f, 6, Color.RED), Shape(15f, 8, Color.BLUE), Shape(20f, 4, Color.GREEN),
        Shape(9f, 5, Color.YELLOW), Shape(2f, 3, Color.BLACK), Shape(11f, 7, Color.WHITE),
        Shape(6f, 10, Color.RED), Shape(3f, 2, Color.BLUE), Shape(4f, 1, Color.GREEN),
        Shape(25f, 12, Color.YELLOW), Shape(30f, 14, Color.BLACK), Shape(35f, 16, Color.WHITE),
        Shape(40f, 18, Color.RED), Shape(50f, 20, Color.BLUE)
    )

    val builder = MatcherBuilder()

    // Пример использования всех матчеров
    builder {
        lengthRangeCheck(5f, 10f)
        cornersCheck(0)
        sideMatcher(true)
        shapesColor(Color.GREEN)
        isPositiveLength()
        isPositiveSide()
    }

    var result = shapes.filter { builder.buildAllMatchers().matches(it) }
    println("Подходит под все условия:")
    println(result)

    // Пример использования любого из матчеров
    builder.clearMatchers()
    builder {
        shapesColor(Color.GREEN)
        cornersCheck(3)
    }

    result = shapes.filter { builder.buildAnyMatchers().matches(it) }
    println("\nПодходит хотя бы под одно условие:")
    println(result)

    // Проверка на ошибку при неверном количестве углов
    try {
        builder.clearMatchers()
        builder { cornersCheck(2) }
    } catch (e: Exception) {
        println("\nОжидаемая ошибка при попытке создать матчер для 2 углов: ${e.message}")
    }
}
