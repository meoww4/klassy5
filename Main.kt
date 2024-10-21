import kotlin.math.sqrt

data class Point(val x: Double, val y: Double)

class Triangle(val p1: Point, val p2: Point, val p3: Point) {
    fun incenter(): Point {
        val a = p2.distanceTo(p3)
        val b = p1.distanceTo(p3)
        val c = p1.distanceTo(p2)
        val perimeter = a + b + c

        val centerX = (a * p1.x + b * p2.x + c * p3.x) / perimeter
        val centerY = (a * p1.y + b * p2.y + c * p3.y) / perimeter

        return Point(centerX, centerY)
    }

    fun inradius(): Double {
        val a = p2.distanceTo(p3)
        val b = p1.distanceTo(p3)
        val c = p1.distanceTo(p2)
        val semiPerimeter = (a + b + c) / 2
        val area = sqrt(semiPerimeter * (semiPerimeter - a) * (semiPerimeter - b) * (semiPerimeter - c))

        return area / semiPerimeter
    }
}

fun Point.distanceTo(other: Point): Double {
    return sqrt((other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y))
}

class Circle(val center: Point, val radius: Double) {
    override fun toString(): String {
        return "Окружность с центром в точке (${center.x}, ${center.y}) и радиусом $radius"
    }
}

fun safeInput(prompt: String): Double {
    while (true) {
        try {
            print(prompt)
            return readLine()?.toDouble() ?: throw NumberFormatException()
        } catch (e: NumberFormatException) {
            println("Ошибка: Введите корректное число.")
        }
    }
}

fun main() {
    val x1 = safeInput("x1: ")
    val y1 = safeInput("y1: ")
    val x2 = safeInput("x2: ")
    val y2 = safeInput("y2: ")
    val x3 = safeInput("x3: ")
    val y3 = safeInput("y3: ")

    val triangle = Triangle(Point(x1, y1), Point(x2, y2), Point(x3, y3))

    val center = triangle.incenter()
    val radius = triangle.inradius()

    val circle = Circle(center, radius)
    println(circle)
}